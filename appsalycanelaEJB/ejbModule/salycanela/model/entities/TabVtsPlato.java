package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_vts_plato database table.
 * 
 */
@Entity
@Table(name="tab_vts_plato")
@NamedQuery(name="TabVtsPlato.findAll", query="SELECT t FROM TabVtsPlato t")
public class TabVtsPlato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_VTS_PLATO_IDPLATO_GENERATOR", sequenceName="TAB_VTS_PLATO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_VTS_PLATO_IDPLATO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idplato;

	@Column(length=300)
	private String descripcionplato;

	private Boolean estadoplato;
	
	private Boolean menu;

	@Column(length=50)
	private String nombreplato;

	private BigDecimal precioespecialplato;

	private BigDecimal precioplato;

	private Integer stock;

	//bi-directional many-to-one association to TabVtsDetallePedido
	@OneToMany(mappedBy="tabVtsPlato")
	private List<TabVtsDetallePedido> tabVtsDetallePedidos;

	//bi-directional many-to-one association to TabVtsMenu
	@OneToMany(mappedBy="tabVtsPlato")
	private List<TabVtsMenu> tabVtsMenus;

	//bi-directional many-to-one association to TabVtsTipoPlato
	@ManyToOne
	@JoinColumn(name="idtipoplato")
	private TabVtsTipoPlato tabVtsTipoPlato;

	public TabVtsPlato() {
	}

	public Integer getIdplato() {
		return this.idplato;
	}

	public void setIdplato(Integer idplato) {
		this.idplato = idplato;
	}

	public String getDescripcionplato() {
		return this.descripcionplato;
	}

	public void setDescripcionplato(String descripcionplato) {
		this.descripcionplato = descripcionplato;
	}

	public Boolean getEstadoplato() {
		return this.estadoplato;
	}

	public void setEstadoplato(Boolean estadoplato) {
		this.estadoplato = estadoplato;
	}
	public Boolean getMenu() {
		return this.menu;
	}

	public void setMenu(Boolean menu) {
		this.menu = menu;
	}

	public String getNombreplato() {
		return this.nombreplato;
	}

	public void setNombreplato(String nombreplato) {
		this.nombreplato = nombreplato;
	}

	public BigDecimal getPrecioespecialplato() {
		return this.precioespecialplato;
	}

	public void setPrecioespecialplato(BigDecimal precioespecialplato) {
		this.precioespecialplato = precioespecialplato;
	}

	public BigDecimal getPrecioplato() {
		return this.precioplato;
	}

	public void setPrecioplato(BigDecimal precioplato) {
		this.precioplato = precioplato;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<TabVtsDetallePedido> getTabVtsDetallePedidos() {
		return this.tabVtsDetallePedidos;
	}

	public void setTabVtsDetallePedidos(List<TabVtsDetallePedido> tabVtsDetallePedidos) {
		this.tabVtsDetallePedidos = tabVtsDetallePedidos;
	}

	public TabVtsDetallePedido addTabVtsDetallePedido(TabVtsDetallePedido tabVtsDetallePedido) {
		getTabVtsDetallePedidos().add(tabVtsDetallePedido);
		tabVtsDetallePedido.setTabVtsPlato(this);

		return tabVtsDetallePedido;
	}

	public TabVtsDetallePedido removeTabVtsDetallePedido(TabVtsDetallePedido tabVtsDetallePedido) {
		getTabVtsDetallePedidos().remove(tabVtsDetallePedido);
		tabVtsDetallePedido.setTabVtsPlato(null);

		return tabVtsDetallePedido;
	}

	public List<TabVtsMenu> getTabVtsMenus() {
		return this.tabVtsMenus;
	}

	public void setTabVtsMenus(List<TabVtsMenu> tabVtsMenus) {
		this.tabVtsMenus = tabVtsMenus;
	}

	public TabVtsMenu addTabVtsMenus(TabVtsMenu tabVtsMenus) {
		getTabVtsMenus().add(tabVtsMenus);
		tabVtsMenus.setTabVtsPlato(this);

		return tabVtsMenus;
	}

	public TabVtsMenu removeTabVtsMenus(TabVtsMenu tabVtsMenus) {
		getTabVtsMenus().remove(tabVtsMenus);
		tabVtsMenus.setTabVtsPlato(null);

		return tabVtsMenus;
	}

	public TabVtsTipoPlato getTabVtsTipoPlato() {
		return this.tabVtsTipoPlato;
	}

	public void setTabVtsTipoPlato(TabVtsTipoPlato tabVtsTipoPlato) {
		this.tabVtsTipoPlato = tabVtsTipoPlato;
	}

}