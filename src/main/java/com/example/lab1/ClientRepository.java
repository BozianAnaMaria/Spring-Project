package com.example.lab1;

import org.springframework.stereotype.Repository;

    @Repository
    public class ClientRepository {
        public Client findClientById(int id) {
            // Simulate fetching a client from a database
            return new Client("Client X", "x@gmail.com");
        }
    }

