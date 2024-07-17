package com.example.lab3;

import com.example.lab3.model.Task;
import com.example.lab3.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public Task task() {
        return new Task();
    }
}
