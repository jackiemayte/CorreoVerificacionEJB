
package ejbs.action;

import ejbs.beans.DatabaseBeanLocal;
import ejbs.beans.Usuario2;
import java.util.Collection;
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
public class ListUsers2Action extends org.apache.struts.action.Action {
    @EJB
    private DatabaseBeanLocal ejbDatabase;
    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ejbDatabase=(DatabaseBeanLocal) request.getServletContext().getAttribute("ejbDatabase");
        Collection<Usuario2> usuarios2 = ejbDatabase.getUsuarios2();
        request.setAttribute("usuarios2", usuarios2);
        return mapping.findForward(SUCCESS);
    }
}
