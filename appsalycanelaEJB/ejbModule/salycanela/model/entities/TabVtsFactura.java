package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tab_vts_factura database table.
 * 
 */
@Entity
@Table(name = "tab_vts_factura")
@NamedQuery(name = "TabVtsFactura.findAll", query = "SELECT t FROM TabVtsFactura t")
public class TabVtsFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private Integer idfactura;

	private Boolean anulafactura;

	@Temporal(TemporalType.DATE)
	private Date fechafactura;

	private BigDecimal ivafactura;

	private BigDecimal subtotalfactura;

	private BigDecimal totalfactura;

	// bi-directional many-to-one association to TabVtsDetalleFactura
	@OneToMany(mappedBy = "tabVtsFactura", cascade = CascadeType.ALL)
	private List<TabVtsDetalleFactura> tabVtsDetalleFacturas;

	// bi-directional many-to-one association to TabAdmUsuario
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private TabAdmUsuario tabAdmUsuario;

	// bi-directional many-to-one association to TabVtsCliente
	@ManyToOne
	@JoinColumn(name = "idcliente")
	private TabVtsCliente tabVtsCliente;

	public TabVtsFactura() {
	}

	public Integer getIdfactura() {
		return this.idfactura;
	}

	public void setIdfactura(Integer idfactura) {
		this.idfactura = idfactura;
	}

	public Boolean getAnulafactura() {
		return this.anulafactura;
	}

	public void setAnulafactura(Boolean anulafactura) {
		this.anulafactura = anulafactura;
	}

	public Date getFechafactura() {
		return this.fechafactura;
	}

	public void setFechafactura(Date fechafactura) {
		this.fechafactura = fechafactura;
	}

	public BigDecimal getIvafactura() {
		return this.ivafactura;
	}

	public void setIvafactura(BigDecimal ivafactura) {
		this.ivafactura = ivafactura;
	}

	public BigDecimal getSubtotalfactura() {
		return this.subtotalfactura;
	}

	public void setSubtotalfactura(BigDecimal subtotalfactura) {
		this.subtotalfactura = subtotalfactura;
	}

	public BigDecimal getTotalfactura() {
		return this.totalfactura;
	}

	public void setTotalfactura(BigDecimal totalfactura) {
		this.totalfactura = totalfactura;
	}

	public List<TabVtsDetalleFactura> getTabVtsDetalleFacturas() {
		return this.tabVtsDetalleFacturas;
	}

	public void setTabVtsDetalleFacturas(List<TabVtsDetalleFactura> tabVtsDetalleFacturas) {
		this.tabVtsDetalleFacturas = tabVtsDetalleFacturas;
	}

	public TabVtsDetalleFactura addTabVtsDetalleFactura(TabVtsDetalleFactura tabVtsDetalleFactura) {
		getTabVtsDetalleFacturas().add(tabVtsDetalleFactura);
		tabVtsDetalleFactura.setTabVtsFactura(this);

		return tabVtsDetalleFactura;
	}

	public TabVtsDetalleFactura removeTabVtsDetalleFactura(TabVtsDetalleFactura tabVtsDetalleFactura) {
		getTabVtsDetalleFacturas().remove(tabVtsDetalleFactura);
		tabVtsDetalleFactura.setTabVtsFactura(null);

		return tabVtsDetalleFactura;
	}

	public TabAdmUsuario getTabAdmUsuario() {
		return this.tabAdmUsuario;
	}

	public void setTabAdmUsuario(TabAdmUsuario tabAdmUsuario) {
		this.tabAdmUsuario = tabAdmUsuario;
	}

	public TabVtsCliente getTabVtsCliente() {
		return this.tabVtsCliente;
	}

	public void setTabVtsCliente(TabVtsCliente tabVtsCliente) {
		this.tabVtsCliente = tabVtsCliente;
	}

}