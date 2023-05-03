package f2.tirocinio.mailFromTemplate.utils;

import java.io.IOException;
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

	private static final String ACCOUNT = "alberto.ramponi@gmail.com";
	private static final String KEY = "loebpktfdfhrxxqh";

	public static void sendMail(String recipient, String body, String fileToAttach) {
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
			msg.setFrom(new InternetAddress(ACCOUNT));
			msg.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			msg.setSubject("Welcome to our site!");

			MimeBodyPart bp = new MimeBodyPart();
			bp.setContent(body, "text/html");

			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(bp);

			if (fileToAttach != null) {
				MimeBodyPart bpAtt = new MimeBodyPart();
				try {
					bpAtt.attachFile(fileToAttach);
					mp.addBodyPart(bpAtt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			msg.setContent(mp);
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
