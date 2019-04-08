package web.controller;

import beans.models.User;
import beans.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import web.form.UserForm;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {

	private static final String HOME_PAGE = "home";
	private static final String REGISTRATION_PAGE = "registration";
	private static final String USERS_PAGE = "users";

	private static final String USER_FORM_ATTRIBUTE = "userForm";
	private static final String USERS_ATTRIBUTE = "users";

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
		User user = new User(userForm.getEmail(), userForm.getName(), userForm.getBirthday(), userForm.getPassword());
		userService.register(user);
		return new RedirectView(HOME_PAGE);
	}

	@RequestMapping(value = "/users", method = GET)
	public String usersPage() {
		return USERS_PAGE;
	}

	@RequestMapping(value = "/users-by-name", method = GET)
	public String getUsersByName(@RequestParam String name, Model model) {
		List<User> users = userService.getUsersByName(name);
		model.addAttribute(USERS_ATTRIBUTE, users);
		return USERS_PAGE;
	}

	@RequestMapping(value = "/user-by-email", method = GET)
	public ModelAndView getUserByEmail(@RequestParam String email) {
		List<User> users = ofNullable(userService.getUserByEmail(email))
				.map(Collections::singletonList)
				.orElseGet(Collections::emptyList);
		return new ModelAndView(USERS_PAGE, USERS_ATTRIBUTE, users);
	}

}
