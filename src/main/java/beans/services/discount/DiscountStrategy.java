package beans.services.discount;

import beans.models.User;

public interface DiscountStrategy {

    double calculateDiscount(User user);

}
