
package ejbs.action;

import ejbs.beans.DatabaseBeanLocal;
import ejbs.beans.EmailSenderBeanLocal;
import ejbs.beans.Usuario2;
import ejbs.form.FormInsertActionForm;
import java.util.Locale;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author mayteEcheverry
 */

public class InsertAction extends org.apache.struts.action.Action {
    
    @EJB
    private DatabaseBeanLocal ejbDatabase;
    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        ejbDatabase=(DatabaseBeanLocal) request.getServletContext().getAttribute("ejbDatabase");
        ActionErrors errors = new ActionErrors();
        ActionMessage textoError;
        MessageResources mrs = this.getResources(request);
        Locale userlocale=this.getLocale(request);
        ActionForward actionForward = null;
        FormInsertActionForm datos = (FormInsertActionForm ) form;
        
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        String email= request.getParameter("email");
        
        if(ejbDatabase.findUsuario2(user)){
            textoError = new ActionMessage("fallo.existe",user);//mrs.getMessage(userlocale,"header.user")
            errors.add(user,textoError);
            this.saveErrors(request, errors);
            actionForward =mapping.getInputForward();
        }else{
             //guardo en BD
              String id;
              Optional<Usuario2> optional = ejbDatabase.insertUsuario2(user, pass, email);
              if ( optional.isPresent() ){
                    Usuario2 user2=optional.get();
                    id=user2.getId();
              }else{
                    id=null;
              }
              
              if(id!=null){
                   //envio Email
                   EmailSenderBeanLocal emailSenderEJB = (EmailSenderBeanLocal) request.getServletContext().getAttribute("emailSenderEJB");
                   String to      = email;
                   String subject = "Activación de Email";
                   String text    = "http://localhost:8080/Envio_Verificacion_Email/activate.do?id="+id;
                   emailSenderEJB.sendEmail(to, subject, text);
                   actionForward= mapping.findForward(SUCCESS); 
             }else{
                actionForward= mapping.findForward(FAILURE);  
             }
        } 
        return actionForward;
    }
}
