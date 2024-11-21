package egovframework.naksinuri_original.let.naksinuri.web;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.naksinuri_original.let.naksinuri.service.NaksinuriMailVO;

public class MailUtil
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);
	
  private static String MAIL_CHARSET = "euc-kr";

  public static String mailSend(NaksinuriMailVO mailVO)
  {
    String msg = "";

    String fromMail = mailVO.getFromMail();
    String fromMailNm = mailVO.getFromMailNm();
    String title = mailVO.getTitle();
    String[] addToMail = mailVO.getAddToMail();

    if (isEmpty(fromMail)) {
      msg = "FAIL";
      return msg;
    }

    String mailMsg = "";
    mailMsg = mailMsg + mailVO.getMailMsg();
    try
    {
      MultiPartEmail email = new MultiPartEmail();
      
      email.setCharset(MAIL_CHARSET);
      email.setFrom(fromMail, fromMailNm);

      email.addReplyTo(fromMail, fromMailNm);
      email.setSubject(title);
      email.setMsg(mailMsg);

      email.addTo(addToMail);

      email.setHostName(mailVO.getSmtp());
      email.setSSL(true);
      email.setSmtpPort(mailVO.getSmtpPort());

      email.setAuthentication(mailVO.getSmtpEmail(), mailVO.getSmtpEmailPw());

      email.send();
      msg = "SUCCESS";
    } catch (EmailException e) {
    	LOGGER.debug("fail sendMail " + e.getMessage());
      msg = "FAIL";
    }

    return msg;
  }

  public static boolean isEmpty(String str) {
    return (str == null) || (str.length() == 0);
  }
}