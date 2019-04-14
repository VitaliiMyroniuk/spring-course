package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDao;
import beans.models.UserAccount;
import org.springframework.stereotype.Repository;

@Repository("userAccountDao")
public class UserAccountDaoImpl extends AbstractDAO implements UserAccountDao {

	@Override
	public UserAccount getById(long id) {
		return getCurrentSession().get(UserAccount.class, id);
	}

	@Override
	public UserAccount create(UserAccount userAccount) {
		Long id = (Long) getCurrentSession().save(userAccount);
		userAccount.setId(id);
		return userAccount;
	}

	@Override
	public UserAccount update(UserAccount userAccount) {
		return (UserAccount) getCurrentSession().merge(userAccount);
	}

}
