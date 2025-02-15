package com.example.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Clasa Project - reprezintă un proiect
class Project {
    private String name;
    private String description;
    private int durationInMonths;
    private double budget;
    private String client;
    private List<TeamMember> teamMembers;

    public Project() {
        this.name = "Unknown";
        this.description = "No description available";
        this.durationInMonths = 0;
        this.budget = 0.0;
        this.client = "Unknown";
    }

    public Project(String name, String description, int durationInMonths, double budget, String client) {
        this.name = name;
        this.description = description;
        this.durationInMonths = durationInMonths;
        this.budget = budget;
        this.client = client;
        this.teamMembers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
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
class TeamMember {
    private String name;
    private String role;
    private int experienceYears;
    private String email;
    private String phoneNumber;

    public TeamMember() {}

    public TeamMember(String name, String role, int experienceYears, String email, String phoneNumber) {
        this.name = name;
        this.role = role;
        this.experienceYears = experienceYears;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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
        Project project = new Project("Website Redesign", "Redesign al site-ului companiei", 6, 15000.0, "Client X");
        TeamMember member1 = new TeamMember("Ana", "QA", 3, "ana@example.com", "+37312345678");
        TeamMember member2 = new TeamMember("Victor", "Developer", 3, "victor@example.com", "+37312345678");

        project.addTeamMember(member1);
        project.addTeamMember(member2);

        System.out.println(project);
    }
}
