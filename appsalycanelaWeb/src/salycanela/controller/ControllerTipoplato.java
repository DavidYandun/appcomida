package salycanela.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import salycanela.model.entities.TabVtsTipoPlato;
import salycanela.model.manager.ManagerTipoplato;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerTipoplato {
	private int idtipoplato;
	private String nombretipoplato;
	private String descripciontipoplato;
	private List<TabVtsTipoPlato> lista;

	@EJB
	private ManagerTipoplato managerTipoplato;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();

	}

	public void AgregarTipoplato() {
		try {
			managerTipoplato.agregarTipoplato(nombretipoplato, descripciontipoplato);
			JSFUtil.crearMensajeInfo("Tipo de plato registrado.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarTipoplato(TabVtsTipoPlato tipoplato) {
		idtipoplato = tipoplato.getIdtipoplato();
		nombretipoplato = tipoplato.getNombretipoplato();
		descripciontipoplato = tipoplato.getDescripciontipoplato();
	}

	public void EditarTipoplato() {
		try {
			managerTipoplato.editarTipoplato(idtipoplato, nombretipoplato, descripciontipoplato);
			JSFUtil.crearMensajeInfo("Tipo de plato" + idtipoplato + " editado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void vaciarCampos() {
		lista = managerTipoplato.findAllTipoplatos();
		idtipoplato = 0;
		nombretipoplato = null;
		descripciontipoplato = null;
	}

	public int getIdtipoplato() {
		return idtipoplato;
	}

	public void setIdtipoplato(int idtipoplato) {
		this.idtipoplato = idtipoplato;
	}

	public String getNombretipoplato() {
		return nombretipoplato;
	}

	public void setNombretipoplato(String nombretipoplato) {
		this.nombretipoplato = nombretipoplato;
	}

	public String getDescripciontipoplato() {
		return descripciontipoplato;
	}

	public void setDescripciontipoplato(String descripciontipoplato) {
		this.descripciontipoplato = descripciontipoplato;
	}

	public List<TabVtsTipoPlato> getLista() {
		return lista;
	}

	public void setLista(List<TabVtsTipoPlato> lista) {
		this.lista = lista;
	}

}
