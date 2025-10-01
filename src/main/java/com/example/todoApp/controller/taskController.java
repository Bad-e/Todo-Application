package com.example.todoApp.controller;

import com.example.todoApp.model.taskModel;
import com.example.todoApp.repository.taskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class taskController {

    private final taskRepository taskRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add (@RequestParam String title){
        taskModel newTask = taskModel.builder()
                .title(title)
                .completed(false)
                .build();
        taskRepository.save(newTask);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id){
        taskModel existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        existingTask.setCompleted(true);
        taskRepository.save(existingTask);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        taskModel  existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        taskRepository.delete(existingTask);
        return "redirect:/";
    }
}
