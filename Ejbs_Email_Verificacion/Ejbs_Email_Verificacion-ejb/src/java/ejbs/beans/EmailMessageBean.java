
package ejbs.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mayteEcheverry
 */
@MessageDriven(activationConfig = {
                                    @ActivationConfigProperty(propertyName = "destinationLookup",       propertyValue = "jms/emailsQueue"),
                                    @ActivationConfigProperty(propertyName = "destinationType",         propertyValue = "javax.jms.Queue"),
                                    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "jms/xaConnectionFactory")
                                  })
public class EmailMessageBean implements MessageListener {
    //@Resource(lookup = "mail/rberjon")
    @Resource(lookup = "mail/babsy.mayte")
    private Session mailSession;
        
    @Override
    public void onMessage(Message message) {

        try{
            TextMessage jmsMessage = (TextMessage) message;
            String to      = jmsMessage.getStringProperty( Constants.EMAIL_TO_PROPERTY );
            String subject = jmsMessage.getStringProperty( Constants.EMAIL_SUBJECT_PROPERTY );
            String text    = jmsMessage.getText();
            
           javax.mail.Message emailMessage = new MimeMessage(mailSession);
           emailMessage.setSubject(subject);
           emailMessage.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
           emailMessage.setText(text);
           Transport.send(emailMessage);         
        } catch (MessagingException | JMSException exception) {
            Logger.getGlobal().log(Level.SEVERE, null, exception);
          }
    }
    
}
