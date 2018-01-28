package pl.coderslab.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User addressee;
	
	@NotEmpty
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private LocalDateTime created;
	
	@Column(name = "read_status")
	private boolean read;
	
	
	public Message() {}
	
	public String getHeading() {
		String string = this.getText();
		return this.getText().substring(0, Math.min(30, string.length()));
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getAddressee() {
		return addressee;
	}

	public void setAddressee(User addressee) {
		this.addressee = addressee;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", addressee=" + addressee + ", text=" + text + ", created="
				+ created + ", read=" + read + "]";
	}




	
	
	
}
