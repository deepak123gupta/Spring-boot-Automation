package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double income;
    private String address;
    private String dob;
    private Boolean isIndian;

    // getters & setters
    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public Double getIncome() { return this.income; }
    public void setIncome(Double income) { this.income = income; }
    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }
    public String getDob() { return this.dob; }
    public void setDob(String dob) { this.dob = dob; }
    public Boolean getIsIndian() { return this.isIndian; }
    public void setIsIndian(Boolean isIndian) { this.isIndian = isIndian; }
}
