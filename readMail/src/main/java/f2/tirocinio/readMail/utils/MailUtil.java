package f2.tirocinio.readMail.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Flags;
import jakarta.mail.Flags.Flag;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.ComparisonTerm;
import jakarta.mail.search.DateTerm;
import jakarta.mail.search.FlagTerm;
import jakarta.mail.search.ReceivedDateTerm;

public class MailUtil {

	private static final String ACCOUNT = "XXX";
	private static final String KEY = "XXX";

	public static void readMail() {

		Properties props = new Properties();
		props.put("mail.imap.host", "imap.gmail.com");
		props.put("mail.imap.port", "993");
		props.put("mail.imap.starttls.enable", "true");
		props.put("mail.imap.ssl.trust", "imap.gmail.com");

		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ACCOUNT, KEY);
			}
		};

		Session session = Session.getInstance(props, authenticator);

		try {

			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", ACCOUNT, KEY);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			FlagTerm ft = new FlagTerm(new Flags(Flag.SEEN), false);
			DateTerm dt = new ReceivedDateTerm(ComparisonTerm.GE, Date.valueOf(LocalDate.now().minusDays(1)));

			AndTerm at = new AndTerm(ft, dt);

			// Message[] messages = inbox.getMessages();
			Message[] messages = inbox.search(at);
			System.out.println(messages.length);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
