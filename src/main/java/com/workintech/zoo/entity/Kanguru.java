package com.workintech.zoo.entity;

import lombok.Data;

@Data
public class Kanguru {
    private  int id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private Boolean isAggressive;
}
