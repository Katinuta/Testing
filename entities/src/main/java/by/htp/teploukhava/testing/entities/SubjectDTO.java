package by.htp.teploukhava.testing.entities;

/**Class describes subject, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/

public class SubjectDTO extends EntityAbs {

	private int subjectId;

	private String name;


	public SubjectDTO() {
	}

	public SubjectDTO(int subjectId, String name) {
		this.name = name;
		this.subjectId= subjectId;
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

//	public List<Test> getTest() {
//		return tests;
//	}
//
//	public void setTest(List<Test> tests) {
//		this.tests = tests;
//	}
//
//    public Collection<SubjectToStudent> getListSubject() {
//
//    	return listUsers;
//    }
//
//    public void setListSubject(Collection<SubjectToStudent> listUsers) {
//        this.listUsers = listUsers;
//    }
//
//	public Collection<SubjectToStudent> getListUsers() {
//		return listUsers;
//	}
//
//	public void setListUsers(Collection<SubjectToStudent> listUsers) {
//		this.listUsers = listUsers;
//	}

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

        SubjectDTO that = (SubjectDTO) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
