package com.lcwd.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String about;
    private List<Rating> ratings; // Not persisted in user DB
}
