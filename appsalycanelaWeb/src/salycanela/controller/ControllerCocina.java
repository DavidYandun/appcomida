package salycanela.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import salycanela.model.entities.TabVtsCocina;
import salycanela.model.manager.ManagerCocina;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped

public class ControllerCocina {
	private int idcocina;
	private String nombrecocina;
	private List<TabVtsCocina> lista;
	@EJB
	private ManagerCocina managerCocina;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void AgregarCocina() {
		try {
			managerCocina.agregarCocina(nombrecocina);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Parámetro registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarCocina(TabVtsCocina cocina) {
		idcocina = cocina.getIdcocina();
		nombrecocina = cocina.getNombrecocina();
	}

	public void EditarCocina() {
		try {
			managerCocina.editarCocina(idcocina, nombrecocina);
			JSFUtil.crearMensajeInfo("Cocina " + nombrecocina + " editada correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void vaciarCampos() {
		lista = managerCocina.findAllCocinas();
		idcocina = 0;
		nombrecocina = null;
	}

	public int getIdcocina() {
		return idcocina;
	}

	public void setIdcocina(int idcocina) {
		this.idcocina = idcocina;
	}

	public String getNombrecocina() {
		return nombrecocina;
	}

	public void setNombrecocina(String nombrecocina) {
		this.nombrecocina = nombrecocina;
	}

	public List<TabVtsCocina> getLista() {
		return lista;
	}

	public void setLista(List<TabVtsCocina> lista) {
		this.lista = lista;
	}

}
