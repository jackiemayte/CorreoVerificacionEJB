package ejb;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class EJBModulo
 */
@Stateless
public class EJBModulo implements EJBModuloRemote, EJBModuloLocal {

	@Override
	public String maullar() {
		
		return "Miiiaaaauuuu";
	}

    

}
