package beans.daos.mocks;

import beans.daos.db.BookingDAOImpl;
import beans.models.Ticket;
import beans.models.User;

import java.util.Map;
import java.util.Set;

public class BookingDAOBookingMock extends BookingDAOImpl {

    private final Map<User, Set<Ticket>> initWith;

    public BookingDAOBookingMock(Map<User, Set<Ticket>> initWith) {
        this.initWith = initWith;
    }

    public void init() {
        cleanup();
        System.out.println("creating " + initWith);
        initWith.forEach((user, tickets) -> tickets.forEach(ticket -> create(user, ticket)));
    }

    public void cleanup() {
        getAllTickets().forEach(ticket -> delete(ticket.getUser(), ticket));
    }

}
