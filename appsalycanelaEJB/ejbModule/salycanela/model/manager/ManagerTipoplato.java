package salycanela.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import salycanela.model.entities.TabVtsCliente;
import salycanela.model.entities.TabVtsTipoPlato;

/**
 * Session Bean implementation class ManagerCategoriaplato
 */
@Stateless
@LocalBean
public class ManagerTipoplato {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerTipoplato() {
		// TODO Auto-generated constructor stub
	}

	public void agregarTipoplato(String nombretipoplato, String descripciontipoplato) throws Exception {

		Boolean bandera = false;
		for (TabVtsTipoPlato tipo : findAllTipoplatos()) {
			if (tipo.getNombretipoplato().equals(nombretipoplato))
				bandera = true;
		}
		if (bandera == true)
			throw new Exception("Este tipo de plato ya ha sido registrado");
		if (nombretipoplato == null || nombretipoplato.length() == 0)
			throw new Exception("Debe especificar el tipo de plato.");
		TabVtsTipoPlato tp = new TabVtsTipoPlato();
		tp.setNombretipoplato(nombretipoplato);
		tp.setDescripciontipoplato(descripciontipoplato);
		em.persist(tp);
	}

	public TabVtsTipoPlato findTipoplatoByID(int idtipoplato) throws Exception {
		TabVtsTipoPlato tp = em.find(TabVtsTipoPlato.class, idtipoplato);
		return tp;
	}

	public void editarTipoplato(int idtipoplato, String nombretipoplato, String descripciontipoplato) throws Exception {
		TabVtsTipoPlato tp = findTipoplatoByID(idtipoplato);
		if (tp == null)
			throw new Exception("No existe el tipo de plato especificado.");
		tp.setNombretipoplato(nombretipoplato);
		tp.setDescripciontipoplato(descripciontipoplato);
		em.merge(tp);
	}

	public List<TabVtsTipoPlato> findAllTipoplatos() {
		Query q;
		List<TabVtsTipoPlato> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT tp FROM TabVtsTipoPlato tp ORDER BY tp.nombretipoplato";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

}
