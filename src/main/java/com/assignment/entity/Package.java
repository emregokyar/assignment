package com.assignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "packages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    @JsonBackReference
    private Author authorId;

    @OneToMany(targetEntity = Version.class, mappedBy = "packageId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Version> versions;

    public void addVersion(Version version) {
        if (versions == null) {
            versions = new ArrayList<>();
        }
        versions.add(version);
    }
}
