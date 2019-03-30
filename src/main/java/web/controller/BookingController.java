package web.controller;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.form.BookingForm;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class BookingController {

	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";

	private static final String HOME_PAGE = "home";
	private static final String BOOKING_PAGE = "booking";
	private static final String REDIRECT_TO_HOME_PAGE = "redirect:/home";

	private static final String EVENTS_ATTRIBUTE = "events";
	private static final String BOOKING_FORM_ATTRIBUTE = "bookingForm";
	private static final String SEATS_NUMBER_ATTRIBUTE = "seatsNumber";
	private static final String VIP_SEATS_ATTRIBUTE = "vipSeats";

	@Resource
	private BookingService bookingService;
	@Resource
	private EventService eventService;
	@Resource
	private AuditoriumService auditoriumService;
	@Resource
	private UserService userService;

	@RequestMapping(value = "/home", method = GET)
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView(HOME_PAGE);
		modelAndView.addObject(EVENTS_ATTRIBUTE, eventService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/booking", method = GET)
	public String bookingPage(@RequestParam String eventName,
							  @RequestParam @DateTimeFormat(pattern = DATE_TIME_PATTERN) LocalDateTime eventDateTime,
							  @RequestParam String auditoriumName, Model model) {
		BookingForm bookingForm = new BookingForm();
		bookingForm.setEventName(eventName);
		bookingForm.setEventDateTime(eventDateTime);
		bookingForm.setAuditoriumName(auditoriumName);
		model.addAttribute(BOOKING_FORM_ATTRIBUTE, bookingForm);
		model.addAttribute(SEATS_NUMBER_ATTRIBUTE, auditoriumService.getSeatsNumber(auditoriumName));
		model.addAttribute(VIP_SEATS_ATTRIBUTE, auditoriumService.getVipSeats(auditoriumName));
		return BOOKING_PAGE;
	}

	@RequestMapping(value = "/booking", method = POST)
	public String bookTicket(@ModelAttribute BookingForm bookingForm) {
		String eventName = bookingForm.getEventName();
		String auditoriumName = bookingForm.getAuditoriumName();
		Auditorium auditorium = auditoriumService.getByName(auditoriumName);
		LocalDateTime dateTime = bookingForm.getEventDateTime();
		Event event = eventService.getEvent(eventName, auditorium, dateTime);
		List<Integer> seatNumbers = bookingForm.getSeatNumbers();
		User user = userService.getUserByEmail(bookingForm.getUserEmail());
		Double price = bookingService.getTicketPrice(eventName, auditoriumName, dateTime, seatNumbers, user);
		Ticket ticket = new Ticket(event, dateTime, seatNumbers, user, price);

		bookingService.bookTicket(user, ticket);

		return REDIRECT_TO_HOME_PAGE;
	}

}
