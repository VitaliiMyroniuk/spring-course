package beans.services;

import beans.models.UserAccount;

public interface UserAccountService {

	UserAccount create(UserAccount userAccount);

	UserAccount update(UserAccount userAccount);

	void refillAccount(String userEmail, double amount);

	void withdrawMoney(UserAccount userAccount, double amount);

	double getBalance(String userEmail);

}
