package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_adm_usuarios database table.
 * 
 */
@Entity
@Table(name="tab_adm_usuarios")
@NamedQuery(name="TabAdmUsuario.findAll", query="SELECT t FROM TabAdmUsuario t")
public class TabAdmUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=15)
	private String idusuario;

	@Column(length=50)
	private String apellidousuario;

	@Column(length=50)
	private String correousuario;

	private Boolean estadousuario;

	@Column(length=50)
	private String nombreusuario;

	@Column(length=50)
	private String passwordusuario;

	//bi-directional many-to-one association to TabAdmTipoUsuario
	@ManyToOne
	@JoinColumn(name="idtipousuario")
	private TabAdmTipoUsuario tabAdmTipoUsuario;

	//bi-directional many-to-one association to TabVtsFactura
	@OneToMany(mappedBy="tabAdmUsuario")
	private List<TabVtsFactura> tabVtsFacturas;

	//bi-directional many-to-one association to TabVtsPedido
	@OneToMany(mappedBy="tabAdmUsuario")
	private List<TabVtsPedido> tabVtsPedidos;

	//bi-directional many-to-one association to TabVtsTransaccion
	@OneToMany(mappedBy="tabAdmUsuario")
	private List<TabVtsTransaccion> tabVtsTransaccions;

	public TabAdmUsuario() {
	}

	public String getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getApellidousuario() {
		return this.apellidousuario;
	}

	public void setApellidousuario(String apellidousuario) {
		this.apellidousuario = apellidousuario;
	}

	public String getCorreousuario() {
		return this.correousuario;
	}

	public void setCorreousuario(String correousuario) {
		this.correousuario = correousuario;
	}

	public Boolean getEstadousuario() {
		return this.estadousuario;
	}

	public void setEstadousuario(Boolean estadousuario) {
		this.estadousuario = estadousuario;
	}

	public String getNombreusuario() {
		return this.nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getPasswordusuario() {
		return this.passwordusuario;
	}

	public void setPasswordusuario(String passwordusuario) {
		this.passwordusuario = passwordusuario;
	}

	public TabAdmTipoUsuario getTabAdmTipoUsuario() {
		return this.tabAdmTipoUsuario;
	}

	public void setTabAdmTipoUsuario(TabAdmTipoUsuario tabAdmTipoUsuario) {
		this.tabAdmTipoUsuario = tabAdmTipoUsuario;
	}

	public List<TabVtsFactura> getTabVtsFacturas() {
		return this.tabVtsFacturas;
	}

	public void setTabVtsFacturas(List<TabVtsFactura> tabVtsFacturas) {
		this.tabVtsFacturas = tabVtsFacturas;
	}

	public TabVtsFactura addTabVtsFactura(TabVtsFactura tabVtsFactura) {
		getTabVtsFacturas().add(tabVtsFactura);
		tabVtsFactura.setTabAdmUsuario(this);

		return tabVtsFactura;
	}

	public TabVtsFactura removeTabVtsFactura(TabVtsFactura tabVtsFactura) {
		getTabVtsFacturas().remove(tabVtsFactura);
		tabVtsFactura.setTabAdmUsuario(null);

		return tabVtsFactura;
	}

	public List<TabVtsPedido> getTabVtsPedidos() {
		return this.tabVtsPedidos;
	}

	public void setTabVtsPedidos(List<TabVtsPedido> tabVtsPedidos) {
		this.tabVtsPedidos = tabVtsPedidos;
	}

	public TabVtsPedido addTabVtsPedido(TabVtsPedido tabVtsPedido) {
		getTabVtsPedidos().add(tabVtsPedido);
		tabVtsPedido.setTabAdmUsuario(this);

		return tabVtsPedido;
	}

	public TabVtsPedido removeTabVtsPedido(TabVtsPedido tabVtsPedido) {
		getTabVtsPedidos().remove(tabVtsPedido);
		tabVtsPedido.setTabAdmUsuario(null);

		return tabVtsPedido;
	}

	public List<TabVtsTransaccion> getTabVtsTransaccions() {
		return this.tabVtsTransaccions;
	}

	public void setTabVtsTransaccions(List<TabVtsTransaccion> tabVtsTransaccions) {
		this.tabVtsTransaccions = tabVtsTransaccions;
	}

	public TabVtsTransaccion addTabVtsTransaccion(TabVtsTransaccion tabVtsTransaccion) {
		getTabVtsTransaccions().add(tabVtsTransaccion);
		tabVtsTransaccion.setTabAdmUsuario(this);

		return tabVtsTransaccion;
	}

	public TabVtsTransaccion removeTabVtsTransaccion(TabVtsTransaccion tabVtsTransaccion) {
		getTabVtsTransaccions().remove(tabVtsTransaccion);
		tabVtsTransaccion.setTabAdmUsuario(null);

		return tabVtsTransaccion;
	}

}