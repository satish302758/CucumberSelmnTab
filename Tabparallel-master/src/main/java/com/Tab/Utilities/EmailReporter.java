package com.Tab.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailReporter
{
	public static void mailTestNGReport()
	{
		String user = "";
		String pass = "";
		// Map<String,String> mailTo = new HashMap<String,String>();
		List<String> mailTo = new ArrayList<String>();
		mailTo.add("");
		mailTo.add("");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.host", "127.0.0.1");
		// props.put("mail.smtp.port", "25");
		Session session = Session.getInstance(props);
		try
		{
			session = Session.getInstance(props, new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(user, pass);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			for (String mail : mailTo)
			{
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			}
			String filename = System.getProperty("user.dir") + "\\target\\SummaryReports\\Summary Report.html";
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Summary test run Report");
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);
			message.setContent(multipart);
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
