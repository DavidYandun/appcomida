package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tab_vts_pedido database table.
 * 
 */
@Entity
@Table(name="tab_vts_pedido")
@NamedQuery(name="TabVtsPedido.findAll", query="SELECT t FROM TabVtsPedido t")
public class TabVtsPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer idpedido;

	private Boolean anulapedido;

	private Boolean entregapedido;

	@Temporal(TemporalType.DATE)
	private Date fechapedido;

	private Integer mesapedido;

	private BigDecimal valorpedido;

	//bi-directional many-to-one association to TabVtsDetallePedido
	@OneToMany(mappedBy="tabVtsPedido",cascade=CascadeType.ALL)
	private List<TabVtsDetallePedido> tabVtsDetallePedidos;

	//bi-directional many-to-one association to TabAdmUsuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private TabAdmUsuario tabAdmUsuario;

	//bi-directional many-to-one association to TabVtsTransaccion
	@ManyToOne
	@JoinColumn(name="idtransaccion")
	private TabVtsTransaccion tabVtsTransaccion;

	public TabVtsPedido() {
	}

	public Integer getIdpedido() {
		return this.idpedido;
	}

	public void setIdpedido(Integer idpedido) {
		this.idpedido = idpedido;
	}

	public Boolean getAnulapedido() {
		return this.anulapedido;
	}

	public void setAnulapedido(Boolean anulapedido) {
		this.anulapedido = anulapedido;
	}

	public Boolean getEntregapedido() {
		return this.entregapedido;
	}

	public void setEntregapedido(Boolean entregapedido) {
		this.entregapedido = entregapedido;
	}

	public Date getFechapedido() {
		return this.fechapedido;
	}

	public void setFechapedido(Date fechapedido) {
		this.fechapedido = fechapedido;
	}

	public Integer getMesapedido() {
		return this.mesapedido;
	}

	public void setMesapedido(Integer mesapedido) {
		this.mesapedido = mesapedido;
	}

	public BigDecimal getValorpedido() {
		return this.valorpedido;
	}

	public void setValorpedido(BigDecimal valorpedido) {
		this.valorpedido = valorpedido;
	}

	public List<TabVtsDetallePedido> getTabVtsDetallePedidos() {
		return this.tabVtsDetallePedidos;
	}

	public void setTabVtsDetallePedidos(List<TabVtsDetallePedido> tabVtsDetallePedidos) {
		this.tabVtsDetallePedidos = tabVtsDetallePedidos;
	}

	public TabVtsDetallePedido addTabVtsDetallePedido(TabVtsDetallePedido tabVtsDetallePedido) {
		getTabVtsDetallePedidos().add(tabVtsDetallePedido);
		tabVtsDetallePedido.setTabVtsPedido(this);

		return tabVtsDetallePedido;
	}

	public TabVtsDetallePedido removeTabVtsDetallePedido(TabVtsDetallePedido tabVtsDetallePedido) {
		getTabVtsDetallePedidos().remove(tabVtsDetallePedido);
		tabVtsDetallePedido.setTabVtsPedido(null);

		return tabVtsDetallePedido;
	}

	public TabAdmUsuario getTabAdmUsuario() {
		return this.tabAdmUsuario;
	}

	public void setTabAdmUsuario(TabAdmUsuario tabAdmUsuario) {
		this.tabAdmUsuario = tabAdmUsuario;
	}

	public TabVtsTransaccion getTabVtsTransaccion() {
		return this.tabVtsTransaccion;
	}

	public void setTabVtsTransaccion(TabVtsTransaccion tabVtsTransaccion) {
		this.tabVtsTransaccion = tabVtsTransaccion;
	}

}