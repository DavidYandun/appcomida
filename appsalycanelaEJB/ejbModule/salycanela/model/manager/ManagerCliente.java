package salycanela.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import salycanela.model.entities.TabVtsCliente;

/**
 * Session Bean implementation class ManagerCliente
 */
@Stateless
@LocalBean
public class ManagerCliente {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerCliente() {
		// TODO Auto-generated constructor stub
	}

	public void agregarCliente(String idcliente, String nombrecliente, String apellidocliente, String direccioncliente,
			String telefonocliente, String correocliente, Boolean estadocliente) throws Exception {
		if (idcliente == null || idcliente.length() == 0)
			throw new Exception("Debe especificar la cedula.");
		TabVtsCliente c = new TabVtsCliente();
		c.setIdcliente(idcliente);
		c.setNombrecliente(nombrecliente);
		c.setApellidocliente(apellidocliente);
		c.setDireccioncliente(direccioncliente);
		c.setTelefonocliente(telefonocliente);
		c.setCorreocliente(correocliente);
		c.setEstadocliente(estadocliente);
		em.persist(c);
	}

	public TabVtsCliente findClienteByID(String idcliente) throws Exception {
		TabVtsCliente c = em.find(TabVtsCliente.class, idcliente);
		return c;
	}

	public void editarCliente(String idcliente, String nombrecliente, String apellidocliente, String direccioncliente,
			String telefonocliente, String correocliente,Boolean estadocliente) throws Exception {
		TabVtsCliente c = findClienteByID(idcliente);
		if (c == null)
			throw new Exception("No existe el cliente especificado.");
		c.setNombrecliente(nombrecliente);
		c.setApellidocliente(apellidocliente);
		c.setDireccioncliente(direccioncliente);
		c.setTelefonocliente(telefonocliente);
		c.setCorreocliente(correocliente);
		c.setEstadocliente(estadocliente);
		em.merge(c);
	}

	public List<TabVtsCliente> findAllClientes() {
		Query q;
		List<TabVtsCliente> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT c FROM TabVtsCliente c ORDER BY c.apellidocliente";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}
}
