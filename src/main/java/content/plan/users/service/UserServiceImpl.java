package content.plan.users.service;

import content.plan.users.dto.UsersDTO;
import content.plan.users.mapper.IconDTOMapper;
import content.plan.users.mapper.UsersDTOMapper;
import content.plan.users.repository.UsersRepository;
import content.plan.users.structure.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private static IconDTOMapper mapper;
    private static UsersDTOMapper userMapper;

    private final UsersRepository repository;

    @Override
    public UsersDTO getById(Long id) {
        Users user = repository.findById(id).orElseThrow();
        if (user.isActive()) {
            return userMapper.mapUser(user);
        }
        else {
            return null;
        }
    }

    @Override
    public void update(UsersDTO userDTO) {
        Users user = repository.findById(userDTO.getId()).orElseThrow();

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

            if ((user.getIcon()!=userDTO.getIcon())) {
                user.setIcon(userDTO.getIcon());
            }
        }
    }

    @Override
    public void delete(Long id) {
        Users user = repository.findById(id).orElseThrow();
        user.setActive(false);
    }
}
