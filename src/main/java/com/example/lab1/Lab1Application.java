package com.example.lab1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
class Project {
    private String name;
    private String description;
    private int durationInMonths;
    private double budget;
    private String client;
    private List<TeamMember> teamMembers = new ArrayList<>();

    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", durationInMonths=" + durationInMonths +
                ", budget=" + budget +
                ", client='" + client + '\'' +
                ", teamMembers=" + teamMembers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return durationInMonths == project.durationInMonths &&
                Double.compare(project.budget, budget) == 0 &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(client, project.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, durationInMonths, budget, client);
    }
}

// Clasa TeamMember - reprezintă un membru al echipei
@AllArgsConstructor
@NoArgsConstructor
@Data
class TeamMember {
    private String name;
    private String role;
    private int experienceYears;
    private String email;
    private String phoneNumber;

    @Override
    public String toString() {
        return "TeamMember{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", experienceYears=" + experienceYears +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return experienceYears == that.experienceYears &&
                Objects.equals(name, that.name) &&
                Objects.equals(role, that.role) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, experienceYears, email, phoneNumber);
    }
}

// Clasa principală cu @SpringBootApplication
@SpringBootApplication
public class Lab1Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);

        // Exemplu de utilizare a claselor
        Project project = new Project("Website Redesign", "Redesign al site-ului companiei", 6, 15000.0, "Client X", new ArrayList<>());
        TeamMember member1 = new TeamMember("Ana", "QA", 3, "ana@example.com", "+37312345678");
        TeamMember member2 = new TeamMember("Victor", "Developer", 3, "victor@example.com", "+37312345678");

        project.addTeamMember(member1);
        project.addTeamMember(member2);

        System.out.println(project);
    }
}
