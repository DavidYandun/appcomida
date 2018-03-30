package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tab_parametros database table.
 * 
 */
@Entity
@Table(name="tab_parametros")
@NamedQuery(name="TabParametro.findAll", query="SELECT t FROM TabParametro t")
public class TabParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String nombreparametro;

	private Integer valorparametro;

	public TabParametro() {
	}

	public String getNombreparametro() {
		return this.nombreparametro;
	}

	public void setNombreparametro(String nombreparametro) {
		this.nombreparametro = nombreparametro;
	}

	public Integer getValorparametro() {
		return this.valorparametro;
	}

	public void setValorparametro(Integer valorparametro) {
		this.valorparametro = valorparametro;
	}

}