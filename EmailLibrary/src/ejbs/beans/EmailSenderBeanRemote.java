
package ejbs.beans;

import javax.ejb.Remote;

@Remote
public interface EmailSenderBeanRemote {
    public void sendEmail(String to, String subject, String text);    
}
