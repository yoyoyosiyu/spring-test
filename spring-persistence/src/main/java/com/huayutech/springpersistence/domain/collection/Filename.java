package com.huayutech.springpersistence.domain.collection;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class Filename implements Serializable {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String extension;


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Filename filename = ((Filename) obj);

        if (!filename.getName().equals(this.getName())) return false;
        if (!filename.getExtension().equals(this.getExtension())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();

        return 31*result + extension.hashCode();
    }

    @Override
    public String toString() {
        return name+"."+extension;
    }
}
