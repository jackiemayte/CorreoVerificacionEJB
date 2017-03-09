
package ejbs.beans;

import ejbs.exeption.BDException;
import java.util.Collection;
import java.util.Optional;
import javax.ejb.Local;

/**
 *
 * @author mayteEcheverry
 */
@Local
public interface DatabaseBeanLocal {
    public Optional<Usuario2> insertUsuario2(String username, String password,String email) throws BDException;
    public Optional<Usuario> insertUsuario(String username, String password,String email) throws BDException; 
    public boolean findUsuario2(String username) throws BDException;
    public Collection<Usuario> getUsuarios() throws BDException;
    public Collection<Usuario2> getUsuarios2() throws BDException;
    public Optional<Usuario2> findId(String id) throws BDException ;
    public int deleteUsuario(String id)throws BDException;
}
