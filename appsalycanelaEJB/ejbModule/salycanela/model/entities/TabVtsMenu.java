package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the tab_vts_menu database table.
 * 
 */
@Entity
@Table(name="tab_vts_menu")
@NamedQuery(name="TabVtsMenu.findAll", query="SELECT t FROM TabVtsMenu t")
public class TabVtsMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer idmenu;

	@Column(length=50)
	private String nombremenu;

	private BigDecimal precio1;

	private BigDecimal precio2;

	private Integer stock;

	//bi-directional many-to-one association to TabVtsPlato
	@ManyToOne
	@JoinColumn(name="idplato")
	private TabVtsPlato tabVtsPlato;

	public TabVtsMenu() {
	}

	public Integer getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(Integer idmenu) {
		this.idmenu = idmenu;
	}

	public String getNombremenu() {
		return this.nombremenu;
	}

	public void setNombremenu(String nombremenu) {
		this.nombremenu = nombremenu;
	}

	public BigDecimal getPrecio1() {
		return this.precio1;
	}

	public void setPrecio1(BigDecimal precio1) {
		this.precio1 = precio1;
	}

	public BigDecimal getPrecio2() {
		return this.precio2;
	}

	public void setPrecio2(BigDecimal precio2) {
		this.precio2 = precio2;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public TabVtsPlato getTabVtsPlato() {
		return this.tabVtsPlato;
	}

	public void setTabVtsPlato(TabVtsPlato tabVtsPlato) {
		this.tabVtsPlato = tabVtsPlato;
	}

}