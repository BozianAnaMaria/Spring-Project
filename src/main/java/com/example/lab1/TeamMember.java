package com.example.lab1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class TeamMember {
    private String name;
    private String role;
    private int experienceYears;
    private String email;
    private String phoneNumber;
}
