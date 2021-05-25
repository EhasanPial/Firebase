package com.example.fire;

public class Students {
    private String name;
    private String roll;
    private String cgpa;


    public Students() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public Students(String name, String roll, String cgpa) {


        this.name = name;
        this.roll = roll;
        this.cgpa = cgpa;
    }


}







