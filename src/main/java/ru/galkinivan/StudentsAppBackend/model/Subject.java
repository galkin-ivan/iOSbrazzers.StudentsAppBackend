package ru.galkinivan.StudentsAppBackend.model;

import javax.persistence.*;

/**
 * Data model for Subjects Entity
 *
 * @author galkinivan
 * @version 1.0
 */

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
