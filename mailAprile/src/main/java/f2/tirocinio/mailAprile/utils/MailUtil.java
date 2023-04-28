package f2.tirocinio.mailAprile.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class MailUtil {

	private static final String ACCOUNT = "XXX";
	private static final String KEY = "XXX";

	public static void sendMail(String recipient, String subject, String body) {

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ACCOUNT, KEY);
			}
		};

		Session session = Session.getInstance(props, auth);

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(ACCOUNT));
			msg.setRecipient(RecipientType.TO, new InternetAddress(recipient));

			msg.setText(body);

			Transport.send(msg);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void sendMultiMail(String recipient, String subject, String body) {

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ACCOUNT, KEY);
			}
		};

		Session session = Session.getInstance(props, auth);

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(ACCOUNT));
			msg.setRecipient(RecipientType.TO, new InternetAddress(recipient));

			MimeBodyPart bp1 = new MimeBodyPart();
			bp1.setContent(
					"<img src='https://th.bing.com/th/id/OIG.elX_x0etp9qPh2ZYa3qO?pid=ImgGn' style='width:200px; height:200px;'>",
					"text/html");
			MimeBodyPart bp2 = new MimeBodyPart();
			bp2.setContent(body, "text/plain");

			MimeMultipart mp = new MimeMultipart();

			mp.addBodyPart(bp1);
			mp.addBodyPart(bp2);

			msg.setContent(mp);

			Transport.send(msg);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
