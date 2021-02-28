package jp.co.rngd.ss.todo.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="todo")
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Size(max=1000)
    @Column(name="subject")
    // TODO件名
    private String subject;
    
    @Size(max=10000)
    @Column(name="body", nullable=false)
    // TODO本文
    private String body;
    
    @Column(name="start_date")
    private LocalDateTime start_date;
    
    @Column(name="start_time")
    private LocalDateTime start_time;
    
    @Column(name="end_date")
    private LocalDateTime end_date;
    
    @Column(name="end_time")
    private LocalDateTime end_time;
    
    @Column(name="completed")
    private boolean completed;
    
    @ManyToOne
    private AppUserModel user;
}
