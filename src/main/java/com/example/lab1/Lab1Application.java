package com.example.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab1Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Lab1Application.class, args);

        Client client = context.getBean(Client.class);
        TeamMember member1 = context.getBean("teamMember1", TeamMember.class);
        TeamMember member2 = context.getBean("teamMember2", TeamMember.class);
        List<TeamMember> teamMembers = new ArrayList<>();
        teamMembers.add(member1);
        teamMembers.add(member2);

        Project project = context.getBean(Project.class);
        project.setName("Project 1");
        project.setDescription("Project Description");
        project.setDurationInMonths(6);
        project.setTeamMembers(teamMembers);

        //project.addTeamMember(member1);
        //project.addTeamMember(member2);

        System.out.println(project.getTeamMembers());

        System.out.println("-----------------------------------------------------------------------");

        System.out.println(project);

        CustomComponent component = context.getBean(CustomComponent.class);
        String message = component.getMessage();
        System.out.println(component);
        System.out.println(message);
    }
}
