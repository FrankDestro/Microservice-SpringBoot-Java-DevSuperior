package com.devsuperior.hr_user.models.entities.dto;

import com.devsuperior.hr_user.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    private List<RoleDTO> roles = new ArrayList<>();

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        user.getRoles().forEach(roles -> this.roles.add(new RoleDTO(roles)));
    }
}
