package jp.co.rngd.ss.todo.forms;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoForm {
    @NotBlank
    private String subject;
    
    @NotBlank
    private String body;
    
    private String start_date;
    private String start_time_hour;
    private String start_time_minutes;
    private String end_date;
    private String end_time_hour;
    private String end_time_minutes;
    private String completed;
}
