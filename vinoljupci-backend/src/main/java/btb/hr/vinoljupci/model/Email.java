package btb.hr.vinoljupci.model;

public class Email {
	private String[] to;
	private String subject;
	private String body;
	private StringBuilder mailTekst;

	public Email() {
	}

	public Email(String[] to, String subject, String body, StringBuilder mailTekst) {
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.mailTekst = mailTekst;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public StringBuilder getMailTekst() {
		return mailTekst;
	}

	public void setMailTekst(StringBuilder mailTekst) {
		this.mailTekst = mailTekst;
	}

}
