package com.example.lab1.controller;

import com.example.lab1.model.Client;
import com.example.lab1.model.Project;
import com.example.lab1.repository.ClientRepository;
import com.example.lab1.service.ProjectService;
import com.example.lab1.model.TeamMember;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects") // Base URL for project-related endpoints
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ClientRepository clientRepository;

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
        // Existing client handling
        if (project.getClient() == null) {
            throw new RuntimeException("Client is required for a project");
        }

        if (project.getTeamMembers() != null) {
            for (TeamMember member : project.getTeamMembers()) {
                member.setProject(project);
                if (member.getEmail() == null) {
                    member.setEmail(member.getName().toLowerCase().replace(" ", ".") + "@company.com");
                }
                if (member.getPhoneNumber() == null) {
                    member.setPhoneNumber("000-000-0000");
                }
            }
        }

        return projectService.save(project);
    }

    // PUT: Update an existing project
    @PutMapping("/{id}")
    public Project update(@PathVariable int id, @RequestBody Project project) {
        return projectService.updateProjectWithDetails(id, project);
    }


    // PATCH: Partially update an existing project
    @PatchMapping("/{id}")
    public Project patch(@PathVariable int id, @RequestBody Project projectUpdates) {
        Project existingProject = projectService.findById(id);

        if (existingProject == null) {
            throw new RuntimeException("Project with ID " + id + " not found");
        }

        // Handle team members updates only if the list is not empty
        if (projectUpdates.getTeamMembers() != null && !projectUpdates.getTeamMembers().isEmpty()) {
            // Clear existing team members to prevent duplicates
            existingProject.getTeamMembers().clear();

            // Add each team member from the request to the existing project
            for (TeamMember newMember : projectUpdates.getTeamMembers()) {
                newMember.setProject(existingProject);

                // Generate email if not provided
                if (newMember.getEmail() == null) {
                    newMember.setEmail(newMember.getName().toLowerCase().replace(" ", ".") + "@company.com");
                }

                // Default phone number if not provided
                if (newMember.getPhoneNumber() == null) {
                    newMember.setPhoneNumber("000-000-0000");
                }

                existingProject.addTeamMember(newMember);
            }
        }

        return projectService.save(existingProject);
    }

    // DELETE: Delete a project by ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        projectService.deleteById(id);
    }
}