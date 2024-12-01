package content.plan.users.api;

import content.plan.users.dto.RequestUserDTO;
import content.plan.users.dto.ResponseUserDTO;
import content.plan.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseUserDTO getById(@PathVariable Long id) {return userService.getById(id);}

    @PostMapping
    @ResponseBody
    public ResponseUserDTO create(@RequestBody RequestUserDTO requestUserDTO) {
        return userService.create(requestUserDTO);
    }

    @PatchMapping("/{id}")
    public ResponseUserDTO update(@PathVariable Long id, @RequestBody ResponseUserDTO responseUserDTO) {
        return userService.update(id, responseUserDTO);
    }




}


