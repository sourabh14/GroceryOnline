package com.example.groceryonline.security.dao.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest implements Serializable {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
