/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.action;

import ejbs.beans.DatabaseBeanLocal;
import ejbs.beans.Usuario;
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
public class ListUsersAction extends org.apache.struts.action.Action {
    @EJB
    private DatabaseBeanLocal ejbDatabase;
    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ejbDatabase=(DatabaseBeanLocal) request.getServletContext().getAttribute("ejbDatabase");
        Collection<Usuario> usuarios = ejbDatabase.getUsuarios();
        request.setAttribute("usuarios", usuarios);
        return mapping.findForward(SUCCESS);
    }
}
