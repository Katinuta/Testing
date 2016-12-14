package by.htp.telpoukhava.testing.entities;

import by.htp.telpoukhava.testing.abst.EntityAbs;

import javax.persistence.*;
import java.io.Serializable;

/**Class describes result, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/
@Entity
@Table(name="result")
public class Result extends EntityAbs {
	@Embeddable
	public static class IdResult implements Serializable{
		@Column(name="test_id")
		private int testId;

		@Column(name="user_id")
		private int userId;

		public IdResult(){}

		public IdResult(int testId, int userId) {
			this.testId = testId;
			this.userId = userId;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			IdResult idResult = (IdResult) o;

			if (testId != idResult.testId) return false;
			return userId == idResult.userId;

		}

		@Override
		public int hashCode() {
			int result = testId;
			result = 31 * result + userId;
			return result;
		}

	}

	@EmbeddedId
	private IdResult idResult;

	@Column(name="result")
	private int result;

	@ManyToOne(optional = false)
	@JoinColumn(name="user_id",  nullable =false,updatable = false,insertable = false)
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name="test_id", nullable =false,updatable = false,insertable = false)
	private Test test;


	public Result() {}

	public Result(int result, User user, Test test) {
		this.result = result;
		this.user = user;
		this.test = test;
		this.idResult.testId=test.getTestId();
		this.idResult.userId=user.getUserId();
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	public IdResult getIdResult() {
		return idResult;
	}

	public void setIdResult(IdResult idResult) {
		this.idResult = idResult;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "Result{" +
				"idResult=" + idResult +
				", result=" + result +
				", user=" + user +
				", test=" + test +
				'}';
	}
}
