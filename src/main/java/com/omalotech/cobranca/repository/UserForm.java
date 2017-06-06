package com.omalotech.cobranca.repository;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {
	@Size(max=32,min=6, message="O nome não pode ser menor que 6 e maior que 32 caracteres")
    @NotEmpty(message="O nome do usuário é obrigatória")
    private String username;
    
	@Size(max=16,min=6, message="A senha não pode ser menor que 6 e maior que 16 caracteres")
	@NotEmpty(message="A senha é obrigatória")
	private String password;
    
	 @NotEmpty(message="A confirmação de senha é obrigatória")
	 @Size(max=16,min=6, message="A confirmação de senha não pode ser menor que 6 e maior que 16 caracteres")
	 private String passwordConfirm;
    
	 @NotEmpty(message="O email é obrigatório")
	 private String email;
 


    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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


    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

	



   
 } 
