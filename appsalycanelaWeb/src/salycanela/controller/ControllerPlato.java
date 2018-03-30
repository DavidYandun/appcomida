package salycanela.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import salycanela.model.entities.TabVtsPlato;
import salycanela.model.entities.TabVtsTipoPlato;
import salycanela.model.manager.ManagerPlato;
import salycanela.model.manager.ManagerTipoplato;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped

public class ControllerPlato {
	private int idplato;
	private int idtipoplato;
	private String nombreplato;
	private String descripcionplato;
	private BigDecimal precioplato;
	private BigDecimal precioespecialplato;
	private boolean estadoplato;
	private int stock;
	private List<TabVtsPlato> lista;
	@EJB
	private ManagerPlato managerPlato;
	@EJB
	private ManagerTipoplato managerTipoPlato;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void AgregarPlato() {
		try {
			managerPlato.agregarPlato(idtipoplato, nombreplato, descripcionplato, precioplato, precioespecialplato,
					estadoplato, stock);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Plato registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarPlato(TabVtsPlato plato) {
		idplato = plato.getIdplato();
		idtipoplato = plato.getTabVtsTipoPlato().getIdtipoplato();
		nombreplato = plato.getNombreplato();
		descripcionplato = plato.getDescripcionplato();
		precioplato = plato.getPrecioplato();
		precioespecialplato = plato.getPrecioespecialplato();
		estadoplato = plato.getEstadoplato();
		stock = plato.getStock();
	}

	public void EditarPlato() {
		try {
			managerPlato.editarPlato(idplato, idtipoplato, nombreplato, descripcionplato, precioplato,
					precioespecialplato, estadoplato, stock);
			JSFUtil.crearMensajeInfo("Plato " + nombreplato + " editado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SelectItem> getListaTipoPlatoSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<TabVtsTipoPlato> listadoTipo = managerTipoPlato.findAllTipoplatos();
		for (TabVtsTipoPlato c : listadoTipo) {
			SelectItem item = new SelectItem(c.getIdtipoplato(), c.getNombretipoplato());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public void vaciarCampos() {
		lista = managerPlato.findAllPlatos();
		idplato = 0;
		idtipoplato = 0;
		nombreplato = null;
		descripcionplato = null;
		precioplato = null;
		precioespecialplato = null;
		estadoplato = true;
		stock = 1;
	}

	public int getIdplato() {
		return idplato;
	}

	public void setIdplato(int idplato) {
		this.idplato = idplato;
	}

	public int getIdtipoplato() {
		return idtipoplato;
	}

	public void setIdtipoplato(int idtipoplato) {
		this.idtipoplato = idtipoplato;
	}

	public String getDescripcionplato() {
		return descripcionplato;
	}

	public void setDescripcionplato(String descripcionplato) {
		this.descripcionplato = descripcionplato;
	}

	public BigDecimal getPrecioplato() {
		return precioplato;
	}

	public void setPrecioplato(BigDecimal precioplato) {
		this.precioplato = precioplato;
	}

	public BigDecimal getPrecioespecialplato() {
		return precioespecialplato;
	}

	public void setPrecioespecialplato(BigDecimal precioespecialplato) {
		this.precioespecialplato = precioespecialplato;
	}

	public boolean isEstadoplato() {
		return estadoplato;
	}

	public void setEstadoplato(boolean estadoplato) {
		this.estadoplato = estadoplato;
	}

	public List<TabVtsPlato> getLista() {
		return lista;
	}

	public void setLista(List<TabVtsPlato> lista) {
		this.lista = lista;
	}

	public String getNombreplato() {
		return nombreplato;
	}

	public void setNombreplato(String nombreplato) {
		this.nombreplato = nombreplato;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}