package web.controller;

import beans.models.User;
import beans.services.UserAccountService;
import beans.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {

	private static final String HOME_PAGE = "home";
	private static final String REGISTRATION_PAGE = "registration";
	private static final String USERS_PAGE = "users";
	private static final String LOGIN_PAGE = "login";

	private static final String USER_FORM_ATTRIBUTE = "userForm";
	private static final String USERS_ATTRIBUTE = "users";
	private static final String LOGIN_ERROR_MSG_ATTRIBUTE = "loginErrorMsg";
	private static final String LOGOUT_MSG_ATTRIBUTE = "logoutMsg";
	private static final String USER_EMAIL_ATTRIBUTE = "userEmail";
	private static final String BALANCE_ATTRIBUTE = "balance";

	private static final String LOGIN_ERROR_MSG = "Invalid email and password";
	private static final String LOGOUT_MSG = "You have been logged out successfully";

	@Resource
	private UserService userService;
	@Resource
	private UserAccountService userAccountService;

	@RequestMapping(value = "/login", method = GET)
	public String login(@RequestParam(required = false) String error,
						@RequestParam(required = false) String logout, Model model) {
		if (nonNull(error))
			model.addAttribute(LOGIN_ERROR_MSG_ATTRIBUTE, LOGIN_ERROR_MSG);
		if (nonNull(logout))
			model.addAttribute(LOGOUT_MSG_ATTRIBUTE, LOGOUT_MSG);
		return LOGIN_PAGE;
	}

	@RequestMapping(value = "/home", method = GET)
	public String homePage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		model.addAttribute(USER_EMAIL_ATTRIBUTE, userEmail);
		model.addAttribute(BALANCE_ATTRIBUTE, userAccountService.getBalance(userEmail));
		return HOME_PAGE;
	}

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

	@RequestMapping(value = "/refill-account", method = POST)
	public RedirectView refillAccount(@RequestParam String userEmail, @RequestParam double amount) {
		userAccountService.refillAccount(userEmail, amount);
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
