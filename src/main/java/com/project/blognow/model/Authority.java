package com.project.blognow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Authority implements Serializable {
    @Id
    @Column(length = 16)
    private String name;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority1 = (Authority) o;
        return name.equals(authority1.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
