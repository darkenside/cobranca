package com.omalotech.cobranca.model;


//import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



//import java.util.Set;

//@Entity
@Document(collection = "user")
public class User {
    private ObjectId id ;
    
   
    private String username;
    
 
    private String password;
   
   
    private String passwordConfirm;
   
  
    private String email;
    
 //   private Set<Role> roles;

 
    //@Generated(value = Generated)
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    //@ManyToMany
    //@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  //  public Set<Role> getRoles() {
  //     return roles;
  // }

//   public void setRoles(Set<Role> roles) {
 //      this.roles = roles;
 //  }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
 } 
