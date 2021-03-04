package jp.co.rngd.ss.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rngd.ss.todo.controllers.model_settings.AppModelSetting;
import jp.co.rngd.ss.todo.controllers.model_settings.BaseModelSetting;
import jp.co.rngd.ss.todo.entity.TodoSearchCondition;
import jp.co.rngd.ss.todo.forms.TodoForm;
import jp.co.rngd.ss.todo.models.AppUserModel;
import jp.co.rngd.ss.todo.models.TodoModel;
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
    public String top(Model model, @RequestParam(name = "order", required = false) String order, @RequestParam(name = "filter", required = false) String filter) {
        BaseModelSetting.applyHeaderFooterModel(model);
        model.addAttribute("contents", "todo_app/top::contents");
        model.addAttribute("title", "トップページ");
        AppUserModel user = appUserManagerService.getAppUser();
        log.info("top {}", user);
        log.info("order {}", order);
        log.info("filter {}", filter);
        TodoSearchCondition cond = TodoSearchCondition.of(order, filter);
        
        // todoリストの取得
//        var todoList = todoManagerService.getTodoList(user);
        var todoList = todoManagerService.searchTodoList(user, cond);
        model.addAttribute("todoList", todoList);
        model.addAttribute("order", order);
        model.addAttribute("filter", filter);
        
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
    
    @GetMapping("/todo/{id}/detail")
    public String todoDetail(@PathVariable Integer id, Model model) {
        BaseModelSetting.applyHeaderFooterModel(model);
        
        model.addAttribute("contents", "todo_app/details::contents");
        model.addAttribute("title", "詳細");
        AppUserModel user = appUserManagerService.getAppUser();
        String resultPath = "redirect:/top";
        AppModelSetting.applyMenuModel(model);
        TodoModel todo = todoManagerService.getTodo(id, user);
        if(todoManagerService.isTodoBelongToUsers(todo, user)) {
            resultPath = "common/layout";
            model.addAttribute("todo", todo);
        }
        
        return resultPath;
    }
    
    @PostMapping("/todo/{id}/update")
    public String todoUpdate(@PathVariable Integer id, @ModelAttribute TodoForm form) {
        AppUserModel user = appUserManagerService.getAppUser();
        todoManagerService.updateTodo(form, user);
        return "redirect:/top";
    }
    
    @PostMapping("todo/{id}/delete")
    public String todoDelete(@PathVariable Integer id) {
        AppUserModel user = appUserManagerService.getAppUser();
        todoManagerService.deleteTodo(id, user);
        return "redirect:/top";
    }
    
}
