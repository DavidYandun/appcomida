package salycanela.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import salycanela.model.entities.TabVtsPlato;
import salycanela.model.entities.TabVtsTipoPlato;

/**
 * Session Bean implementation class ManagerPlato
 */
@Stateless
@LocalBean
public class ManagerPlato {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerPlato() {
		// TODO Auto-generated constructor stub
	}

	public void agregarPlato(int idtipoplato, String nombreplato, String descripcionplato, BigDecimal precioplato,
			BigDecimal precioespecialplato, boolean estadoplato, int stock, boolean menu, String foto)
			throws Exception {

		Boolean bandera = false;
		for (TabVtsPlato plato : findAllPlatos()) {
			if (plato.getNombreplato().equals(nombreplato))
				bandera = true;
		}
		if (bandera == true)
			throw new Exception("Este plato ya ha sido registrado");
		if (nombreplato == null || nombreplato.length() == 0)
			throw new Exception("Debe especificar el nombre del plato.");
		TabVtsTipoPlato tipo = em.find(TabVtsTipoPlato.class, idtipoplato);
		TabVtsPlato p = new TabVtsPlato();
		p.setTabVtsTipoPlato(tipo);
		p.setNombreplato(nombreplato);
		p.setDescripcionplato(descripcionplato);
		p.setPrecioplato(precioplato);
		p.setPrecioespecialplato(precioespecialplato);
		p.setEstadoplato(estadoplato);
		p.setStock(stock);
		p.setMenu(menu);
		p.setFoto(foto);
		em.persist(p);
	}

	public TabVtsPlato findPlatoById(int idplato) throws Exception {
		TabVtsPlato p = em.find(TabVtsPlato.class, idplato);
		return p;
	}

	public void editarPlato(int idplato, int idtipoplato, String nombreplato, String descripcionplato,
			BigDecimal precioplato, BigDecimal precioespecialplato, boolean estadoplato, int stock, boolean menu,
			String foto) throws Exception {

		TabVtsPlato p = findPlatoById(idplato);
		if (p == null)
			throw new Exception("No existe el plato especificado.");

		int bandera = 0;
		for (TabVtsPlato plato : findAllPlatos()) {
			if (plato.getNombreplato().equals(nombreplato))
				bandera++;
		}
		if (bandera > 1)
			throw new Exception("Este plato ya ha sido registrado con este nombre");
		TabVtsTipoPlato tipo = em.find(TabVtsTipoPlato.class, idtipoplato);
		p.setTabVtsTipoPlato(tipo);
		p.setNombreplato(nombreplato);
		p.setDescripcionplato(descripcionplato);
		p.setPrecioplato(precioplato);
		p.setPrecioespecialplato(precioespecialplato);
		p.setEstadoplato(estadoplato);
		p.setStock(stock);
		p.setMenu(menu);
		p.setFoto(foto);
		em.merge(p);
	}

	public List<TabVtsPlato> findAllPlatos() {
		Query q;
		List<TabVtsPlato> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT p FROM TabVtsPlato p ORDER BY p.idplato";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public List<TabVtsPlato> findAllPlatosTipo(int tipoplato) {
		Query q;
		List<TabVtsPlato> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT p FROM TabVtsPlato p WHERE p.tabVtsTipoPlato.idtipoplato=" + tipoplato
				+ " AND p.menu=false";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public List<TabVtsPlato> findAllMenu(int tipoplato) {
		Query q;
		List<TabVtsPlato> listado;
		String sentenciaSQL = "SELECT p FROM TabVtsPlato p WHERE p.tabVtsTipoPlato.idtipoplato=" + tipoplato
				+ " AND p.menu=true ORDER BY p.nombreplato";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public void AgregarMenu(TabVtsPlato plato, int stock) throws Exception {
		if (plato.getMenu() == true)
			throw new Exception("Este plato ya ha sido agregado al menú");
		plato.setMenu(true);
		plato.setStock(stock);
		em.merge(plato);
	}

	public void EliminarMenu(TabVtsPlato plato) throws Exception {
		if (plato.getMenu() == false)
			throw new Exception("Este plato ya ha sido quitado al menú");
		plato.setMenu(false);
		em.merge(plato);
	}

	public void EditarMenu(TabVtsPlato plato, int stock) throws Exception {
		if (plato == null)
			throw new Exception("No existe el menú especificado.");
		plato.setStock(stock);
		em.merge(plato);
	}
}
