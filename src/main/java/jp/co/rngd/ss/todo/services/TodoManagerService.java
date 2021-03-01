package jp.co.rngd.ss.todo.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
    
    /***
     * フォームデータをモデルに設定します
     * @param form {TodoForm}
     * @param todoModel {TodoModel}
     * @param user {AppUserModel}
     */
    private void setFormToModel(TodoForm form, TodoModel todoModel, AppUserModel user) {
        todoModel.setSubject(form.getSubject());
        todoModel.setBody(form.getBody());
        todoModel.setUser(user);
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
        todoModel.setCompleted(form.getCompleted() != null);
    }
    
    /***
     * TODOを作成します
     * @param form {TodoForm}
     * @param user {AppUserModel}
     */
    public void createTodo(TodoForm form, AppUserModel user) {
        var todoModel = new TodoModel();
        this.setFormToModel(form, todoModel, user);
        todoRep.saveAndFlush(todoModel);
    }
    
    /***
     * TODOを更新します
     * @param form {TodoForm}
     * @param user `{AppUserModel}
     */
    public void updateTodo(TodoForm form, AppUserModel user) {
        try {
            Integer pk = Integer.parseInt(form.getTodoId());
            TodoModel todoModel = todoRep.getOne(pk);
            if(this.isTodoBelongToUsers(todoModel, user)) {
                this.setFormToModel(form, todoModel, user);
                todoRep.save(todoModel);
            }
            
        } catch(EntityNotFoundException e) {}
    }
    
    /***
     * TODOを削除します
     * @param id {Integer}
     * @param user {AppUserModel}
     */
    public void deleteTodo(Integer id, AppUserModel user) {
        if(this.isTodoBelongToUsers(id, user)) {
            todoRep.deleteById(id);
        }
    }
    
    
    /***
     * 対象ユーザのTODO一覧を取得します
     * @param user {AppUserModel}
     * @return todoList {List<TodoModel>}
     */
    public List<TodoModel> getTodoList(AppUserModel user) {
        List<TodoModel> todoList = todoRep.findByUser(user);
        return todoList;
    }
    
    /***
     * IDを元にTODOを取得します
     * @param todoId {Integer}
     * @param user {AppUserModel}
     * @return
     */
    public TodoModel getTodo(Integer todoId, AppUserModel user) {
        TodoModel todo = null;
        try {
            todo = todoRep.getOne(todoId);
            
        } catch (EntityNotFoundException e) {}
        return todo;
    }
    
    /***
     * 対象のTODOがユーザのデータかどうかをチェックします
     * @param todoId {Integer}
     * @param user {AppUserModel}
     * @return result {boolean}
     */
    public boolean isTodoBelongToUsers(Integer todoId, AppUserModel user) {
        boolean result = false;
        try {
            TodoModel todo = todoRep.getOne(todoId);
            
            if(todo.getUser().equals(user)) {
                result = true;
            }
        } catch (EntityNotFoundException e) {}
        return result;
    }
    /***
     * 対象のTODOがユーザのデータかどうかをチェックします
     * @param todo {TodoModel} 
     * @param user {AppUserModel}
     * @return result {boolean}
     */
    public boolean isTodoBelongToUsers(TodoModel todo, AppUserModel user) {
        boolean result = false;
        if(todo.getUser().equals(user)) {
            result = true;
        }
        return result;
    }
}
