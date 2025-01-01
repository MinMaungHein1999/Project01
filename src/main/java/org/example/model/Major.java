package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "majors")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "major", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    public int studentCount(){
       return this.students.size();
    }

    @Override
    public String toString(){
        return " Major : "+this.name;
    }
}
