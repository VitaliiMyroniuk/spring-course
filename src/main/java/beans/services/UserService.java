package beans.services;

import beans.models.Ticket;
import beans.models.User;

import java.util.List;

public interface UserService {

    User register(User user);

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    List<Ticket> getBookedTickets();

}
