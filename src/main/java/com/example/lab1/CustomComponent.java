package com.example.lab1;

import org.springframework.stereotype.Component;

@Component
public class CustomComponent {
    public String getMessage() {
        return "Hello from CustomComponent!";
    }
}
