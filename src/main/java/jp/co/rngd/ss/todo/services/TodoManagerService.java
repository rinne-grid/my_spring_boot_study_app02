package jp.co.rngd.ss.todo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.rngd.ss.todo.forms.TodoForm;
import jp.co.rngd.ss.todo.models.AppUserModel;
import jp.co.rngd.ss.todo.models.TodoModel;
import jp.co.rngd.ss.todo.models.TodoModelRepository;
import jp.co.rngd.ss.todo.utils.RngdUtility;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoManagerService {
    private final TodoModelRepository todoRep;
    
    
    public void createTodo(TodoForm form, AppUserModel user) {
        var todoModel = new TodoModel();
        todoModel.setSubject(form.getSubject());
        todoModel.setBody(form.getBody());
        todoModel.setUser(user);
        System.out.println("startTimeMinutes:"+form.getStart_time_minutes());
        LocalDateTime startDate = RngdUtility.getLocalDateFormat(
                form.getStart_date(), 
                form.getStart_time_hour(), 
                form.getStart_time_minutes(),
                "-"
        );
        
        todoModel.setStart_date(startDate);
        todoModel.setStart_time(startDate);
        
        // 終了日
        if(form.getEnd_date() != null && !form.getEnd_date().equals("")) {
            LocalDateTime endDate = RngdUtility.getLocalDateFormat(
                    form.getEnd_date(), 
                    form.getEnd_time_hour(), 
                    form.getEnd_time_minutes(),
                    "-"
            );
            todoModel.setEnd_date(endDate);
            todoModel.setEnd_time(endDate);
        }
        System.out.println(form.getCompleted());
        todoModel.setCompleted(!form.getCompleted().equals(null));
        
        todoRep.saveAndFlush(todoModel);
    }
    
    public List<TodoModel> getTodoList(AppUserModel user) {
        List<TodoModel> todoList = todoRep.findByUser(user);
        return todoList;
    }
}
