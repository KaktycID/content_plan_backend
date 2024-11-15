package content.plan.users.mapper;

import content.plan.board.dto.DictDTO;
import content.plan.users.dto.UsersDTO;
import content.plan.users.structure.Users;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UsersDTOMapper {

    IconDTOMapper mapper;

    public UsersDTO mapUser(Users users) {
        return UsersDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .name(users.getName())
                .icon(mapper.mapIconToDTO(users.getIcon()))
                .active(users.isActive())
                .build();
    }
}
