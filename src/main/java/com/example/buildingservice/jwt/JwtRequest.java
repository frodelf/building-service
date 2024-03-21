package com.example.buildingservice.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}