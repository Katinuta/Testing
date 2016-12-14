package by.htp.teploukhava.testing.entities;

import by.htp.teploukhava.testing.abst.EntityAbs;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**Class describes subject, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/
@Entity
@Table(name="subject")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Subject extends EntityAbs {
	@Id
	@Column(name="subject_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subjectId;

	@Column(name="name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "subject")
	private List<Test> tests;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private Collection<SubjectToStudent> listUsers;

	public Subject() {
	}

	public Subject(String name, List<Test> tests, Collection<SubjectToStudent> listUsers) {
		this.name = name;
		this.tests = tests;
		this.listUsers = listUsers;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Test> getTest() {
		return tests;
	}

	public void setTest(List<Test> tests) {
		this.tests = tests;
	}

    public Collection<SubjectToStudent> getListSubject() {

    	return listUsers;
    }

    public void setListSubject(Collection<SubjectToStudent> listUsers) {
        this.listUsers = listUsers;
    }

	public Collection<SubjectToStudent> getListUsers() {
		return listUsers;
	}

	public void setListUsers(Collection<SubjectToStudent> listUsers) {
		this.listUsers = listUsers;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"subjectId=" + subjectId +
				", name='" + name + '\''+

				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Subject subject = (Subject) o;

		return name.equals(subject.name);

	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
