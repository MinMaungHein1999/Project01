package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name="enrollments",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns ={ @JoinColumn(name = "subject_id") }
    )
    private Set<Subject> subjects = new HashSet<>();

    @Override
    public String toString(){
        return "Id :"+ this.id+", Name :"+this.name+", Email :"+this.email;
    }
}
