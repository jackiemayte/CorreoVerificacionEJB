
package ejbs.action;

import ejbs.beans.DatabaseBeanLocal;
import ejbs.beans.Usuario;
import ejbs.beans.Usuario2;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mayteEcheverry
 */
public class ActivateAction extends org.apache.struts.action.Action {
    @EJB
    private DatabaseBeanLocal ejbDatabase;
    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";
    ActionForward actionForward = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        ejbDatabase=(DatabaseBeanLocal) request.getServletContext().getAttribute("ejbDatabase");
        String id = request.getParameter("id");
        Optional<Usuario2> optional =ejbDatabase.findId(id);
        if(optional.isPresent()){
            Usuario2 user2=optional.get();
            if(ejbDatabase.deleteUsuario(id)!=0){
                Optional<Usuario> optional1=ejbDatabase.insertUsuario(user2.getUsername(),user2.getPassword(),user2.getEmail());
                if(optional1.isPresent())
                    actionForward=mapping.findForward(SUCCESS);
            }    
        }else{
             actionForward=mapping.findForward(FAILURE);  
        }
        return actionForward;
    }
}
