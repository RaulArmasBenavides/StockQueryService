package com.maestria.springmvcstock.repository;

import com.maestria.springmvcstock.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}