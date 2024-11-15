package content.plan.users.service;

import content.plan.users.dto.UsersDTO;

public interface UserService {

    UsersDTO getById(Long id);
    void update(UsersDTO userDTO);
    void delete(Long id);
}
