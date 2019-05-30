package btb.hr.vinoljupci.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import btb.hr.vinoljupci.model.Email;
import btb.hr.vinoljupci.model.FilterQueryToClient;
import btb.hr.vinoljupci.reports.ReportPdf;

public class MailBuilder {
	
	// Method for sending email
		public static void send(Email email, FilterQueryToClient filterQueryToClient) throws UnsupportedEncodingException, MessagingException {

			Properties props = propertiesInitialization();
			System.out.println("MailBuilder prop ok");

			// Get the default Session object.
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);

			message.setSubject(email.getSubject());
			message.setFrom(new InternetAddress(MailEnvironment.MAIL_USERNAME));
			message = setTo(message, email.getTo());

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");

			Multipart multipart = new MimeMultipart();

			MimeBodyPart messageBodyPart1 = new MimeBodyPart(headers, email.getMailTekst().toString().getBytes("UTF-8"));
			multipart.addBodyPart(messageBodyPart1);
			
			//---------------------------- SLANJE ATTACHMENTA --------
			ReportPdf reportPdf = new ReportPdf();
			
			DataSource dataSource = new ByteArrayDataSource(reportPdf.generateVinarPdfReport(filterQueryToClient), "application/pdf; charset=\"UTF-8\"");

			MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName(MimeUtility.encodeText("Izvještaj-vinoljupci.pdf", "UTF-8", null));
            System.out.println("---- encode "+MimeUtility.getEncoding(new DataHandler(dataSource)));
			multipart.addBodyPart(pdfBodyPart);
		
			message.setContent(multipart);
			//--------------------------------------------------------------------------------
			Transport transport = session.getTransport("smtp");
			transport.connect(MailEnvironment.MAIL_HOST, MailEnvironment.MAIL_USERNAME, MailEnvironment.MAIL_PASSWORD);
			System.out.println("MailBuilder send start");
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("MailBuilder send end");
			System.out.println("---------------------------");
			System.out.println();
			transport.close();	
			
		}

		private static MimeMessage setTo(MimeMessage message, String[] to) throws MessagingException {

			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			return message;
		}

		private static Properties propertiesInitialization() {

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", MailEnvironment.MAIL_HOST);
			props.put("mail.smtp.user", MailEnvironment.MAIL_USERNAME);
			props.put("mail.smtp.password", MailEnvironment.MAIL_PASSWORD);
			props.put("mail.smtp.port", MailEnvironment.MAIL_PORT);
			props.put("mail.smtp.auth", "true");

			return props;
		}

		public boolean sendMail(FilterQueryToClient filterQueryToClient) throws UnsupportedEncodingException, MessagingException {

			String[] to = null;
			String subject = "";
			String body = "";
			StringBuilder mailTekst2 = null;

			
				to = new String[] { "matej.samanic@btb.hr" };
		
//				to = new String[] { ((User) object).getMail() };
			

			
				subject = "Vinoljupci - Izvještaj";
				body = "";
				mailTekst2 = new StringBuilder();
				mailTekst2.append("<htm>");
				mailTekst2.append("<head>");
				mailTekst2.append("<meta charset='UTF-8'>");
				mailTekst2.append("</head>");
				mailTekst2.append("<body>");
				mailTekst2.append("<h1> Poštovani, </h1>");
				mailTekst2.append("<p>U privitku se nalazi izvještaj o prometu</p>");
				mailTekst2.append("<p> -------------------------------------------------- </p>");
				mailTekst2.append("<p>S poštovanjem,</p>");
				mailTekst2.append("<p>Irena Lučić</p>");
				mailTekst2.append("<p><b>Vinoljupci</b> </p><br>");
				mailTekst2.append("<p>Kontakt:  </p>");
				mailTekst2.append("<p><b>mob:</b>  091 6464 684 </p>");
				mailTekst2.append("<p><b>mail:</b>  vinoljupci@gmail.com </p><br>");

				
				mailTekst2.append("<img alt='logo' src='btb/hr/vinoljupci/reports/kiss.png'>");
				mailTekst2.append("</body>");
				mailTekst2.append("</html>");

		
//				SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
//				String datumRezervacije = dateFormat.format(((Rezervacija) p_object).getDatum_rezervacije());
//				String datumVracanja = dateFormat.format(((Rezervacija) p_object).getDatum_vracanja());

				

			Email email = new Email(to, subject, body, mailTekst2);
			send(email, filterQueryToClient);

			return true;
		}
}
