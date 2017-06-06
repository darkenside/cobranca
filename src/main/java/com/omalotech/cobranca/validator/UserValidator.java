package com.omalotech.cobranca.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omalotech.cobranca.model.User;
import com.omalotech.cobranca.service.UserService;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object o, Errors errors, RedirectAttributes redirect) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "com.omalotech.nome.obrig");
		if ((user.getUsername().length() < 4 || user.getUsername().length() > 32) && !user.getUsername().equals("")) {

			errors.rejectValue("username", "com.omalotech.nome.tamanho");

		}
		if (userService.findByUsername(user.getUsername()) != null) {

			errors.rejectValue("username", "com.omalotech.usuario.cadastrado");

		}

		if (userService.findByEmail(user.getEmail()) != null) {

			errors.rejectValue("email", "com.omalotech.email.cadastrado");

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "com.omalotech.email.obrig");
		if ((user.getEmail().length() < 6 && user.getEmail().length() >= 1) || user.getEmail().length() > 32) {

			errors.rejectValue("email", "com.omalotech.email.tamanho");

		}

		if (user.getEmail().contains("@") == false && user.getEmail().length() >= 5) {

			errors.rejectValue("email", "com.omalotech.email.invalido");

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "com.omalotech.senha.obrig");
		if ((user.getPassword().length() < 5 || user.getPassword().length() > 32) && !user.getPassword().equals("")) {

			errors.rejectValue("password", "com.omalotech.senha.tamanho");

		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "com.omalotech.confirS.obrig");
		if (!user.getPasswordConfirm().equals(user.getPassword()) && !user.getPasswordConfirm().equals("")
				&& !user.getPassword().equals("")) {

			errors.rejectValue("passwordConfirm", "com.omalotech.senhas.conferem");

		}
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}
}
