package com.example.tpo_project_03_part_2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;
@Configuration
public interface DisplayProfile {
    void display(Entry entry);

}
