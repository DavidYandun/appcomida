package salycanela.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import salycanela.model.entities.TabVtsCliente;
import salycanela.model.manager.ManagerCliente;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerCliente {
	private String idcliente;
	private String nombrecliente;
	private String apellidocliente;
	private String direccioncliente;
	private String telefonocliente;
	private String correocliente;
	private Boolean estadocliente;
	private List<TabVtsCliente> lista;

	@EJB
	private ManagerCliente managerCliente;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();
	}

	public void AgregarCliente() {
		try {
			managerCliente.agregarCliente(idcliente, nombrecliente, apellidocliente, direccioncliente, telefonocliente,
					correocliente, true);
			JSFUtil.crearMensajeInfo("Cliente registrado.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarCliente(TabVtsCliente cliente) {
		idcliente = cliente.getIdcliente();
		nombrecliente = cliente.getNombrecliente();
		apellidocliente = cliente.getApellidocliente();
		direccioncliente = cliente.getDireccioncliente();
		telefonocliente = cliente.getTelefonocliente();
		correocliente = cliente.getCorreocliente();
		estadocliente = cliente.getEstadocliente();
	}

	public void EditarCliente() {
		try {
			managerCliente.editarCliente(idcliente, nombrecliente, apellidocliente, direccioncliente, telefonocliente,
					correocliente, estadocliente);
			JSFUtil.crearMensajeInfo("Cliente con cédula" + idcliente + " editado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void vaciarCampos() {
		lista = managerCliente.findAllClientes();
		idcliente = null;
		nombrecliente = null;
		apellidocliente = null;
		direccioncliente = null;
		telefonocliente = null;
		correocliente = null;
		estadocliente = null;
	}

	public String getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getApellidocliente() {
		return apellidocliente;
	}

	public void setApellidocliente(String apellidocliente) {
		this.apellidocliente = apellidocliente;
	}

	public String getDireccioncliente() {
		return direccioncliente;
	}

	public void setDireccioncliente(String direccioncliente) {
		this.direccioncliente = direccioncliente;
	}

	public String getTelefonocliente() {
		return telefonocliente;
	}

	public void setTelefonocliente(String telefonocliente) {
		this.telefonocliente = telefonocliente;
	}

	public String getCorreocliente() {
		return correocliente;
	}

	public void setCorreocliente(String correocliente) {
		this.correocliente = correocliente;
	}

	public Boolean getEstadocliente() {
		return estadocliente;
	}

	public void setEstadocliente(Boolean estadocliente) {
		this.estadocliente = estadocliente;
	}

	public List<TabVtsCliente> getLista() {
		return lista;
	}

	public void setLista(List<TabVtsCliente> lista) {
		this.lista = lista;
	}

}
