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
			BigDecimal precioespecialplato, boolean estadoplato,int stock) throws Exception {

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

		em.persist(p);
	}

	public TabVtsPlato findPlatoById(int idplato) throws Exception {
		TabVtsPlato p = em.find(TabVtsPlato.class, idplato);
		return p;
	}

	public void editarPlato(int idplato, int idtipoplato, String nombreplato, String descripcionplato,
			BigDecimal precioplato, BigDecimal precioespecialplato, boolean estadoplato, int stock) throws Exception {

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
		em.merge(p);
	}

	public List<TabVtsPlato> findAllPlatos() {
		Query q;
		List<TabVtsPlato> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT p FROM TabVtsPlato p ORDER BY p.nombreplato";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}
}
