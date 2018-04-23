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
	private boolean menu;
	private String foto;
	private List<TabVtsPlato> lista;
	private List<TabVtsPlato> filtroPlatos;

	private List<TabVtsPlato> listaalmuerzos;
	private List<TabVtsPlato> listamenu;

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
					estadoplato, stock, menu, foto);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Plato registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	public void AgregarAlmuerzo() {
		try {
			managerPlato.agregarPlato(1, nombreplato, descripcionplato, precioplato, precioespecialplato,
					estadoplato, stock, menu, foto);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Almuerzo registrado.");
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
		menu = plato.getMenu();
		foto = plato.getFoto();
	}

	public void EditarPlato() {
		try {
			managerPlato.editarPlato(idplato, idtipoplato, nombreplato, descripcionplato, precioplato,
					precioespecialplato, estadoplato, stock, menu, foto);
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

	public void AgregarMenu() {
		try {
			TabVtsPlato plato = managerPlato.findPlatoById(idplato);
			managerPlato.AgregarMenu(plato, stock);
			JSFUtil.crearMensajeInfo("Menú agregado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void EditarMenu() {
		try {
			TabVtsPlato plato = managerPlato.findPlatoById(idplato);
			managerPlato.EditarMenu(plato, stock);
			JSFUtil.crearMensajeInfo("Menú editado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void EliminarMenu(TabVtsPlato plato) {
		try {
			managerPlato.EliminarMenu(plato);
			JSFUtil.crearMensajeInfo("Menú eliminado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void vaciarCampos() {
		actualizarTablas();
		idplato = 0;
		idtipoplato = 0;
		nombreplato = null;
		descripcionplato = null;
		precioplato = null;
		precioespecialplato = null;
		estadoplato = true;
		stock = 1;
		menu = false;
		foto = null;
	}

	public void actualizarTablas() {
		lista = managerPlato.findAllPlatos();
		listaalmuerzos = managerPlato.findAllPlatosTipo(1);
		listamenu = managerPlato.findAllMenu(1);
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

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public List<TabVtsPlato> getListaalmuerzos() {
		return listaalmuerzos;
	}

	public void setListaalmuerzos(List<TabVtsPlato> listaalmuerzos) {
		this.listaalmuerzos = listaalmuerzos;
	}

	public List<TabVtsPlato> getListamenu() {
		return listamenu;
	}

	public void setListamenu(List<TabVtsPlato> listamenu) {
		this.listamenu = listamenu;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<TabVtsPlato> getFiltroPlatos() {
		return filtroPlatos;
	}

	public void setFiltroPlatos(List<TabVtsPlato> filtroPlatos) {
		this.filtroPlatos = filtroPlatos;
	}

}
