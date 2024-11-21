package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriAdminVO extends CommonVO
{
  private String email;
  private String email_pw;
  private String idx;

  public String getIdx()
  {
    return this.idx;
  }
  public void setIdx(String idx) {
    this.idx = idx;
  }
  public String getEmail() {
    return this.email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getEmail_pw() {
    return this.email_pw;
  }
  public void setEmail_pw(String email_pw) {
    this.email_pw = email_pw;
  }
}