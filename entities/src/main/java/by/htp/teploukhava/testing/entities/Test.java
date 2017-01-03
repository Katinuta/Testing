package by.htp.teploukhava.testing.entities;

import by.htp.teploukhava.testing.abst.EntityAbs;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**Class describes test, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/
@Entity
@Table(name="test")
public class Test extends EntityAbs {
	@Id
	@Column(name="test_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testId;

	@Column(name="name")
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name="subject_id")
	private Subject subject;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "test")
	private List<Question> listQuestions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
	private List<Result> listResult;

	public Test() {
	}

	public Test(String name, Subject subject, List<Question> listQuestions, List<Result> listResult) {
		this.name = name;
		this.subject = subject;
		this.listQuestions = listQuestions;
		this.listResult = listResult;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(List<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

    public List<Result> getListResult() {
        return listResult;
    }

    public void setListResult(List<Result> listResult) {
        this.listResult = listResult;
    }

	@Override
	public String toString() {
		return "Test{" +
				"testId=" + testId +
				", name='" + name + '\'' +
				", subject=" + subject +
				", listQuestions=" + listQuestions +

				'}';
	}
}
