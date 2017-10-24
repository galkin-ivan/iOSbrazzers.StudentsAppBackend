package ru.galkinivan.StudentsAppBackend.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Model representing university entity
 *
 *
 * @author Galkin Ivan
 * @version 1.0
 *
 */

@Entity
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "universities_faculties", joinColumns = @JoinColumn(name = "university_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<Faculty> faculties;



    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}






