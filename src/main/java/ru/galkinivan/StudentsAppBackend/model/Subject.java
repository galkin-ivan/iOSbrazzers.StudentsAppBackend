package ru.galkinivan.StudentsAppBackend.model;

import javax.persistence.*;
import java.util.Set;

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


    @ManyToMany
    @JoinTable(name = "groups_subjects", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
