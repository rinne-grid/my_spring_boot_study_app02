package jp.co.rngd.ss.todo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="app_user_role")
public class AppUserRoleModel {

	@Id
	@Size(max=50)
	@Column(name = "role_cd", nullable=false)
	private String roleCd;
	
	@NotBlank
	@Size(max=500)
	@Column(name = "role_name", nullable=false)
	private String roleName;

	public static AppUserRoleModel factory(String roleCd, String roleName) {
		var appUserRole = new AppUserRoleModel();
		appUserRole.setRoleCd(roleCd);
		appUserRole.setRoleName(roleName);
		return appUserRole;
	}
	
}
