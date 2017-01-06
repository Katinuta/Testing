package by.htp.teploukhava.testing.entities;

import javax.persistence.*;
import java.util.List;

/**Class describes question, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/
@Entity
@Table(name="question")
public class Question extends EntityAbs {
	@Id
	@Column(name="question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;

	@Column(name="content")
	private String content;

	@ManyToOne(optional = false)
	@JoinColumn(name="test_id")
	private Test test;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "question")
    private List<Answer> listAnswer;

    public Question() {	}

    public Question(String content, Test test, List<Answer> listAnswer) {
        this.content = content;
        this.test = test;
        this.listAnswer = listAnswer;
    }

    public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<Answer> getListAnswer() {
		return listAnswer;
	}

	public void setListAnswer(List<Answer> listAnswer) {
		this.listAnswer = listAnswer;
	}

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", content='" + content + '\'' +
                
                ", listAnswer=" + listAnswer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (questionId != question.questionId) return false;
        if (content != null ? !content.equals(question.content) : question.content != null) return false;
        if (test != null ? !test.equals(question.test) : question.test != null) return false;
        return listAnswer != null ? listAnswer.equals(question.listAnswer) : question.listAnswer == null;

    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        result = 31 * result + (listAnswer != null ? listAnswer.hashCode() : 0);
        return result;
    }
}
