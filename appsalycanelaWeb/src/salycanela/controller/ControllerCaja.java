package salycanela.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import salycanela.model.entities.TabVtsCaja;
import salycanela.model.manager.ManagerCaja;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped

public class ControllerCaja {
	private String idcaja;
	private BigDecimal actualcaja;
	private List<TabVtsCaja> lista;
	@EJB
	private ManagerCaja managerCaja;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void AgregarCaja() {
		try {
			managerCaja.agregarCaja(idcaja, actualcaja);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Parámetro registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarCaja(TabVtsCaja caja) {
		idcaja = caja.getIdcaja();
		actualcaja = caja.getActualcaja();
	}

	public void EditarCaja() {
		try {
			managerCaja.editarCaja(idcaja, actualcaja);
			JSFUtil.crearMensajeInfo("Caja " + idcaja + " editada correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void vaciarCampos() {
		lista = managerCaja.findAllCajas();
		idcaja = null;
		actualcaja = null;
	}

	public String getIdcaja() {
		return idcaja;
	}

	public void setIdcaja(String idcaja) {
		this.idcaja = idcaja;
	}

	public BigDecimal getActualcaja() {
		return actualcaja;
	}

	public void setActualcaja(BigDecimal actualcaja) {
		this.actualcaja = actualcaja;
	}

	public List<TabVtsCaja> getLista() {
		return lista;
	}

	public void setLista(List<TabVtsCaja> lista) {
		this.lista = lista;
	}

}
