package jp.co.rngd.ss.todo.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserModelRepository extends JpaRepository<AppUserModel, Integer> {
    AppUserModel findByUsername(String username);
}
