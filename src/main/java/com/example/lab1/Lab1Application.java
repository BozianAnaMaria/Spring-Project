package com.example.lab1;

import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
class Client{
    private String name;
    private String email;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Builder
class TeamMember {
    private String name;
    private String role;
    private int experienceYears;
    private String email;
    private String phoneNumber;
}

@AllArgsConstructor
@NoArgsConstructor (force = true)
@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Component
class Project {
    private String name;
    private String description;
    private int durationInMonths;
    private double budget;
    private final Client client; // constructor injected
    private List<TeamMember> teamMembers = new ArrayList<>();

    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
    }

    // setter injection
    @Autowired
    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }
}

@Configuration
class AppConfig {
    @Bean
    public Client client() {
        return new Client("Client x", "x@gmail.com");
    }

    @Bean
    public TeamMember teamMember1() {
        return TeamMember.builder()
                .name("Ana")
                .role("Engineer")
                .experienceYears(1)
                .build();
    }

    @Bean
    public TeamMember teamMember2() {
        return TeamMember.builder()
                .name("Victor")
                .role("Developer")
                .experienceYears(1)
                .build();
    }
}

@SpringBootApplication
public class Lab1Application {
    public static void main(String[] args) {
        var context = SpringApplication.run(Lab1Application.class, args);

        Client client = context.getBean(Client.class);
        TeamMember member1 = context.getBean("teamMember1", TeamMember.class);
        TeamMember member2 = context.getBean("teamMember2", TeamMember.class);

        Project project = new Project("Website Redesign", "Redesign company web", 6, 15000.0, client, new ArrayList<>());

        project.addTeamMember(member1);
        project.addTeamMember(member2);

        System.out.println(project);
    }
}
