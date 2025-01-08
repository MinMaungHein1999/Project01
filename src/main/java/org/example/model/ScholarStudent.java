package org.example.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("Scholar")
public class ScholarStudent extends Student{
    private double scholarshipAmount;
    private int studyHours;
    private double scholarshipPercentage;

    public ScholarStudent(Student student, double scholarshipAmount, int studyHours, double scholarshipPercentage){
        super(student);
        this.scholarshipAmount = scholarshipAmount;
        this.studyHours = studyHours;
        this.scholarshipPercentage = scholarshipPercentage;
    }

}
