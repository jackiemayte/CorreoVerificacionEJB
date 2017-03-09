
package ejbs.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author mayteEcheverry
 */
@Stateless
public class EmailSenderBean implements  EmailSenderBeanLocal {///*EmailSenderBeanRemote*/
    @Resource(lookup = "jms/xaConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/emailsQueue")
    private Queue emailsQueue;

    @Override
    public void sendEmail(String to, String subject, String text) {
        try ( Connection connection = connectionFactory.createConnection();
              Session session = connection.createSession();
              MessageProducer producer = session.createProducer(emailsQueue)
            )
        {
            Message jmsMessage = session.createTextMessage( text );
            jmsMessage.setStringProperty(Constants.EMAIL_TO_PROPERTY, to);
            jmsMessage.setStringProperty(Constants.EMAIL_SUBJECT_PROPERTY, subject);
            producer.send(jmsMessage);            
        } catch (JMSException jmsException) {
              Logger.getGlobal().log(Level.SEVERE, null, jmsException);
          } 
    }


}
