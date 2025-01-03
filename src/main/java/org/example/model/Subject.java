package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "code",nullable = false)
    private String code;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    @Override
    public String toString(){
        return this.code+" - "+this.title+"\n";
    }

}
