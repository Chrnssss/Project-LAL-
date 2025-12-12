package be.ipam.learnalanguage.dto;

import be.ipam.learnalanguage.models.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDto {
    private Long id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private UserRole role;
}

