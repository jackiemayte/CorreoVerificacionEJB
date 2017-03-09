
package ejbs.form;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author mayteEcheverry
 */
public class FormInsertActionForm extends org.apache.struts.action.ActionForm {  
    private String user;
    private String password;
    private String email;

    public String getUser() {
        return user;
    }

    public void setUser(String string) {
        user = string;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String string) {
        password = string;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String string) {
        email = string;
    }

    public FormInsertActionForm() {
        super();
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        ActionMessage textoError;
        MessageResources mrs = (MessageResources) request.getServletContext().getAttribute(Globals.MESSAGES_KEY);
        Locale userLocale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
          
        if ( GenericValidator.isBlankOrNull( user ) ){
           textoError  = new ActionMessage("errors.required",mrs.getMessage(userLocale, "header.user"));
            errors.add(mrs.getMessage(userLocale, "header.user"), textoError );
        }
        
        if ( !GenericValidator.minLength(password,8) ){
            textoError  = new ActionMessage("errors.minlength",mrs.getMessage(userLocale, "header.password"),mrs.getMessage(userLocale,"header.num"));
            errors.add(mrs.getMessage(userLocale,"header.password"), textoError );
        }
        
        if ( GenericValidator.isBlankOrNull( password ) ){
            textoError  = new ActionMessage("errors.required",mrs.getMessage(userLocale, "header.password"));
            errors.add(mrs.getMessage(userLocale, "header.password"), textoError );
        }
        
        if ( GenericValidator.isBlankOrNull( email ) ){
            textoError  = new ActionMessage("errors.required",mrs.getMessage(userLocale, "header.email"));
            errors.add(mrs.getMessage(userLocale, "header.email"), textoError );
        } 
        
        if ( !GenericValidator.isEmail(email ) ){
            textoError = new ActionMessage("errors.email",mrs.getMessage(userLocale, "header.email"));
            errors.add(mrs.getMessage(userLocale, "header.email"),textoError );
        } 
        return errors;
    }
}
