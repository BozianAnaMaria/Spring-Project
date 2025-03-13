package com.example.lab1;

import com.example.lab1.model.Client;
import com.example.lab1.model.Project;
import com.example.lab1.model.TeamMember;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab1Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Lab1Application.class, args);

        // Retrieve beans from the Spring context
        Client client = context.getBean(Client.class);
        TeamMember member1 = context.getBean("teamMember1", TeamMember.class);
        TeamMember member2 = context.getBean("teamMember2", TeamMember.class);

        // Create a list of team members
        List<TeamMember> teamMembers = new ArrayList<>();
        teamMembers.add(member1);
        teamMembers.add(member2);

        // Retrieve the Project bean and set its properties
        Project project = context.getBean(Project.class);
        project.setId(1); // Set an ID for the project
        project.setName("Project 1");
        project.setDescription("Project Description");
        project.setDurationInMonths(6);
        project.setBudget(10000.0);
        project.setTeamMembers(teamMembers); // Inject the team members

        // Print the team members and project details
        System.out.println("Team Members:");
        project.getTeamMembers().forEach(member -> System.out.println(member));

        System.out.println("-----------------------------------------------------------------------");

        System.out.println("Project Details:");
        System.out.println(project);
    }
}