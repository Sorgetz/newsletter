package com.sorgetz.newslettertask.dto.res;

import com.sorgetz.newslettertask.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResDTO {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthdate;

    public static UserResDTO toRes(Users user){
        return UserResDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .build();
    }

}
