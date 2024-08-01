package com.springbootaws.web.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
