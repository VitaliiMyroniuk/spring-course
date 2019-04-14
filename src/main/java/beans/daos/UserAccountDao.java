package beans.daos;

import beans.models.UserAccount;

public interface UserAccountDao {

	UserAccount getById(long id);

	UserAccount create(UserAccount userAccount);

	UserAccount update(UserAccount userAccount);

}
