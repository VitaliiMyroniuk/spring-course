package beans.daos.mocks;

import beans.daos.inmemory.InMemoryAuditoriumDAO;
import beans.models.Auditorium;

import java.util.List;

public class InMemoryAuditoriumDAOMock extends InMemoryAuditoriumDAO {

    public InMemoryAuditoriumDAOMock(List<Auditorium> auditoriums) {
        super(auditoriums);
    }

}
