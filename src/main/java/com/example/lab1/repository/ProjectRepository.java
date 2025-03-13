package com.example.lab1.repository;

import com.example.lab1.model.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectRepository {
    private final Map<Integer, Project> projects = new HashMap<>(); // Simulated database
    private int nextId = 1; // Counter for generating unique IDs

    // Find all projects
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    // Find a project by ID
    public Project findById(int id) {
        return projects.get(id);
    }

    // Save a project (create or update)
    public Project save(Project project) {
        if (project.getId() == 0) { // New project (no ID assigned)
            project.setId(nextId++); // Assign a new ID
        }
        projects.put(project.getId(), project); // Save or update the project
        return project;
    }

    // Delete a project by ID
    public void deleteById(int id) {
        projects.remove(id);
    }
}