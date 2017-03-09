
package ejbs.beans;

import javax.ejb.Local;
/**
 *
 * @author mayteEcheverry
 */
@Local
public interface EmailSenderBeanLocal {
    public void sendEmail(String to, String subject, String text);       
}
