package salycanela.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import salycanela.model.entities.TabVtsCaja;

/**
 * Session Bean implementation class ManagerCaja
 */
@Stateless
@LocalBean
public class ManagerCaja {
	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerCaja() {

	}

	public void agregarCaja(String idcaja, BigDecimal actualcaja) throws Exception {

		if (idcaja == null || idcaja.length() == 0)
			throw new Exception("Debe especificar la ID de la caja.");
		TabVtsCaja p = new TabVtsCaja();
		p.setIdcaja(idcaja);
		p.setActualcaja(actualcaja);
		em.persist(p);
	}

	public TabVtsCaja findCajaById(String idcaja) throws Exception {
		TabVtsCaja p = em.find(TabVtsCaja.class, idcaja);
		return p;
	}

	public void editarCaja(String idcaja, BigDecimal actualcaja) throws Exception {
		TabVtsCaja p = findCajaById(idcaja);
		if (p == null)
			throw new Exception("No existe la caja especificada.");
		p.setActualcaja(actualcaja);
		em.merge(p);
	}

	public List<TabVtsCaja> findAllCajas() {
		Query q;
		List<TabVtsCaja> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT p FROM TabVtsCaja p ORDER BY p.idcaja";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

}
