package by.htp.telpoukhava.testing.entities;

import by.htp.telpoukhava.testing.abst.Entity;

/**
 * Class describes answer, contains constructors,set and get methods for all
 * fields of class and overring method toString
 */

public class Answer extends Entity {
	private int answerId;
	private int questionId;
	private String content;
	private boolean rightAnswer;

	public Answer() {

	}



	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
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

	public boolean isRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(boolean rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", questionId=" + questionId + ", content=" + content + ", rightAnswer="
				+ rightAnswer + "]";
	}

}
