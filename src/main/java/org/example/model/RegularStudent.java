package org.example.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Regular")
@AllArgsConstructor
@NoArgsConstructor
public class RegularStudent extends Student{
    private String status;
    public RegularStudent(Student student, String status){
        super(student);
        this.status = status;
    }
}
