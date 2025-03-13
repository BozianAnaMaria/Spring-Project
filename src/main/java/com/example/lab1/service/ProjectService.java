package com.example.lab1.service;

import com.example.lab1.model.Project;
import com.example.lab1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Get all projects
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    // Get a project by ID
    public Project findById(int id) {
        return projectRepository.findById(id);
    }

    // Save a project (create or update)
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    // Delete a project by ID
    public void deleteById(int id) {
        projectRepository.deleteById(id);
    }
}