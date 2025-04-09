package com.example.lab1.service;

import com.example.lab1.model.Client;
import com.example.lab1.model.Project;
import com.example.lab1.model.TeamMember;
import com.example.lab1.repository.ProjectRepository;
import com.example.lab1.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project save(Project project) {
        if (project.getClient() == null) {
            throw new IllegalArgumentException("Client cannot be null for a project");
        }

        if (project.getClient() != null && project.getClient().getEmail() != null) {
            Optional<Client> existingClient = clientRepository.findByEmail(project.getClient().getEmail());
            if (existingClient.isPresent()) {
                project.setClient(existingClient.get());
            } else {
                // Save the new client first
                Client newClient = clientRepository.save(project.getClient());
                project.setClient(newClient);
            }
        }

        // Handle team members as before
        if (project.getTeamMembers() != null) {
            for (TeamMember member : project.getTeamMembers()) {
                member.setProject(project);
            }
        }

        return projectRepository.save(project);
    }

    @Transactional
    public Project updateProjectWithDetails(int id, Project projectUpdates) {
        // Fetch the project with all its related entities
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project with ID " + id + " not found"));

        // Update basic fields
        existing.setName(projectUpdates.getName());
        existing.setDescription(projectUpdates.getDescription());
        existing.setDurationInMonths(projectUpdates.getDurationInMonths());
        existing.setBudget(projectUpdates.getBudget());

        // Handle client updates if provided
        if (projectUpdates.getClient() != null) {
            Client updatedClient = projectUpdates.getClient();
            if (updatedClient.getEmail() != null) {
                Optional<Client> existingClient = clientRepository.findByEmail(updatedClient.getEmail());
                if (existingClient.isPresent()) {
                    existing.setClient(existingClient.get());
                } else {
                    // Save the new client
                    Client savedClient = clientRepository.save(updatedClient);
                    existing.setClient(savedClient);
                }
            }
        }

        // Handle team members - explicitly trigger loading of the collection
        List<TeamMember> existingMembers = new ArrayList<>(existing.getTeamMembers());

        // Now it's safe to clear and update
        if (projectUpdates.getTeamMembers() != null) {
            existing.getTeamMembers().clear();

            for (TeamMember member : projectUpdates.getTeamMembers()) {
                // Generate email if not provided
                if (member.getEmail() == null) {
                    member.setEmail(member.getName().toLowerCase().replace(" ", ".") + "@company.com");
                }

                // Default phone number if not provided
                if (member.getPhoneNumber() == null) {
                    member.setPhoneNumber("000-000-0000");
                }

                member.setProject(existing);
                existing.addTeamMember(member);
            }
        }

        return projectRepository.save(existing);
    }

    public void deleteById(int id) {
        projectRepository.deleteById(id);
    }
}