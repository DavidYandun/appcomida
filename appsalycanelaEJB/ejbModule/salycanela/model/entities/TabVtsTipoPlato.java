package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_vts_tipo_plato database table.
 * 
 */
@Entity
@Table(name="tab_vts_tipo_plato")
@NamedQuery(name="TabVtsTipoPlato.findAll", query="SELECT t FROM TabVtsTipoPlato t")
public class TabVtsTipoPlato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_VTS_TIPO_PLATO_IDTIPOPLATO_GENERATOR", sequenceName="TAB_VTS_TIPO_PLATO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_VTS_TIPO_PLATO_IDTIPOPLATO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idtipoplato;

	@Column(length=300)
	private String descripciontipoplato;

	@Column(length=50)
	private String nombretipoplato;

	//bi-directional many-to-one association to TabVtsDetalleFactura
	@OneToMany(mappedBy="tabVtsTipoPlato")
	private List<TabVtsDetalleFactura> tabVtsDetalleFacturas;

	//bi-directional many-to-one association to TabVtsPlato
	@OneToMany(mappedBy="tabVtsTipoPlato")
	private List<TabVtsPlato> tabVtsPlatos;

	public TabVtsTipoPlato() {
	}

	public Integer getIdtipoplato() {
		return this.idtipoplato;
	}

	public void setIdtipoplato(Integer idtipoplato) {
		this.idtipoplato = idtipoplato;
	}

	public String getDescripciontipoplato() {
		return this.descripciontipoplato;
	}

	public void setDescripciontipoplato(String descripciontipoplato) {
		this.descripciontipoplato = descripciontipoplato;
	}

	public String getNombretipoplato() {
		return this.nombretipoplato;
	}

	public void setNombretipoplato(String nombretipoplato) {
		this.nombretipoplato = nombretipoplato;
	}

	public List<TabVtsDetalleFactura> getTabVtsDetalleFacturas() {
		return this.tabVtsDetalleFacturas;
	}

	public void setTabVtsDetalleFacturas(List<TabVtsDetalleFactura> tabVtsDetalleFacturas) {
		this.tabVtsDetalleFacturas = tabVtsDetalleFacturas;
	}

	public TabVtsDetalleFactura addTabVtsDetalleFactura(TabVtsDetalleFactura tabVtsDetalleFactura) {
		getTabVtsDetalleFacturas().add(tabVtsDetalleFactura);
		tabVtsDetalleFactura.setTabVtsTipoPlato(this);

		return tabVtsDetalleFactura;
	}

	public TabVtsDetalleFactura removeTabVtsDetalleFactura(TabVtsDetalleFactura tabVtsDetalleFactura) {
		getTabVtsDetalleFacturas().remove(tabVtsDetalleFactura);
		tabVtsDetalleFactura.setTabVtsTipoPlato(null);

		return tabVtsDetalleFactura;
	}

	public List<TabVtsPlato> getTabVtsPlatos() {
		return this.tabVtsPlatos;
	}

	public void setTabVtsPlatos(List<TabVtsPlato> tabVtsPlatos) {
		this.tabVtsPlatos = tabVtsPlatos;
	}

	public TabVtsPlato addTabVtsPlato(TabVtsPlato tabVtsPlato) {
		getTabVtsPlatos().add(tabVtsPlato);
		tabVtsPlato.setTabVtsTipoPlato(this);

		return tabVtsPlato;
	}

	public TabVtsPlato removeTabVtsPlato(TabVtsPlato tabVtsPlato) {
		getTabVtsPlatos().remove(tabVtsPlato);
		tabVtsPlato.setTabVtsTipoPlato(null);

		return tabVtsPlato;
	}

}