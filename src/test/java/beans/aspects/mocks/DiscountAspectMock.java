package beans.aspects.mocks;

import beans.aspects.DiscountAspect;
import beans.services.discount.BirthdayStrategy;
import beans.services.discount.TicketsStrategy;

import java.util.HashMap;

public class DiscountAspectMock extends DiscountAspect {

    public static void cleanup() {
        discountPerUserCounter.put(BirthdayStrategy.class.getSimpleName(), new HashMap<>());
        discountPerUserCounter.put(TicketsStrategy.class.getSimpleName(), new HashMap<>());
    }

}
