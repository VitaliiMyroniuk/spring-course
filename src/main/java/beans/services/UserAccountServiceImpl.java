package beans.services;

import beans.daos.UserAccountDao;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;

@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

	@Resource
	private UserAccountDao userAccountDao;
	@Resource
	private UserService userService;
	@Resource
	private PlatformTransactionManager txManager;

	@Override
	@Transactional
	public UserAccount create(UserAccount userAccount) {
		return userAccountDao.create(userAccount);
	}

	@Override
	@Transactional
	public UserAccount update(UserAccount userAccount) {
		return userAccountDao.create(userAccount);
	}

	@Override
	public void refillAccount(String userEmail, double amount) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
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
		} catch (Exception ex) {
			txManager.rollback(status);
			throw new RuntimeException(ex);
		}
		txManager.commit(status);
	}

	@Override
	@Transactional
	public void withdrawMoney(UserAccount userAccount, double amount) {
		double currentBalance = userAccount.getBalance();
		if (amount > currentBalance)
			throw new IllegalStateException("Not enough money to book a ticket");
		userAccount.setBalance(currentBalance - amount);
		userAccountDao.update(userAccount);
	}

	@Override
	@Transactional(readOnly = true)
	public double getBalance(String userEmail) {
		User user = userService.getUserByEmail(userEmail);
		UserAccount userAccount = user.getUserAccount();
		return ofNullable(userAccount)
				.map(UserAccount::getBalance)
				.orElse(DOUBLE_ZERO);
	}

}
