package egovframework.naksinuri_original.let.naksinuri.service;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriMailVO extends CommonVO
{
  private String smtp = "smtp.daum.net";//"smtp.gmail.com";
  private int smtpPort = 465;
  private String smtpEmail;
  private String smtpEmailPw;
  private String[] addToMail;
  private String filePath;
  private String fileName;
  private String description = "파일 설명";

  private String title = "메일 제목.";
  private String fromMail;
  private String fromMailNm;
  private String mailMsg;

  public String getSmtpEmail()
  {
    return this.smtpEmail;
  }
  public void setSmtpEmail(String smtpEmail) {
    this.smtpEmail = smtpEmail;
  }
  public String getSmtpEmailPw() {
    return this.smtpEmailPw;
  }
  public void setSmtpEmailPw(String smtpEmailPw) {
    this.smtpEmailPw = smtpEmailPw;
  }
  public String getTitle() {
    return this.title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getFilePath() {
    return this.filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  public String getFileName() {
    return this.fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  public String getFromMail() {
    return this.fromMail;
  }
  public void setFromMail(String fromMail) {
    this.fromMail = fromMail;
  }
  public String getFromMailNm() {
    return this.fromMailNm;
  }
  public void setFromMailNm(String fromMailNm) {
    this.fromMailNm = fromMailNm;
  }
  public String getMailMsg() {
    return this.mailMsg;
  }
  public void setMailMsg(String mailMsg) {
    this.mailMsg = mailMsg;
  }
  public String getSmtp() {
    return this.smtp;
  }
  public int getSmtpPort() {
    return this.smtpPort;
  }

  public String[] getAddToMail() {
	String[] ret = null;
	if(addToMail != null) {
		ret = new String[addToMail.length];
		for(int i=0; i<addToMail.length;i++) {
			ret[i] = addToMail[i];
		}
	}
	return ret;
  }
  public void setAddToMail(String[] addToMail) {
	  //this.addToMail = addToMail;
	  this.addToMail = Arrays.copyOf(addToMail, addToMail.length);
  }
}