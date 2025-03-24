package content.users.service;

import content.auth.CustomUserDetails;
import content.board.mapper.DictionaryMapper;
import content.handler.exceptions.UnauthorizedException;
import content.users.dto.RequestUserDTO;
import content.users.dto.ResponseUserDTO;
import content.users.repository.UsersRepository;
import content.users.structure.Users;
import content.handler.exceptions.BadRequestException;
import content.handler.exceptions.NotFoundException;
import content.auth.AuthResponse;
import content.auth.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.Optional;

import static content.board.mapper.DictionaryMapper.getActualTime;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    public static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Пользователь не найден");

    private final IconServiceImpl iconMapper;
    private final JwtService jwtService;
    private final UsersRepository repository;
    private static DictionaryMapper dictionaryMapper;

    @Override
    public ResponseUserDTO me(String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Убираем "Bearer " из начала
        String email = jwtService.extractUsername(token);
        Optional<Users> userByEmail = repository.findByEmail(email);
        if (userByEmail.isPresent()) {
            Users user = userByEmail.get();
            CustomUserDetails userDetails = new CustomUserDetails(user); // Создаем CustomUserDetails

            // Проверяем токен
            if (jwtService.validateToken(token, userDetails)) {
                return mapToDto(user); // Возвращаем DTO пользователя
            } else {
                throw new UnauthorizedException("Неавторизованный доступ");
            }
        } else {
            throw new UnauthorizedException("Неавторизованный доступ");
        }
    }

    @Override
    public String auth(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            throw new UnauthorizedException("Неавторизованный доступ");
        }

        // Извлекаем и декодируем учетные данные
        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        final String[] values = credentials.split(":", 2);

        String email = values[0];
        String password = values[1];

        Optional<Users> userByEmail = repository.findByEmail(email);
        if (userByEmail.isPresent()) {
            Users user = userByEmail.get();
            if (user.isActive() && user.getPassword().equals(password)) {
                // Устанавливаем Authentication в SecurityContextHolder
                CustomUserDetails userDetails = new CustomUserDetails(user);
                // Устанавливаем Authentication в SecurityContextHolder
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                SecurityContextHolder.getContext().setAuthentication(auth);


                return jwtService.generateToken(email); // Генерация JWT
            } else {
                throw new UnauthorizedException("Неверный пароль");
            }
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }




    @Override
    public Users getUser(Long id) {
        Optional<Users> userById = repository.findById(id);
        if (userById.isPresent()) {
            return userById.get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    @Override
    public ResponseUserDTO getById(Long id) {
        Optional<Users> userById = repository.findById(id);
        if (userById.isPresent()) {
            Users user = userById.get();
            if (user.isActive()) {
                return mapToDto(user);
            } else {
                throw new BadRequestException("Пользователь неактивен");
            }
        } else {
            throw new NotFoundException("Пользователь не найден");
            }
    }

    @Override
    public ResponseUserDTO create(RequestUserDTO requestUserDTOUserDTO) {
        validateRequest(requestUserDTOUserDTO);
        Users user = mapToEntity(requestUserDTOUserDTO);
        user.setCreateDate(getActualTime());
        user.setActive(true);
        repository.save(user);
        return mapToDto(user);
    }

    @Override
    public ResponseUserDTO update(Long id, RequestUserDTO userDTO) {
        Optional<Users> userById = repository.findById(id);

        if (userById.isPresent()) {
            Users user = userById.get();
            int iconId = userDTO.getIcon();
            if (user.isActive()) {

                if (!user.getEmail().equals(userDTO.getEmail())) {
                    user.setEmail(userDTO.getEmail());
                }

                if (!user.getPassword().equals(userDTO.getPassword())) {
                    user.setEmail(userDTO.getEmail());
                }

                if (!user.getName().equals(userDTO.getName())) {
                    user.setName(userDTO.getName());
                }

                if (iconId != iconMapper.mapIconToDTO(user.getIcon()).getId()) {
                    user.setIcon(iconMapper.getById(iconId));
                }
            } else {
                user.setActive(false);
            }
            repository.save(user);
        } else {
            throw new NotFoundException("Пользователь не найден");
        }

        return mapToDto(repository.findById(id).get());
    }

    public ResponseUserDTO mapToDto(Users users) {
        return ResponseUserDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .name(users.getName())
                .icon(iconMapper.mapIconToDTO(users.getIcon()))
                .createDate(users.getCreateDate().toInstant().toEpochMilli())
                .updateDate(users.getUpdateDate()!=null ? users.getUpdateDate().toInstant().toEpochMilli() : null)
                .active(users.isActive())
                .build();
    }

    public ResponseUserDTO mapToDtoShort(Users users) {
        return ResponseUserDTO.builder()
                .id(users.getId())
                .name(users.getName())
                .icon(iconMapper.mapIconToDTO(users.getIcon()))
                .active(users.isActive())
                .build();
    }

    public Users mapToEntity(ResponseUserDTO responseUserDTO) {
        Users user = new Users();
        user.setId(responseUserDTO.getId());
        user.setEmail(responseUserDTO.getEmail());
        user.setPassword(responseUserDTO.getPassword());
        user.setName(responseUserDTO.getName());
        user.setIcon(iconMapper.mapIconToEntity(responseUserDTO.getIcon()));
        user.setCreateDate(new Timestamp(responseUserDTO.getCreateDate()));
        user.setUpdateDate(new Timestamp(responseUserDTO.getUpdateDate()));
        user.setActive(responseUserDTO.isActive());
        return user;
    }

    public Users mapToEntity(RequestUserDTO requestUserDTO) {
        Users user = new Users();
        user.setEmail(requestUserDTO.getEmail());
        user.setPassword(requestUserDTO.getPassword());
        user.setName(requestUserDTO.getName());
        user.setIcon(iconMapper.getById(requestUserDTO.getIcon()));
        return user;
    }

    private void validateRequest(RequestUserDTO requestUserDTO) {
        if (requestUserDTO.getEmail() == null || requestUserDTO.getPassword() == null) {
            throw new BadRequestException("Незаполнены обязательные поля");
        }
    }

}
