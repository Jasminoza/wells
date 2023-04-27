package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Well implements org.example.model.Entity{
    private Long id;
    private String name;
}
