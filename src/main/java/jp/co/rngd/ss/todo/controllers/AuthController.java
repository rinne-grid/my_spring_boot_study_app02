package jp.co.rngd.ss.todo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.rngd.ss.todo.controllers.model_settings.BaseModelSetting;
import jp.co.rngd.ss.todo.forms.SignupForm;
import jp.co.rngd.ss.todo.models.AppUserModel;
import jp.co.rngd.ss.todo.services.AppUserManagerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AppUserManagerService appUserManagerService;

    @GetMapping("/")
    public String index(Model model) {
        BaseModelSetting.applyHeaderFooterModel(model);
        model.addAttribute("contents", "brand/contents::contents");
        model.addAttribute("title", "RngdTodo");
        return "common/layout";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("single", "brand/register::contents");
        model.addAttribute("title", "新規登録");
        return "common/layout";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupForm signupForm, HttpServletRequest request) {
        String passwordConfirm = signupForm.getPassword_confirm();
        String password = signupForm.getPassword();
        String user = signupForm.getUser();
        AppUserModel appUser = new AppUserModel();
        appUser.setPassword(password);
        appUser.setUsername(user);
        appUserManagerService.createUser(appUser);
        appUserManagerService.authWithAuthManager(request, user, password);
        System.out.println(passwordConfirm + " : " + password + " : " + user + " ");
        System.out.println(appUser.getUsername() + " : " + appUser.getPassword());

        
        return "redirect:/top";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("single", "brand/login::contents");
        model.addAttribute("title", "ログイン");
        return "common/layout";
    }
}
