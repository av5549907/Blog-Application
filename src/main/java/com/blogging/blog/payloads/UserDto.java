package com.blogging.blog.payloads;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private  int id;
    @NotNull
    @Size(min=2,max=30)
    private String name;
    @Email(message = "Please provide a valid Email id")
    private String email;
    @NotEmpty(message = "Password is not valid")
    private String password;
    @NotEmpty(message = "About section should not be empty")
    private String about;
}
