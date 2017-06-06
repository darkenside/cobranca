package com.omalotech.cobranca.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omalotech.cobranca.MessageResource;
import com.omalotech.cobranca.model.User;
import com.omalotech.cobranca.repository.UserForm;
import com.omalotech.cobranca.service.SecurityService;
import com.omalotech.cobranca.service.UserService;
import com.omalotech.cobranca.validator.UserValidator;

@Controller
public class UserController {
    
	
	@Autowired
	MessageResource messag;
	
	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Validated UserForm uform, Errors erros, RedirectAttributes redirect) {
        User user = new User();
    	user.setUsername(uform.getUsername());
    	user.setPassword(uform.getPassword());
    	user.setPasswordConfirm(uform.getPasswordConfirm());
    	user.setEmail(uform.getEmail());
    	userValidator.validate(user, erros,redirect);

        if (erros.hasErrors()) {
            return "registration";
        }

        userService.save(user);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/titulos";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model,String error, String logout) {
        if (error != null)
        	model.addAttribute("mensagem", messag.mensagemProperties("com.omalotech.login.erro"));
        	
        if (logout != null){
         //   model.addAttribute("message", "");
        }
        
        return "/login";
    } 
    
   

    
}
