package beans.models;

import java.util.Objects;

public class UserAccount {

	private long id;
	private User user;
	private double balance;

	public UserAccount() {
	}

	public UserAccount(User user, double balance) {
		this.user = user;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserAccount that = (UserAccount) o;

		return id == that.id &&
				Double.compare(that.balance, balance) == 0 &&
				Objects.equals(user, that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, balance);
	}

	@Override
	public String toString() {
		return "UserAccount{" +
				"id=" + id +
				", user=" + user +
				", balance=" + balance +
				'}';
	}

}
