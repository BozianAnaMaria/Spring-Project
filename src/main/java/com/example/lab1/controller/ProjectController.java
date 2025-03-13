package com.example.lab1.controller;

import com.example.lab1.model.Project;
import com.example.lab1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects") // Base URL for project-related endpoints
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // GET: Get all projects
    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    // GET: Get a project by ID
    @GetMapping("/{id}")
    public Project findById(@PathVariable int id) {
        return projectService.findById(id);
    }

    // POST: Create a new project
    @PostMapping
    public Project save(@RequestBody Project project) {
        return projectService.save(project);
    }

    // PUT: Update an existing project
    @PutMapping("/{id}")
    public Project update(@PathVariable int id, @RequestBody Project project) {
        project.setId(id); // Ensure the ID matches the path variable
        return projectService.save(project);
    }

    // PATCH: Partially update an existing project
    @PatchMapping("/{id}")
    public Project patch(@PathVariable int id, @RequestBody Project projectUpdates) {
        Project existingProject = projectService.findById(id);

        // Apply partial updates
        if (projectUpdates.getName() != null) {
            existingProject.setName(projectUpdates.getName());
        }
        if (projectUpdates.getDescription() != null) {
            existingProject.setDescription(projectUpdates.getDescription());
        }
        if (projectUpdates.getDurationInMonths() != 0) {
            existingProject.setDurationInMonths(projectUpdates.getDurationInMonths());
        }
        if (projectUpdates.getBudget() != 0) {
            existingProject.setBudget(projectUpdates.getBudget());
        }

        return projectService.save(existingProject);
    }

    // DELETE: Delete a project by ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        projectService.deleteById(id);
    }
}