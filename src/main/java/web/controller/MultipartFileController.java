package web.controller;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.EventService;
import beans.services.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MultipartFileController {

	private static final String REDIRECT_TO_HOME_PAGE = "redirect:/home";

	@Resource
	private UserService userService;
	@Resource
	private EventService eventService;
	@Resource
	private AuditoriumService auditoriumService;

	@RequestMapping(value = "/upload-users", method = POST)
	public String uploadUsersFromJSON(@RequestParam MultipartFile file) throws IOException {
		if (isFalse(file.isEmpty())) {
			byte[] bytes = file.getBytes();
			String json = new String(bytes, StandardCharsets.UTF_8);
			Type listType = new TypeToken<List<User>>() {}.getType();
			List<User> users = new Gson().fromJson(json, listType);
			users.forEach(user -> userService.register(user));
		}
		return REDIRECT_TO_HOME_PAGE;
	}

	@RequestMapping(value = "/upload-events", method = POST)
	public String uploadEventsFromJSON(@RequestParam MultipartFile file) throws IOException {
		if (isFalse(file.isEmpty())) {
			byte[] bytes = file.getBytes();
			String json = new String(bytes, StandardCharsets.UTF_8);
			Type listType = new TypeToken<List<Event>>() {}.getType();
			List<Event> events = new Gson().fromJson(json, listType);
			events.forEach(event -> {
				String auditoriumName = event.getAuditorium().getName();
				Auditorium auditorium = auditoriumService.getByName(auditoriumName);
				event.setAuditorium(auditorium);
				eventService.create(event);
			});
		}
		return REDIRECT_TO_HOME_PAGE;
	}

}
