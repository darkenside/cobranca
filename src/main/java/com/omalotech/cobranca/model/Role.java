package com.omalotech.cobranca.model;

import org.bson.types.ObjectId;

//import javax.persistence.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

//@Entity
//@Document(collection = "role")
public class Role {
    private ObjectId id;
    private String name;
    
    @DBRef
    private Set<User> users;

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@ManyToMany(mappedBy = "roles")
    
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
