package salycanela.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import salycanela.model.entities.TabParametro;

/**
 * Session Bean implementation class Parametro
 */
@Stateless
@LocalBean
public class ManagerParametro {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;
	
    public ManagerParametro() {
        // TODO Auto-generated constructor stub
    }
    
    public void agregarParametro(String nombreparametro, int valorparametro) throws Exception {

		if (nombreparametro == null || nombreparametro.length() == 0)
			throw new Exception("Debe especificar el parametro.");
		TabParametro p = new TabParametro();
		p.setNombreparametro(nombreparametro);
		p.setValorparametro(valorparametro);
		em.persist(p);
	}

	public TabParametro findParametroByName(String nombreparametro) throws Exception {
		TabParametro p = em.find(TabParametro.class, nombreparametro);
		return p;
	}

	public void editarParametro(String nombreparametro, int valorparametro) throws Exception {
		TabParametro p = findParametroByName(nombreparametro);
		if (p == null)
			throw new Exception("No existe el parametro especificado.");
		p.setValorparametro(valorparametro);
		em.merge(p);
	}

	public List<TabParametro> findAllParametros() {
		Query q;
		List<TabParametro> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT p FROM TabParametro p ORDER BY p.nombreparametro";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

}
