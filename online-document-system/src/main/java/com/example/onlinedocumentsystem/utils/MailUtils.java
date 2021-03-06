package com.example.onlinedocumentsystem.utils;
import com.sun.mail.util.MailSSLSocketFactory;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
    public static final String URL = "http://localhost:8080/verifyByMail?code=";
    public static final String EMAIL_PASSWORD = "123";
    public static final String EMAIL_ACCOUNT = "123";
    public static final String QQ_SMTP_HOST = "smtp.qq.com";
    public static int sendMail(String mail) throws Exception {
        Integer code = (int)(Math.random()*1e7);
        String url = code.toString();
        Properties prop = new Properties();
        prop.setProperty("mail.debug", "true");
        prop.setProperty("mail.host", QQ_SMTP_HOST);
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.transport.protocol", "smtp");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getInstance(prop);
        Transport ts = session.getTransport();
        ts.connect(QQ_SMTP_HOST,EMAIL_ACCOUNT, EMAIL_PASSWORD);
        Message message = createVerifiedMail(session,mail,url);
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        return code;
    }
    public static MimeMessage createVerifiedMail(Session session,String mail,String url)throws Exception {
        MimeMessage message = new MimeMessage(session);
        String content = "您正在使用该邮箱进行账户验证，您的验证码为：，" + url +"\n如非本人操作请忽略。";
        message.setFrom(new InternetAddress("123"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        message.setSubject("【九日云】账户验证");
        message.setContent(content, "text/html;charset=UTF-8");
        return message;
    }
}
