package web.controller;

import beans.models.User;
import beans.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import web.form.UserForm;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {

	private static final String HOME_PAGE = "home";
	private static final String REGISTRATION_PAGE = "registration";

	private static final String USER_FORM_ATTRIBUTE = "userForm";

	@Resource
	private UserService userService;

	@RequestMapping(value = "/register", method = GET)
	public String registrationPage(Model model) {
		UserForm userForm = new UserForm();
		model.addAttribute(USER_FORM_ATTRIBUTE, userForm);
		return REGISTRATION_PAGE;
	}

	@RequestMapping(value = "/register", method = POST)
	public RedirectView registerUser(UserForm userForm) {
		User user = new User(userForm.getEmail(), userForm.getName(), userForm.getBirthday());
		userService.register(user);
		return new RedirectView(HOME_PAGE);
	}

}
