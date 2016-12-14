package by.htp.teploukhava.testing.entities;

import by.htp.teploukhava.testing.abst.EntityAbs;

import javax.persistence.*;
import java.util.Collection;

/**Class describes question, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/
@Entity
@Table(name="question")
public class Question extends EntityAbs {
	@Id
	@Column(name="question_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int questionId;

	@Column(name="content")
	private String content;

	@ManyToOne(optional = false)
	@JoinColumn(name="test_id")
	private Test test;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "question")
    private Collection<Answer> listAnswer;

    public Question() {	}

    public Question(String content, Test test, Collection<Answer> listAnswer) {
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

	public Collection<Answer> getListAnswer() {
		return listAnswer;
	}

	public void setListAnswer(Collection<Answer> listAnswer) {
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
}
