package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tab_vts_cocina database table.
 * 
 */
@Entity
@Table(name = "tab_vts_cocina")
@NamedQuery(name = "TabVtsCocina.findAll", query = "SELECT t FROM TabVtsCocina t")
public class TabVtsCocina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TAB_VTS_COCINA_IDCOCINA_GENERATOR", sequenceName = "TAB_VTS_COCINA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_VTS_COCINA_IDCOCINA_GENERATOR")
	@Column(unique = true, nullable = false)
	private Integer idcocina;

	@Column(length = 50)
	private String nombrecocina;

	// bi-directional many-to-one association to TabVtsPedido
	@OneToMany(mappedBy = "tabVtsCocina", cascade = CascadeType.ALL)
	private List<TabVtsPedido> tabVtsPedidos;

	public TabVtsCocina() {
	}

	public Integer getIdcocina() {
		return this.idcocina;
	}

	public void setIdcocina(Integer idcocina) {
		this.idcocina = idcocina;
	}

	public String getNombrecocina() {
		return this.nombrecocina;
	}

	public void setNombrecocina(String nombrecocina) {
		this.nombrecocina = nombrecocina;
	}

	public List<TabVtsPedido> getTabVtsPedidos() {
		return this.tabVtsPedidos;
	}

	public void setTabVtsPedidos(List<TabVtsPedido> tabVtsPedidos) {
		this.tabVtsPedidos = tabVtsPedidos;
	}

	public TabVtsPedido addTabVtsPedido(TabVtsPedido tabVtsPedido) {
		getTabVtsPedidos().add(tabVtsPedido);
		tabVtsPedido.setTabVtsCocina(this);

		return tabVtsPedido;
	}

	public TabVtsPedido removeTabVtsPedido(TabVtsPedido tabVtsPedido) {
		getTabVtsPedidos().remove(tabVtsPedido);
		tabVtsPedido.setTabVtsCocina(null);

		return tabVtsPedido;
	}

}