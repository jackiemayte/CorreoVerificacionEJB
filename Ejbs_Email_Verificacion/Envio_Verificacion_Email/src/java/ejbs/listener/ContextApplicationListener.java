
package ejbs.listener;

import ejbs.beans.DatabaseBeanLocal;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ejbs.beans.EmailSenderBeanLocal;


public class ContextApplicationListener implements ServletContextListener {
    @EJB(lookup = "java:app/Ejbs_Email_Verificacion-ejb/EmailSenderBean!ejbs.beans.EmailSenderBeanLocal")
    private EmailSenderBeanLocal emailSenderEJB;
    
    @EJB(lookup = "java:app/Ejbs_Email_Verificacion-ejb/DatabaseBean!ejbs.beans.DatabaseBeanLocal")
    private DatabaseBeanLocal ejbDatabase;
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       sce.getServletContext().setAttribute("emailSenderEJB", emailSenderEJB);
       sce.getServletContext().setAttribute("ejbDatabase", ejbDatabase);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
