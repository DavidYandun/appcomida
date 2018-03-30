package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_vts_caja database table.
 * 
 */
@Entity
@Table(name="tab_vts_caja")
@NamedQuery(name="TabVtsCaja.findAll", query="SELECT t FROM TabVtsCaja t")
public class TabVtsCaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String idcaja;

	private BigDecimal actualcaja;

	//bi-directional many-to-one association to TabVtsTransaccion
	@OneToMany(mappedBy="tabVtsCaja")
	private List<TabVtsTransaccion> tabVtsTransaccions;

	public TabVtsCaja() {
	}

	public String getIdcaja() {
		return this.idcaja;
	}

	public void setIdcaja(String idcaja) {
		this.idcaja = idcaja;
	}

	public BigDecimal getActualcaja() {
		return this.actualcaja;
	}

	public void setActualcaja(BigDecimal actualcaja) {
		this.actualcaja = actualcaja;
	}

	public List<TabVtsTransaccion> getTabVtsTransaccions() {
		return this.tabVtsTransaccions;
	}

	public void setTabVtsTransaccions(List<TabVtsTransaccion> tabVtsTransaccions) {
		this.tabVtsTransaccions = tabVtsTransaccions;
	}

	public TabVtsTransaccion addTabVtsTransaccion(TabVtsTransaccion tabVtsTransaccion) {
		getTabVtsTransaccions().add(tabVtsTransaccion);
		tabVtsTransaccion.setTabVtsCaja(this);

		return tabVtsTransaccion;
	}

	public TabVtsTransaccion removeTabVtsTransaccion(TabVtsTransaccion tabVtsTransaccion) {
		getTabVtsTransaccions().remove(tabVtsTransaccion);
		tabVtsTransaccion.setTabVtsCaja(null);

		return tabVtsTransaccion;
	}

}