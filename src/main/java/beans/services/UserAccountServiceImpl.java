package beans.services;

import beans.daos.UserAccountDao;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static java.util.Objects.isNull;

@Service("userAccountService")
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

	@Resource
	private UserAccountDao userAccountDao;
	@Resource
	private UserService userService;

	@Override
	public UserAccount create(UserAccount userAccount) {
		return userAccountDao.create(userAccount);
	}

	@Override
	public UserAccount update(UserAccount userAccount) {
		return userAccountDao.create(userAccount);
	}

	@Override
	public void refillAccount(String userEmail, double amount) {
		User user = userService.getUserByEmail(userEmail);
		UserAccount userAccount = user.getUserAccount();
		if (isNull(userAccount)) {
			userAccount = new UserAccount(user, amount);
			userAccountDao.create(userAccount);
		} else {
			double currentBalance = userAccount.getBalance();
			userAccount.setBalance(currentBalance + amount);
			userAccountDao.update(userAccount);
		}
	}

}
