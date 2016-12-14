package by.htp.teploukhava.testing.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 16.10.16.
 */
@Entity
@Table(name="student_to_subject")
public class SubjectToStudent {
    @Embeddable
    public static class Id implements Serializable{
        @Column(name="subject_id")
        private int subjectId;

        @Column(name="user_id")
        private int userId;

        public Id(){}

        public Id(int subjectId, int userId) {
            this.subjectId = subjectId;
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;

            if (subjectId != id.subjectId) return false;
            return userId == id.userId;

        }

        @Override
        public int hashCode() {
            int result = subjectId;
            result = 31 * result + userId;
            return result;
        }
    }

    @EmbeddedId
    private Id id=new Id();

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id" , nullable =false,updatable = false,insertable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name="subject_id", nullable =false,updatable = false,insertable = false)
    private Subject subject;

    public SubjectToStudent(){}
    public SubjectToStudent( User user, Subject subject) {
        this.id.subjectId = subject.getSubjectId();
        this.id.userId=user.getUserId();
        this.user = user;
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SubjectToStudent{" +
                "user=" + user +
                ", subject=" + subject.getName()+
                '}';
    }
}
