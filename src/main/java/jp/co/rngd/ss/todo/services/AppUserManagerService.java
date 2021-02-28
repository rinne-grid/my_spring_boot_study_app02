package jp.co.rngd.ss.todo.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import jp.co.rngd.ss.todo.consts.AppUserRoles;
import jp.co.rngd.ss.todo.models.AppUserModel;
import jp.co.rngd.ss.todo.models.AppUserModelRepository;
import jp.co.rngd.ss.todo.models.AppUserRoleModel;
//import jp.co.rngd.ss.todo.models.AppUserRoles;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserManagerService {
    private final PasswordEncoder passwordEncoder;
    private final AppUserModelRepository rep;
    private final AuthenticationManager authenticationManager;
    
    //	@Transactional
    public void createUser(AppUserModel appUser) {
        // boolean createResult = true;
        List<AppUserRoleModel> roles = new ArrayList<>();
    
    	roles.add( AppUserRoleModel.factory(AppUserRoles.NORMAL_USER.toString(), "")  );

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setEnabled(true);
        appUser.setRoles(roles);
//        appUser.setRole(AppUserRoles.NORMAL_USER.toString());
        //rep.save(appUser);
        rep.saveAndFlush(appUser);
    }

    // 参考記事
    // https://ja.getdocs.org/spring-security-auto-login-user-after-registration/
    // https://stackoverflow.com/questions/21633555/how-to-inject-authenticationmanager-using-java-configuration-in-a-custom-filter
    // ユーザ登録直後にログイン画面を表示させずアプリにアクセスさせたい
    public void authWithAuthManager(HttpServletRequest request, String user, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
    // アプリユーザの認証情報を取得
    //https://docs.spring.io/spring-security/site/docs/5.4.5/reference/html5/#servlet-authentication-securitycontextholder
    public AppUserModel getAppUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userName = authentication.getName();
        return rep.findByUsername(userName);
        
    }
    
}
