package com.example.lab1.config;

import com.example.lab1.model.Client;
import com.example.lab1.model.Project;
import com.example.lab1.model.TeamMember;
import com.example.lab1.repository.ClientRepository;
import com.example.lab1.repository.ProjectRepository;
import com.example.lab1.repository.TeamMemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository,
                                      ProjectRepository projectRepository,
                                      TeamMemberRepository teamMemberRepository) {
        return args -> {
            // Skip if any clients already exist
            if (clientRepository.count() > 0) {
                System.out.println("Initial data already exists, skipping initialization.");
                return;
            }

            // Create client
            Client client = new Client();
            client.setName("ABC Corporation");
            client.setEmail("contact@abccorp.com");
            clientRepository.save(client);

            // Create project
            Project project = new Project();
            project.setName("Website Redesign");
            project.setDescription("Redesign the company website with modern UI/UX principles");
            project.setDurationInMonths(4);
            project.setBudget(25000.0);
            project.setClient(client);

            // Create team members
            TeamMember member1 = new TeamMember();
            member1.setName("Alice Johnson");
            member1.setEmail("alice.johnson@company.com");
            member1.setPhoneNumber("123-456-7890");
            member1.setRole("Engineer");
            member1.setExperienceYears(3);
            member1.setProject(project);

            TeamMember member2 = new TeamMember();
            member2.setName("Bob Smith");
            member2.setEmail("bob.smith@company.com");
            member2.setPhoneNumber("987-654-3210");
            member2.setRole("Developer");
            member2.setExperienceYears(5);
            member2.setProject(project);

            // Link members to project
            project.setTeamMembers(List.of(member1, member2));

            // Save project and members
            projectRepository.save(project);

            System.out.println("Initial data loaded successfully!");
        };
    }
}
