package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_vts_cliente database table.
 * 
 */
@Entity
@Table(name="tab_vts_cliente")
@NamedQuery(name="TabVtsCliente.findAll", query="SELECT t FROM TabVtsCliente t")
public class TabVtsCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=15)
	private String idcliente;

	@Column(length=50)
	private String apellidocliente;

	@Column(length=50)
	private String correocliente;

	@Column(length=50)
	private String direccioncliente;

	private Boolean estadocliente;

	@Column(length=50)
	private String nombrecliente;

	@Column(length=15)
	private String telefonocliente;

	//bi-directional many-to-one association to TabVtsFactura
	@OneToMany(mappedBy="tabVtsCliente")
	private List<TabVtsFactura> tabVtsFacturas;

	public TabVtsCliente() {
	}

	public String getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public String getApellidocliente() {
		return this.apellidocliente;
	}

	public void setApellidocliente(String apellidocliente) {
		this.apellidocliente = apellidocliente;
	}

	public String getCorreocliente() {
		return this.correocliente;
	}

	public void setCorreocliente(String correocliente) {
		this.correocliente = correocliente;
	}

	public String getDireccioncliente() {
		return this.direccioncliente;
	}

	public void setDireccioncliente(String direccioncliente) {
		this.direccioncliente = direccioncliente;
	}

	public Boolean getEstadocliente() {
		return this.estadocliente;
	}

	public void setEstadocliente(Boolean estadocliente) {
		this.estadocliente = estadocliente;
	}

	public String getNombrecliente() {
		return this.nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getTelefonocliente() {
		return this.telefonocliente;
	}

	public void setTelefonocliente(String telefonocliente) {
		this.telefonocliente = telefonocliente;
	}

	public List<TabVtsFactura> getTabVtsFacturas() {
		return this.tabVtsFacturas;
	}

	public void setTabVtsFacturas(List<TabVtsFactura> tabVtsFacturas) {
		this.tabVtsFacturas = tabVtsFacturas;
	}

	public TabVtsFactura addTabVtsFactura(TabVtsFactura tabVtsFactura) {
		getTabVtsFacturas().add(tabVtsFactura);
		tabVtsFactura.setTabVtsCliente(this);

		return tabVtsFactura;
	}

	public TabVtsFactura removeTabVtsFactura(TabVtsFactura tabVtsFactura) {
		getTabVtsFacturas().remove(tabVtsFactura);
		tabVtsFactura.setTabVtsCliente(null);

		return tabVtsFactura;
	}

}