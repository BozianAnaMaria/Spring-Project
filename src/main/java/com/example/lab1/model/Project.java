package com.example.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Project {
    private int id;
    private String name;
    private String description;
    private int durationInMonths;
    private double budget;
    private Client client;
    private List<TeamMember> teamMembers = new ArrayList<>();

    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
    }

    @Autowired
    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }
}