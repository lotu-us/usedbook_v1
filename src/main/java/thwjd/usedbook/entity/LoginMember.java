package thwjd.usedbook.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginMember {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
