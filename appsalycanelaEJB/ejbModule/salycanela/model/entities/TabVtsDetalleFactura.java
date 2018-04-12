package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The persistent class for the tab_vts_detalle_factura database table.
 * 
 */
@Entity
@Table(name = "tab_vts_detalle_factura")
@NamedQuery(name = "TabVtsDetalleFactura.findAll", query = "SELECT t FROM TabVtsDetalleFactura t")
public class TabVtsDetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TAB_VTS_DETALLE_FACTURA_IDDF_GENERATOR", sequenceName = "TAB_VTS_DETALLE_FACTURA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_VTS_DETALLE_FACTURA_IDDF_GENERATOR")
	@Column(unique = true, nullable = false)
	private Integer iddf;

	private Integer cantidaddf;

	private BigDecimal valortotaldf;

	private BigDecimal valorunitariodf;

	// bi-directional many-to-one association to TabVtsFactura
	@ManyToOne
	@JoinColumn(name = "idfactura")
	private TabVtsFactura tabVtsFactura;

	// bi-directional many-to-one association to TabVtsTipoPlato
	@ManyToOne
	@JoinColumn(name = "idtipoplato")
	private TabVtsTipoPlato tabVtsTipoPlato;

	public TabVtsDetalleFactura() {
	}

	public Integer getIddf() {
		return this.iddf;
	}

	public void setIddf(Integer iddf) {
		this.iddf = iddf;
	}

	public Integer getCantidaddf() {
		return this.cantidaddf;
	}

	public void setCantidaddf(Integer cantidaddf) {
		this.cantidaddf = cantidaddf;
	}

	public BigDecimal getValortotaldf() {
		return this.valortotaldf;
	}

	public void setValortotaldf(BigDecimal valortotaldf) {
		this.valortotaldf = valortotaldf;
	}

	public BigDecimal getValorunitariodf() {
		return this.valorunitariodf;
	}

	public void setValorunitariodf(BigDecimal valorunitariodf) {
		this.valorunitariodf = valorunitariodf;
	}

	public TabVtsFactura getTabVtsFactura() {
		return this.tabVtsFactura;
	}

	public void setTabVtsFactura(TabVtsFactura tabVtsFactura) {
		this.tabVtsFactura = tabVtsFactura;
	}

	public TabVtsTipoPlato getTabVtsTipoPlato() {
		return this.tabVtsTipoPlato;
	}

	public void setTabVtsTipoPlato(TabVtsTipoPlato tabVtsTipoPlato) {
		this.tabVtsTipoPlato = tabVtsTipoPlato;
	}

}