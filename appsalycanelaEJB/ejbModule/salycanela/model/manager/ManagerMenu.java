package salycanela.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import salycanela.model.entities.TabParametro;
import salycanela.model.entities.TabVtsMenu;
import salycanela.model.entities.TabVtsPlato;
import salycanela.model.entities.TabVtsTipoPlato;

/**
 * Session Bean implementation class ManagerMenu
 */
@Stateless
@LocalBean
public class ManagerMenu {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerMenu() {
		// TODO Auto-generated constructor stub
	}

	public List<TabVtsPlato> findAllAlmuerzos() {
		Query q;
		List<TabVtsPlato> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT p FROM TabVtsPlato p WHERE p.tabVtsTipoPlato.idtipoplato=" + 1;
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();

		return listado;
	}

	public List<TabVtsMenu> findAllMenu() {
		Query q;
		List<TabVtsMenu> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT m FROM TabVtsMenu m";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public void AgregarMenu(TabVtsPlato plato, int stock) throws Exception {

		Boolean bandera = false;
		for (TabVtsMenu menu : findAllMenu()) {
			if (menu.getTabVtsPlato().getIdplato().equals(plato.getIdplato()))
				bandera = true;
		}
		if (bandera == true)
			throw new Exception("Este plato ya ha sido registrado");
		TabVtsMenu m = new TabVtsMenu();
		m.setTabVtsPlato(plato);
		m.setNombremenu(plato.getNombreplato());
		m.setPrecio1(plato.getPrecioplato());
		m.setPrecio2(plato.getPrecioespecialplato());
		plato.setStock(stock);
		em.merge(plato);
		em.persist(m);
	}

	public void EliminarMenu(int idmenu) throws Exception {
		try {
			TabVtsMenu menu = em.find(TabVtsMenu.class, idmenu);
			em.remove(menu);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("No se puede eliminar este campo: " + e.getMessage());
		}
	}

	public void editarMenu(int idplato, int stock) throws Exception {
		TabVtsMenu m = em.find(TabVtsMenu.class, idplato);
		TabVtsPlato p= em.find(TabVtsPlato.class, idplato);
		if (m == null)
			throw new Exception("No existe el menú especificado.");
		p.setStock(stock);
		em.merge(p);
	}

}
