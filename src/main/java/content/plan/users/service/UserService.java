package content.plan.users.service;

import content.plan.users.dto.RequestUserDTO;
import content.plan.users.dto.ResponseUserDTO;
import content.plan.users.structure.Users;
import org.apache.catalina.User;

public interface UserService {

    Users getUser(Long id);
    ResponseUserDTO getById(Long id);
    ResponseUserDTO create(RequestUserDTO requestUserDTO);
    ResponseUserDTO update(Long id, ResponseUserDTO userDTO);
}
