package jp.co.rngd.ss.todo.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignupForm {
    @NotBlank
    private String user;

    @NotBlank
    private String password;

    @NotBlank
    private String password_confirm;
}
