package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String code;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    @Override
    public String toString(){
        return this.code+" - "+this.title+"\n";
    }

}
