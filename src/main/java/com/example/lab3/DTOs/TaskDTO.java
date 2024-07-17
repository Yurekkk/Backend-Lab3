package com.example.lab3.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String content;
}
