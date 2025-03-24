package content.auth;

import content.users.dto.RequestUserDTO;
import content.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping
    @ResponseBody
    public String auth(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.auth(authorizationHeader);
    }
}
