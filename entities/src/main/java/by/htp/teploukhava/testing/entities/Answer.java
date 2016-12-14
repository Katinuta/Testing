package by.htp.teploukhava.testing.entities;
import by.htp.teploukhava.testing.abst.EntityAbs;

import javax.persistence.*;

/**
 * Class describes answer, contains constructors,set and get methods for all
 * fields of class and overring method toString
 */
@Entity
@Table(name="answer")
public class Answer extends EntityAbs {

    @Id
    @Column(name="answer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int answerId;

    @Column(name="content")
	private String content;

    @Column(name="right_answer")
	private boolean rightAnswer;

	@ManyToOne(optional = false)
	@JoinColumn(name="question_id")
	private Question question;

	public Answer() {}

	public Answer(String content, boolean rightAnswer, Question question) {
		this.content = content;
		this.rightAnswer = rightAnswer;
		this.question = question;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(boolean rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId +  ", content=" + content + ", rightAnswer="
				+ rightAnswer + "]";
	}




}
