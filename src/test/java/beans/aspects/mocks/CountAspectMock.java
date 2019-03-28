package beans.aspects.mocks;

import beans.aspects.CounterAspect;

public class CountAspectMock extends CounterAspect {

    public static void cleanup() {
        accessByNameCounter.clear();
        getPriceByNameCounter.clear();
        bookByNameCounter.clear();
    }

}
