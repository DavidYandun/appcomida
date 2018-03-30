package salycanela.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import salycanela.model.entities.TabAdmTipoUsuario;
import salycanela.model.entities.TabAdmUsuario;
import salycanela.model.entities.TabParametro;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerUsuario() {
		// TODO Auto-generated constructor stub
	}

	public void agregarusuario(String idusuario, int idtipousuario, String nombreusuario, String apellidousuario,
			String correousuario, String passwordusuario, boolean estadousuario) throws Exception {

		TabAdmTipoUsuario tipo = em.find(TabAdmTipoUsuario.class, idtipousuario);
		TabAdmUsuario u = new TabAdmUsuario();
		u.setIdusuario(idusuario);
		u.setTabAdmTipoUsuario(tipo);
		u.setNombreusuario(nombreusuario);
		u.setApellidousuario(apellidousuario);
		u.setCorreousuario(correousuario);
		u.setPasswordusuario(passwordusuario);
		u.setEstadousuario(estadousuario);
		em.persist(u);
	}

	public TabAdmUsuario findusuarioByID(String idusuario) throws Exception {
		TabAdmUsuario u = em.find(TabAdmUsuario.class, idusuario);
		return u;
	}

	public void editarUsuario(String idusuario, int idtipousuario, String nombreusuario, String apellidousuario, String correousuario,
			String passwordusuario, boolean estadousuario) throws Exception {
		TabAdmUsuario u = findusuarioByID(idusuario);
		TabAdmTipoUsuario tipo = em.find(TabAdmTipoUsuario.class, idtipousuario);
		if (u == null)
			throw new Exception("No existe el usuario especificado.");
		u.setTabAdmTipoUsuario(tipo);
		u.setNombreusuario(nombreusuario);
		u.setApellidousuario(apellidousuario);
		u.setCorreousuario(correousuario);
		u.setPasswordusuario(passwordusuario);
		u.setEstadousuario(estadousuario);
		em.merge(u);
	}

	public List<TabAdmUsuario> findAllUsuarios() {
		Query q;
		List<TabAdmUsuario> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT c FROM TabAdmUsuario c ORDER BY c.idusuario";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public List<TabAdmTipoUsuario> findAllTipoUsuarios() {
		Query q;
		List<TabAdmTipoUsuario> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT c FROM TabAdmTipoUsuario c ORDER BY c.idtipousuario";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}


}
