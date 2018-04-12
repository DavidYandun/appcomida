package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tab_vts_transaccion database table.
 * 
 */
@Entity
@Table(name = "tab_vts_transaccion")
@NamedQuery(name = "TabVtsTransaccion.findAll", query = "SELECT t FROM TabVtsTransaccion t")
public class TabVtsTransaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TAB_VTS_TRANSACCION_IDTRANSACCION_GENERATOR", sequenceName = "TAB_VTS_TRANSACCION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_VTS_TRANSACCION_IDTRANSACCION_GENERATOR")
	@Column(unique = true, nullable = false)
	private Integer idtransaccion;

	private Boolean anulatransaccion;

	@Column(length = 500)
	private String descripciontransaccion;

	@Temporal(TemporalType.DATE)
	private Date fechatransaccion;

	private Boolean tipotransaccion;

	private BigDecimal valortransaccion;

	// bi-directional many-to-one association to TabVtsPedido
	@OneToMany(mappedBy = "tabVtsTransaccion", cascade = CascadeType.ALL)
	private List<TabVtsPedido> tabVtsPedidos;

	// bi-directional many-to-one association to TabAdmUsuario
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private TabAdmUsuario tabAdmUsuario;

	// bi-directional many-to-one association to TabVtsCaja
	@ManyToOne
	@JoinColumn(name = "idcaja")
	private TabVtsCaja tabVtsCaja;

	public TabVtsTransaccion() {
	}

	public Integer getIdtransaccion() {
		return this.idtransaccion;
	}

	public void setIdtransaccion(Integer idtransaccion) {
		this.idtransaccion = idtransaccion;
	}

	public Boolean getAnulatransaccion() {
		return this.anulatransaccion;
	}

	public void setAnulatransaccion(Boolean anulatransaccion) {
		this.anulatransaccion = anulatransaccion;
	}

	public String getDescripciontransaccion() {
		return this.descripciontransaccion;
	}

	public void setDescripciontransaccion(String descripciontransaccion) {
		this.descripciontransaccion = descripciontransaccion;
	}

	public Date getFechatransaccion() {
		return this.fechatransaccion;
	}

	public void setFechatransaccion(Date fechatransaccion) {
		this.fechatransaccion = fechatransaccion;
	}

	public Boolean getTipotransaccion() {
		return this.tipotransaccion;
	}

	public void setTipotransaccion(Boolean tipotransaccion) {
		this.tipotransaccion = tipotransaccion;
	}

	public BigDecimal getValortransaccion() {
		return this.valortransaccion;
	}

	public void setValortransaccion(BigDecimal valortransaccion) {
		this.valortransaccion = valortransaccion;
	}

	public List<TabVtsPedido> getTabVtsPedidos() {
		return this.tabVtsPedidos;
	}

	public void setTabVtsPedidos(List<TabVtsPedido> tabVtsPedidos) {
		this.tabVtsPedidos = tabVtsPedidos;
	}

	public TabVtsPedido addTabVtsPedido(TabVtsPedido tabVtsPedido) {
		getTabVtsPedidos().add(tabVtsPedido);
		tabVtsPedido.setTabVtsTransaccion(this);
		return tabVtsPedido;
	}

	public TabVtsPedido removeTabVtsPedido(TabVtsPedido tabVtsPedido) {
		getTabVtsPedidos().remove(tabVtsPedido);
		tabVtsPedido.setTabVtsTransaccion(null);
		return tabVtsPedido;
	}

	public TabAdmUsuario getTabAdmUsuario() {
		return this.tabAdmUsuario;
	}

	public void setTabAdmUsuario(TabAdmUsuario tabAdmUsuario) {
		this.tabAdmUsuario = tabAdmUsuario;
	}

	public TabVtsCaja getTabVtsCaja() {
		return this.tabVtsCaja;
	}

	public void setTabVtsCaja(TabVtsCaja tabVtsCaja) {
		this.tabVtsCaja = tabVtsCaja;
	}

}