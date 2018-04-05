package salycanela.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the tab_vts_detalle_pedido database table.
 * 
 */
@Entity
@Table(name="tab_vts_detalle_pedido")
@NamedQuery(name="TabVtsDetallePedido.findAll", query="SELECT t FROM TabVtsDetallePedido t")
public class TabVtsDetallePedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_VTS_DETALLE_PEDIDO_IDDP_GENERATOR", sequenceName="TAB_VTS_DETALLE_PEDIDO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_VTS_DETALLE_PEDIDO_IDDP_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer iddp;

	private Integer cantidaddp;

	private BigDecimal valortotaldp;

	private BigDecimal valorunitariodp;
	
	private boolean segundo;
	
	private boolean llevar;
	
	private boolean tarjeta;

	//bi-directional many-to-one association to TabVtsPedido
	@ManyToOne
	@JoinColumn(name="idpedido")
	private TabVtsPedido tabVtsPedido;

	//bi-directional many-to-one association to TabVtsPlato
	@ManyToOne
	@JoinColumn(name="idplato")
	private TabVtsPlato tabVtsPlato;

	public TabVtsDetallePedido() {
	}

	public Integer getIddp() {
		return this.iddp;
	}

	public void setIddp(Integer iddp) {
		this.iddp = iddp;
	}

	public Integer getCantidaddp() {
		return this.cantidaddp;
	}

	public void setCantidaddp(Integer cantidaddp) {
		this.cantidaddp = cantidaddp;
	}

	public BigDecimal getValortotaldp() {
		return this.valortotaldp;
	}

	public void setValortotaldp(BigDecimal valortotaldp) {
		this.valortotaldp = valortotaldp;
	}

	public BigDecimal getValorunitariodp() {
		return this.valorunitariodp;
	}

	public void setValorunitariodp(BigDecimal valorunitariodp) {
		this.valorunitariodp = valorunitariodp;
	}

	public TabVtsPedido getTabVtsPedido() {
		return this.tabVtsPedido;
	}

	public void setTabVtsPedido(TabVtsPedido tabVtsPedido) {
		this.tabVtsPedido = tabVtsPedido;
	}

	public TabVtsPlato getTabVtsPlato() {
		return this.tabVtsPlato;
	}

	public void setTabVtsPlato(TabVtsPlato tabVtsPlato) {
		this.tabVtsPlato = tabVtsPlato;
	}
	
	public Boolean getSegundo() {
		return this.segundo;
	}

	public void setSegundo(Boolean segundo) {
		this.segundo = segundo;
	}
	
	public Boolean getLlevar() {
		return this.llevar;
	}

	public void setLlevar(Boolean llevar) {
		this.llevar = llevar;
	}

	public Boolean getTarjeta() {
		return this.tarjeta;
	}

	public void setTarjeta(Boolean tarjeta) {
		this.tarjeta = tarjeta;
	}
}