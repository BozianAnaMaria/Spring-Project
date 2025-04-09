package com.example.lab1.service;

import com.example.lab1.model.TeamMember;
import com.example.lab1.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberService {
    private final TeamMemberRepository teamMemberRepository;

    public List<TeamMember> findAll() {
        return teamMemberRepository.findAll();
    }

    public TeamMember findById(Long id) {
        return teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found"));
    }

    public TeamMember save(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    public void deleteById(Long id) {
        teamMemberRepository.deleteById(id);
    }

    public List<TeamMember> findByProjectId(int projectId) {
        // This would require adding a custom method to your repository
        // return teamMemberRepository.findByProjectId(projectId);
        return teamMemberRepository.findAll().stream()
                .filter(member -> member.getProject() != null && member.getProject().getId() == projectId)
                .toList();
    }
}
