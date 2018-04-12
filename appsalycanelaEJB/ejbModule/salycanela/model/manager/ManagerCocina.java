package salycanela.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import salycanela.model.entities.TabVtsCocina;

/**
 * Session Bean implementation class ManagerCaja
 */
@Stateless
@LocalBean
public class ManagerCocina {
	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerCocina() {

	}

	public void agregarCocina(String nombrecocina) throws Exception {

		if (nombrecocina == null)
			throw new Exception("Debe especificar el nombre de la cocina.");
		TabVtsCocina p = new TabVtsCocina();
		p.setNombrecocina(nombrecocina);
		em.persist(p);
	}

	public TabVtsCocina findCocinaById(int idcocina) throws Exception {
		TabVtsCocina p = em.find(TabVtsCocina.class, idcocina);
		return p;
	}

	public void editarCocina(int idcocina, String nombrecocina) throws Exception {
		TabVtsCocina p = findCocinaById(idcocina);
		if (p == null)
			throw new Exception("No existe la cocina especificada.");
		p.setNombrecocina(nombrecocina);
		em.merge(p);
	}

	public List<TabVtsCocina> findAllCocinas() {
		Query q;
		List<TabVtsCocina> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT p FROM TabVtsCocina p ORDER BY p.idcocina";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

}
