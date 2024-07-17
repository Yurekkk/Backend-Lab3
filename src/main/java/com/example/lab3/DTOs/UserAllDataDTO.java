package com.example.lab3.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAllDataDTO {
    private Long id;
    private String name;
    private String login;
    private String password;
}
