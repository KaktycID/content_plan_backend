package content.plan.users.mapper;

import content.plan.users.dto.UsersDTO;
import content.plan.users.service.IconServiceImpl;
import content.plan.users.structure.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersDTOMapper {

    IconServiceImpl service;

    public UsersDTO mapUser(Users users) {
        return UsersDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .name(users.getName())
                .icon(service.getById(users.getIcon()))
                .active(users.isActive())
                .build();
    }
}
