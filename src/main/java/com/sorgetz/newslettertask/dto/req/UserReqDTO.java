package com.sorgetz.newslettertask.dto.req;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserReqDTO {

    private String name;

    @Email
    private String email;

    private LocalDate birthdate;

}
