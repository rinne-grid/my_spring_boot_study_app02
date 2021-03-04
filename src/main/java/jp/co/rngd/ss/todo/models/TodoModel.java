package jp.co.rngd.ss.todo.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="todo")
@Table
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
    private LocalDateTime startDate;
    
    @Column(name="start_time")
    private LocalDateTime startTime;
    
    @Column(name="end_date")
    private LocalDateTime endDate;
    
    @Column(name="end_time")
    private LocalDateTime endTime;
    
    @Column(name="completed")
    private boolean completed;
    
    @ManyToOne
    private AppUserModel user;
}
