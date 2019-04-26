package web.controller.rest;

import beans.converters.DtoConverter;
import beans.dtos.TicketDTO;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/rest/booking")
public class RestBookingController {

	@Resource
	private UserService userService;
	@Resource
	private BookingService bookingService;
	@Resource
	private EventService eventService;
	@Resource
	private DtoConverter<Ticket, TicketDTO> ticketConverter;

	@RequestMapping(method = POST)
	public ResponseEntity bookTicket(@RequestBody TicketDTO ticketDTO) {
		User user = userService.getUserByEmail(ticketDTO.getUserEmail());
		Ticket ticket = ticketConverter.convertToEntity(ticketDTO);
		bookingService.bookTicket(user, ticket);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "event/{id}/tickets", method = GET)
	public List<TicketDTO> getTicketsForEvent(@PathVariable long id) {
		Event event = eventService.getById(id);
		String eventName = event.getName();
		String auditoriumName = event.getAuditorium().getName();
		LocalDateTime eventDate = event.getDateTime();
		List<Ticket> tickets = bookingService.getTicketsForEvent(eventName, auditoriumName, eventDate);
		return tickets.stream()
				.map(ticket -> ticketConverter.convertToDto(ticket))
				.collect(toList());
	}

}
