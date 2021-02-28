package jp.co.rngd.ss.todo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoModelRepository extends JpaRepository<TodoModel, Integer> {
    List<TodoModel> findByUser(AppUserModel user);
    
}
