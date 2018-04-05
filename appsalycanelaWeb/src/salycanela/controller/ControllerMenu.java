package salycanela.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import salycanela.model.entities.TabVtsMenu;
import salycanela.model.entities.TabVtsPlato;
import salycanela.model.manager.ManagerMenu;
import salycanela.model.manager.ManagerPlato;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped

public class ControllerMenu {
	private List<TabVtsPlato> listaplatos;
	private List<TabVtsMenu> listamenu;

	private int idplato;
	private String nombremenu;
	private BigDecimal precio1;
	private BigDecimal precio2;
	private int stock;
	private TabVtsPlato plato;
	private TabVtsMenu menu;

	@EJB
	private ManagerPlato managerPlato;
	@EJB
	private ManagerMenu managerMenu;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void CargarPlato(TabVtsPlato plato) {
		idplato = plato.getIdplato();
		nombremenu = plato.getNombreplato();
		precio1 = plato.getPrecioplato();
		precio2 = plato.getPrecioespecialplato();
		this.plato = plato;
	}

	public void CargarMenu(TabVtsMenu menu,TabVtsPlato plato) {
		this.menu = menu;
		stock = plato.getStock();
	}

	public void EditarMenu() {
		try {
			managerMenu.editarMenu(menu.getTabVtsPlato().getIdplato(), stock);
			JSFUtil.crearMensajeInfo("Menú editado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void AgregarMenu() {
		try {
			managerMenu.AgregarMenu(plato, stock);
			JSFUtil.crearMensajeInfo("Menú registrado.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void EliminarMenu(int idmenu) {
		try {
			managerMenu.EliminarMenu(idmenu);
			JSFUtil.crearMensajeInfo("Menú eliminado.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	private void vaciarCampos() {
		listaplatos = managerMenu.findAllAlmuerzos();
		listamenu = managerMenu.findAllMenu();
		nombremenu = null;
		precio1 = null;
		precio2 = null;
		stock = 1;
		plato = null;
		menu = null;
	}

	public List<TabVtsPlato> getListaplatos() {
		return listaplatos;
	}

	public void setListaplatos(List<TabVtsPlato> listaplatos) {
		this.listaplatos = listaplatos;
	}

	public List<TabVtsMenu> getListamenu() {
		return listamenu;
	}

	public void setListamenu(List<TabVtsMenu> listamenu) {
		this.listamenu = listamenu;
	}

	public int getIdplato() {
		return idplato;
	}

	public void setIdplato(int idplato) {
		this.idplato = idplato;
	}

	public String getNombremenu() {
		return nombremenu;
	}

	public void setNombremenu(String nombremenu) {
		this.nombremenu = nombremenu;
	}

	public BigDecimal getPrecio1() {
		return precio1;
	}

	public void setPrecio1(BigDecimal precio1) {
		this.precio1 = precio1;
	}

	public BigDecimal getPrecio2() {
		return precio2;
	}

	public void setPrecio2(BigDecimal precio2) {
		this.precio2 = precio2;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public TabVtsPlato getPlato() {
		return plato;
	}

	public void setPlato(TabVtsPlato plato) {
		this.plato = plato;
	}

	public TabVtsMenu getMenu() {
		return menu;
	}

	public void setMenu(TabVtsMenu menu) {
		this.menu = menu;
	}

}
