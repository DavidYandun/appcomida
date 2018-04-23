package salycanela.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import salycanela.model.entities.TabVtsCocina;
import salycanela.model.entities.TabVtsDetallePedido;
import salycanela.model.entities.TabVtsPedido;
import salycanela.model.entities.TabVtsPlato;
import salycanela.model.entities.TabVtsTransaccion;

import salycanela.model.manager.ManagerCocina;
import salycanela.model.manager.ManagerPedido;
import salycanela.model.manager.ManagerPlato;
import salycanela.view.util.JSFUtil;

@SessionScoped
@ManagedBean
public class ControllerPedidoAlm {
	@EJB
	private ManagerPedido managerPedido;
	@EJB
	private ManagerPlato managerPlato;
	@EJB
	private ManagerCocina managerCocina;

	private TabVtsPedido pedidoTmp1;
	private List<TabVtsDetallePedido> detalleTmp;

	private TabVtsPedido pedidoTmp;
	private TabVtsTransaccion transaccionTmp;

	private List<TabVtsPedido> lista_x_entregar;
	private List<TabVtsPedido> lista_entregado;
	private List<TabVtsPlato> listamenu;

	private int idpedido;
	private int bebida;
	private int cantidad;
	private int idplato;
	private int mesa;
	private int cocina;
	private int idusuario;
	private int lugar;

	private Date fecha;
	SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-YYYY");
	String f;

	private BigDecimal valorpedido;

	private boolean entregapedido;
	private boolean segundo;
	private boolean llevar;
	private boolean tarjeta;
	private boolean entrega;

	private boolean transaccionTmpGuardada;
	private boolean pedidoTmpGuardada;

	@PostConstruct
	public void iniciar() throws Exception {
		nuevoPedido();
	}

	public void actualizarTablas() {
		lista_x_entregar = managerPedido.findAllPedidosXentregar(lugar);
		lista_entregado = managerPedido.findAllPedidosEntregado(f);
		listamenu = managerPlato.findAllMenu(1);
		//f = date_format.format(fecha);
	}

	public void nuevoPedido() throws Exception {
		actualizarTablas();
		transaccionTmp = managerPedido.crearTransaccionTmp();
		pedidoTmp = managerPedido.crearPedidoTmp(transaccionTmp);
		// detalles
		cantidad = 1;
		idplato = 0;
		// pedido
		mesa = 0;
		cocina = 1;
		bebida = 0;
		entregapedido = false;
		segundo = false;
		llevar = false;
		tarjeta = false;
		// estados de los guardados
		transaccionTmpGuardada = false;
		pedidoTmpGuardada = false;
		fecha = new Date();
		f = date_format.format(fecha);

	}

	public String insertarDetalle(TabVtsPlato plato) {
		if (transaccionTmpGuardada == true && pedidoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El pedido ya fue guardado.");
			return "";
		}
		try {
			managerPedido.agregarDetallePedidoTmp(pedidoTmp, plato.getIdplato(), 1, segundo, llevar, tarjeta);
			segundo = false;
			llevar = false;
			tarjeta = false;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	public String insertarBebida() {
		if (transaccionTmpGuardada == true && pedidoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El pedido ya fue guardado.");
			return "";
		}
		try {
			managerPedido.agregarDetallePedidoTmp(pedidoTmp, bebida, 1, false, false, false);
			segundo = false;
			llevar = false;
			tarjeta = false;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	public String insertarSopa(int sopa) {
		if (transaccionTmpGuardada == true && pedidoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El pedido ya fue guardado.");
			return "";
		}
		try {
			managerPedido.agregarDetallePedidoTmp(pedidoTmp, sopa, 1, false, llevar, tarjeta);
			segundo = false;
			llevar = false;
			tarjeta = false;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	public void eliminarDetalle(int iddp) throws Exception {
		try {
			managerPedido.eliminarDetallePedidoTmp(pedidoTmp, iddp);
			JSFUtil.crearMensajeInfo("Detalle eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void asignarMesa() {
		if (pedidoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El pedido ya fue guardado.");
		}
		try {
			managerPedido.asignarMesaPedidoTmp(pedidoTmp, mesa);
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Por favor asigne una mesa");

		}
	}

	public void asignarCocina() {
		if (pedidoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El pedido ya fue guardado.");
		}
		try {

			managerPedido.asignarCocinaPedidoTmp(pedidoTmp, cocina);
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Por favor asigne una cocina");
		}
	}

	public String guardarPedido() {
		if (transaccionTmpGuardada == true && pedidoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El pedido ya fue guardada.");

			return "";
		}
		try {
			managerPedido.asignarCocinaPedidoTmp(pedidoTmp, cocina);
			managerPedido.guardarPedidoTemporal(transaccionTmp, pedidoTmp);
			transaccionTmpGuardada = true;
			pedidoTmpGuardada = true;
			JSFUtil.crearMensajeInfo("Pedido guardado exitosamente");
			nuevoPedido();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	// editar pedidos entregados - anulados
	public void EntregarPedido(TabVtsPedido pedido, boolean entregar) throws Exception {
		try {
			idpedido = pedido.getIdpedido();
			managerPedido.entregarPedido(idpedido, entregar);

			JSFUtil.crearMensajeInfo("Pedido entregado exitosamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		actualizarTablas();
	}

	public void AnularPedido(TabVtsPedido pedido, boolean anular) throws Exception {
		try {
			idpedido = pedido.getIdpedido();
			managerPedido.anularPedido(idpedido, anular);

			JSFUtil.crearMensajeInfo("Pedido anulado exitosamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		actualizarTablas();
	}

	public List<TabVtsDetallePedido> CargarPedido(TabVtsPedido pedido) {
		try {
			pedidoTmp1 = pedido;
			detalleTmp = managerPedido.mostrarDetalles(pedido.getIdpedido());
		} catch (Exception e) {
			JSFUtil.crearMensajeError("pedido no encontrado");
		}
		return detalleTmp;
	}

	public String Segundos(TabVtsDetallePedido detalle) {
		if (detalle.getSegundo())
			return "Segundo de";
		return "";
	}

	public String Llevar(TabVtsDetallePedido detalle) {
		if (detalle.getLlevar())
			return "para Llevar";
		return "";
	}

	public List<SelectItem> getListaCocinaSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<TabVtsCocina> listadoCocinas = managerCocina.findAllCocinas();
		for (TabVtsCocina p : listadoCocinas) {
			SelectItem item = new SelectItem(p.getIdcocina(), p.getNombrecocina());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public List<SelectItem> getListaBebidaSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<TabVtsPlato> listadoBebidas = managerPlato.findAllMenu(3);
		for (TabVtsPlato p : listadoBebidas) {
			SelectItem item = new SelectItem(p.getIdplato(), p.getNombreplato());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public TabVtsPedido getPedidoTmp1() {
		return pedidoTmp1;
	}

	public void setPedidoTmp1(TabVtsPedido pedidoTmp1) {
		this.pedidoTmp1 = pedidoTmp1;
	}

	public List<TabVtsDetallePedido> getDetalleTmp() {
		return detalleTmp;
	}

	public void setDetalleTmp(List<TabVtsDetallePedido> detalleTmp) {
		this.detalleTmp = detalleTmp;
	}

	public TabVtsPedido getPedidoTmp() {
		return pedidoTmp;
	}

	public void setPedidoTmp(TabVtsPedido pedidoTmp) {
		this.pedidoTmp = pedidoTmp;
	}

	public TabVtsTransaccion getTransaccionTmp() {
		return transaccionTmp;
	}

	public void setTransaccionTmp(TabVtsTransaccion transaccionTmp) {
		this.transaccionTmp = transaccionTmp;
	}

	public List<TabVtsPedido> getLista_x_entregar() {
		return lista_x_entregar;
	}

	public void setLista_x_entregar(List<TabVtsPedido> lista_x_entregar) {
		this.lista_x_entregar = lista_x_entregar;
	}

	public List<TabVtsPedido> getLista_entregado() {
		return lista_entregado;
	}

	public void setLista_entregado(List<TabVtsPedido> lista_entregado) {
		this.lista_entregado = lista_entregado;
	}

	public List<TabVtsPlato> getListamenu() {
		return listamenu;
	}

	public void setListamenu(List<TabVtsPlato> listamenu) {
		this.listamenu = listamenu;
	}

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getBebida() {
		return bebida;
	}

	public void setBebida(int bebida) {
		this.bebida = bebida;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdplato() {
		return idplato;
	}

	public void setIdplato(int idplato) {
		this.idplato = idplato;
	}

	public int getMesa() {
		return mesa;
	}

	public void setMesa(int mesa) {
		this.mesa = mesa;
	}

	public int getCocina() {
		return cocina;
	}

	public void setCocina(int cocina) {
		this.cocina = cocina;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getLugar() {
		return lugar;
	}

	public void setLugar(int lugar) {
		this.lugar = lugar;
	}

	public BigDecimal getValorpedido() {
		return valorpedido;
	}

	public void setValorpedido(BigDecimal valorpedido) {
		this.valorpedido = valorpedido;
	}

	public boolean isEntregapedido() {
		return entregapedido;
	}

	public void setEntregapedido(boolean entregapedido) {
		this.entregapedido = entregapedido;
	}

	public boolean isSegundo() {
		return segundo;
	}

	public void setSegundo(boolean segundo) {
		this.segundo = segundo;
	}

	public boolean isLlevar() {
		return llevar;
	}

	public void setLlevar(boolean llevar) {
		this.llevar = llevar;
	}

	public boolean isTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(boolean tarjeta) {
		this.tarjeta = tarjeta;
	}

	public boolean isEntrega() {
		return entrega;
	}

	public void setEntrega(boolean entrega) {
		this.entrega = entrega;
	}

	public boolean isTransaccionTmpGuardada() {
		return transaccionTmpGuardada;
	}

	public void setTransaccionTmpGuardada(boolean transaccionTmpGuardada) {
		this.transaccionTmpGuardada = transaccionTmpGuardada;
	}

	public boolean isPedidoTmpGuardada() {
		return pedidoTmpGuardada;
	}

	public void setPedidoTmpGuardada(boolean pedidoTmpGuardada) {
		this.pedidoTmpGuardada = pedidoTmpGuardada;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

}
