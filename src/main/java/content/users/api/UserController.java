package content.users.api;

import content.users.dto.RequestUserDTO;
import content.users.dto.ResponseUserDTO;
import content.users.service.UserService;
import content.users.token.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseUserDTO me(@RequestHeader("Authorization") String token) {
        return userService.me(token);}

    @PostMapping("/auth")
    @ResponseBody
    public AuthResponse auth(@RequestBody RequestUserDTO requestUserDTO) {
        return userService.auth(requestUserDTO);
    }

    @GetMapping("/{id}")
    public ResponseUserDTO getById(@PathVariable Long id) {return userService.getById(id);}

    @PostMapping
    @ResponseBody
    public ResponseUserDTO create(@RequestBody RequestUserDTO requestUserDTO) {
        return userService.create(requestUserDTO);
    }

    @PatchMapping("/{id}")
    public ResponseUserDTO update(@PathVariable Long id, @RequestBody RequestUserDTO responseUserDTO) {
        return userService.update(id, responseUserDTO);
    }




}


