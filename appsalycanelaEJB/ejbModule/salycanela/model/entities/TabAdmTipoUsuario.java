package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_adm_tipo_usuario database table.
 * 
 */
@Entity
@Table(name="tab_adm_tipo_usuario")
@NamedQuery(name="TabAdmTipoUsuario.findAll", query="SELECT t FROM TabAdmTipoUsuario t")
public class TabAdmTipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_ADM_TIPO_USUARIO_IDTIPOUSUARIO_GENERATOR", sequenceName="TAB_ADM_TIPO_USUARIO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_ADM_TIPO_USUARIO_IDTIPOUSUARIO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idtipousuario;

	@Column(length=300)
	private String descripciontipousuario;

	@Column(length=50)
	private String nombretipousuario;

	//bi-directional many-to-one association to TabAdmUsuario
	@OneToMany(mappedBy="tabAdmTipoUsuario")
	private List<TabAdmUsuario> tabAdmUsuarios;

	public TabAdmTipoUsuario() {
	}

	public Integer getIdtipousuario() {
		return this.idtipousuario;
	}

	public void setIdtipousuario(Integer idtipousuario) {
		this.idtipousuario = idtipousuario;
	}

	public String getDescripciontipousuario() {
		return this.descripciontipousuario;
	}

	public void setDescripciontipousuario(String descripciontipousuario) {
		this.descripciontipousuario = descripciontipousuario;
	}

	public String getNombretipousuario() {
		return this.nombretipousuario;
	}

	public void setNombretipousuario(String nombretipousuario) {
		this.nombretipousuario = nombretipousuario;
	}

	public List<TabAdmUsuario> getTabAdmUsuarios() {
		return this.tabAdmUsuarios;
	}

	public void setTabAdmUsuarios(List<TabAdmUsuario> tabAdmUsuarios) {
		this.tabAdmUsuarios = tabAdmUsuarios;
	}

	public TabAdmUsuario addTabAdmUsuario(TabAdmUsuario tabAdmUsuario) {
		getTabAdmUsuarios().add(tabAdmUsuario);
		tabAdmUsuario.setTabAdmTipoUsuario(this);

		return tabAdmUsuario;
	}

	public TabAdmUsuario removeTabAdmUsuario(TabAdmUsuario tabAdmUsuario) {
		getTabAdmUsuarios().remove(tabAdmUsuario);
		tabAdmUsuario.setTabAdmTipoUsuario(null);

		return tabAdmUsuario;
	}

}