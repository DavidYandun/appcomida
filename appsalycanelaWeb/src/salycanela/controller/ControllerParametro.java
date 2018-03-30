package salycanela.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import salycanela.model.entities.TabParametro;
import salycanela.model.manager.ManagerParametro;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerParametro {
	private String nombreparametro;
	private int valorparametro;
	private List<TabParametro> lista;
	@EJB
	private ManagerParametro managerParametro;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void AgregarParametro() {
		try {
			managerParametro.agregarParametro(nombreparametro, valorparametro);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Parámetro registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarParametro(TabParametro parametro) {
		nombreparametro = parametro.getNombreparametro();
		valorparametro = parametro.getValorparametro();
	}

	public void EditarParametro() {
		try {
			managerParametro.editarParametro(nombreparametro, valorparametro);
			JSFUtil.crearMensajeInfo("Parámetro " + nombreparametro + " editado correctamente.");
			vaciarCampos();

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void vaciarCampos() {
		lista = managerParametro.findAllParametros();
		nombreparametro = null;
		valorparametro = 0;
	}

	public String getNombreparametro() {
		return nombreparametro;
	}

	public void setNombreparametro(String nombreparametro) {
		this.nombreparametro = nombreparametro;
	}

	public int getValorparametro() {
		return valorparametro;
	}

	public void setValorparametro(int valorparametro) {
		this.valorparametro = valorparametro;
	}

	public List<TabParametro> getLista() {
		return lista;
	}

	public void setLista(List<TabParametro> lista) {
		this.lista = lista;
	}

}
