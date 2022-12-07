package com.sorgetz.newslettertask.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserReqDTO {

    private String name;

    @Email
    private String email;

    private LocalDate birthdate;

}
