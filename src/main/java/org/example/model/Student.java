package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "student_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Student(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.major = student.getMajor();
        this.gender = student.getGender();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.address = student.getAddress();
    }

    public Student(int id, String name, Major major, String gender, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.gender =gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @Column(name = "name", nullable= false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "major_id" )
    private Major major;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy ="student", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments;



    @Override
    public String toString(){
        return "Id :"+ this.id+", Name :"+this.name+", Email :"+this.email;
    }
}
