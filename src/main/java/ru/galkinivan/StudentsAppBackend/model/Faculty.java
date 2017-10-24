package ru.galkinivan.StudentsAppBackend.model;


import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "faculties_groups", joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

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

    @Transactional
    public Set<Group> getGroups() {
        Hibernate.initialize(groups);
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
