package content.users.service;

import content.users.dto.RequestUserDTO;
import content.users.dto.ResponseUserDTO;
import content.users.structure.Users;
import content.auth.AuthResponse;

public interface UserService {

    ResponseUserDTO me(String token);
    String auth(String authorizationHeader);
    Users getUser(Long id);
    ResponseUserDTO getById(Long id);
    ResponseUserDTO create(RequestUserDTO requestUserDTO);
    ResponseUserDTO update(Long id, RequestUserDTO userDTO);
}
