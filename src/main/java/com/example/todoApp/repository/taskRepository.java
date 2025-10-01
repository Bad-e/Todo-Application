package com.example.todoApp.repository;

import com.example.todoApp.model.taskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface taskRepository extends JpaRepository<taskModel, Long> {

}
