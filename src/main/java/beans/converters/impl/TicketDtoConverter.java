package beans.converters.impl;

import beans.converters.DtoConverter;
import beans.dtos.TicketDTO;
import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import util.CsvUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TicketDtoConverter implements DtoConverter<Ticket, TicketDTO> {

	@Resource
	private ConversionService conversionService;
	@Resource
	private AuditoriumService auditoriumService;
	@Resource
	private EventService eventService;
	@Resource
	private UserService userService;
	@Resource
	private BookingService bookingService;

	@Override
	public Ticket convertToEntity(TicketDTO ticketDTO) {
		String eventName = ticketDTO.getEventName();
		String auditoriumName = ticketDTO.getAuditoriumName();
		Auditorium auditorium = auditoriumService.getByName(auditoriumName);
		LocalDateTime eventDate = conversionService.convert(ticketDTO.getEventDate(), LocalDateTime.class);
		Event event = eventService.getEvent(eventName, auditorium, eventDate);
		List<Integer> seatNumbers = CsvUtil.fromCsvToList(ticketDTO.getSeats(), Integer::valueOf);
		User user = userService.getUserByEmail(ticketDTO.getUserEmail());
		Double price = bookingService.getTicketPrice(eventName, auditoriumName, eventDate, seatNumbers, user);
		return new Ticket(event, eventDate, seatNumbers, user, price);
	}

	@Override
	public TicketDTO convertToDto(Ticket ticket) {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setUserEmail(ticket.getUser().getEmail());
		ticketDTO.setEventName(ticket.getEvent().getName());
		ticketDTO.setEventDate(conversionService.convert(ticket.getDateTime(), String.class));
		ticketDTO.setAuditoriumName(ticket.getEvent().getAuditorium().getName());
		ticketDTO.setSeats(ticket.getSeats());
		return ticketDTO;
	}

}
