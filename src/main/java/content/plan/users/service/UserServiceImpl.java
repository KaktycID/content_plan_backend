package content.plan.users.service;

import content.plan.users.dto.RequestUserDTO;
import content.plan.users.dto.ResponseUserDTO;
import content.plan.users.repository.UsersRepository;
import content.plan.users.structure.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static content.plan.board.mapper.DictionaryMapper.getActualTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final IconServiceImpl mapper;
    private final IconServiceImpl iconMapper;

    private final UsersRepository repository;

    @Override
    public Users getUser(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public ResponseUserDTO getById(Long id) {
        Users user = repository.findById(id).orElseThrow();
        if (user.isActive()) {
            return mapToDto(user);
        }
        else {
            return null;
        }
    }

    @Override
    public ResponseUserDTO create(RequestUserDTO requestUserDTOUserDTO) {
        Users user = mapToEntity(requestUserDTOUserDTO);
        user.setCreateDate(getActualTime());
        user.setActive(true);
        repository.save(user);
        return mapToDto(user);
    }

    @Override
    public ResponseUserDTO update(Long id, ResponseUserDTO userDTO) {
        Users user = repository.findById(id).orElseThrow();

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

            if (mapper.mapIconToEntity(userDTO.getIcon()).getId()!=
                    mapper.mapIconToDTO(user.getIcon()).getId())
                     {
                user.setIcon(mapper.mapIconToEntity(userDTO.getIcon()));
            }
        }
        else {
            user.setActive(false);
        }
        repository.save(user);
        return mapToDto(user);
    }

    public ResponseUserDTO mapToDto(Users users) {
        return ResponseUserDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .name(users.getName())
                .icon(iconMapper.mapIconToDTO(users.getIcon()))
                .createDate(users.getCreateDate().toInstant().toEpochMilli())
                .updateDate(users.getUpdateDate().toInstant().toEpochMilli())
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

}
