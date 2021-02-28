package jp.co.rngd.ss.todo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="app_user")
public class AppUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max=255)
    @Column(name = "username", nullable=false)
    private String username;

    @NotBlank
    @Size(max=255)
    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;
    
    @ManyToMany
    private List<AppUserRoleModel> roles;

}
