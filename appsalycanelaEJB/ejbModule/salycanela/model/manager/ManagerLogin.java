package salycanela.model.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import salycanela.model.entities.TabAdmUsuario;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerLogin() {
		// TODO Auto-generated constructor stub
	}

	public TabAdmUsuario findUsuario(String cedulausuario) throws Exception {
		TabAdmUsuario u = em.find(TabAdmUsuario.class, cedulausuario);
		return u;
	}

	public boolean comprobarUsuario(String cedulausuario, String passwordusuario) throws Exception {
		TabAdmUsuario u = findUsuario(cedulausuario);
		if (u == null)
			throw new Exception("No existe el usuario " + cedulausuario);
		if (u.getPasswordusuario().equals(passwordusuario))
			return true;
		throw new Exception("Contraseña no válida.");
	}
}
