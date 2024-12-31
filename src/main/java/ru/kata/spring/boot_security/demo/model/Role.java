package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public  class Role extends AbstractEntity<Integer> implements GrantedAuthority {
    private static final long serialVersionUID = 7217778059836250424L;

    @Column(unique = true)
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id) {
        this.setId(id);
    }

    public String getName() {
        return name.substring(5);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Role [id = %d; name = %s;]", this.getId(), name);
    }
}
