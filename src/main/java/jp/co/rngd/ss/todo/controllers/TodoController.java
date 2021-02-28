package jp.co.rngd.ss.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.rngd.ss.todo.controllers.model_settings.AppModelSetting;
import jp.co.rngd.ss.todo.controllers.model_settings.BaseModelSetting;
import jp.co.rngd.ss.todo.forms.TodoForm;
import jp.co.rngd.ss.todo.models.AppUserModel;
import jp.co.rngd.ss.todo.services.AppUserManagerService;
import jp.co.rngd.ss.todo.services.TodoManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TodoController {
    
    private final TodoManagerService todoManagerService;
    private final AppUserManagerService appUserManagerService;

    @GetMapping("/top")
    public String top(Model model) {
        BaseModelSetting.applyHeaderFooterModel(model);
        model.addAttribute("contents", "todo_app/top::contents");
        model.addAttribute("title", "トップページ");
        AppUserModel user = appUserManagerService.getAppUser();
        log.info("top {}", user);
        
        // todoリストの取得
        var todoList = todoManagerService.getTodoList(user);
        model.addAttribute("todoList", todoList);
        
        AppModelSetting.applyMenuModel(model);
        return "common/layout";
    }
    
    @PostMapping("/todo/regist")
    public String todoRegist(@ModelAttribute TodoForm todoForm) {
        AppUserModel user = appUserManagerService.getAppUser();
        log.info("{}",user);
        todoManagerService.createTodo(todoForm, user);
        
        return "redirect:/top";
    }
}
