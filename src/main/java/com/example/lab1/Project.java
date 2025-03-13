package com.example.lab1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@RequiredArgsConstructor
@Component
class Project {
    private String name;
    private String description;
    private int durationInMonths;
    private double budget;
    private final Client client; // constructor injected
    private List<TeamMember> teamMembers = new ArrayList<>();
    private final CustomComponent customComponent; // constructor injection

    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
    }

    // setter injection
    @Autowired
    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
