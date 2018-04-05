package salycanela.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import salycanela.model.entities.TabVtsDetallePedido;

//import salycanela.model.entities.TabVtsDetallePedido;
import salycanela.model.entities.TabVtsPedido;
import salycanela.model.entities.TabVtsPlato;
import salycanela.model.entities.TabVtsTransaccion;

import salycanela.model.manager.ManagerPedido;
import salycanela.model.manager.ManagerPlato;
import salycanela.view.util.JSFUtil;

@SessionScoped
@ManagedBean
public class ControllerPedidoAlm {
	@EJB
	private ManagerPedido managerPedido;
	private TabVtsPedido pedidoTmp1;
	private List<TabVtsDetallePedido> detalleTmp;
	@EJB
	private ManagerPlato managerPlato;

	private List<TabVtsPedido> lista_x_entregar;
	private List<TabVtsPedido> lista_entregado;
	private List<TabVtsPlato> listamenu;

	private int cantidad;
	private int idplato;
	private int mesa = 0;
	private int idusuario;
	private boolean entregapedido;

	private boolean segundo;
	private boolean llevar;
	private boolean tarjeta;

	private TabVtsPedido pedidoTmp;
	private TabVtsTransaccion transaccionTmp;
	private boolean transaccionTmpGuardada;
	private boolean pedidoTmpGuardada;

	private int idpedido;
	private boolean entrega;
	private BigDecimal valorpedido;

	@PostConstruct
	public void iniciar() {
		nuevoPedido();
	}

	public void actualizarTablas() {
		lista_x_entregar = managerPedido.findAllPedidosXentregar();
		lista_entregado = managerPedido.findAllPedidosEntregado();
		listamenu=managerPlato.findAllMenu(1);
	}

	public void nuevoPedido() {
		actualizarTablas();
		transaccionTmp = managerPedido.crearTransaccionTmp();
		pedidoTmp = managerPedido.crearPedidoTmp(transaccionTmp);
		// detalles
		cantidad = 1;
		idplato = 0;
		// pedido
		mesa = 0;
		entregapedido = false;
		segundo = false;
		llevar = false;
		tarjeta = false;
		// estados de los guardados
		transaccionTmpGuardada = false;
		pedidoTmpGuardada = false;
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

	public String guardarPedido() {
		if (transaccionTmpGuardada == true && pedidoTmpGuardada == true) {
			JSFUtil.crearMensajeWarning("El pedido ya fue guardada.");
			return "";
		}
		try {
			managerPedido.guardarPedidoTemporal(transaccionTmp, pedidoTmp);
			transaccionTmpGuardada = true;
			pedidoTmpGuardada = true;
			actualizarTablas();
			JSFUtil.crearMensajeInfo("Pedido guardado exitosamente");
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
		lista_x_entregar = managerPedido.findAllPedidosXentregar();
		lista_entregado = managerPedido.findAllPedidosEntregado();
	}

	public void AnularPedido(TabVtsPedido pedido, boolean anular) throws Exception {
		try {
			idpedido = pedido.getIdpedido();
			managerPedido.anularPedido(idpedido, anular);

			JSFUtil.crearMensajeInfo("Pedido anulado exitosamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		lista_x_entregar = managerPedido.findAllPedidosXentregar();
		lista_entregado = managerPedido.findAllPedidosEntregado();
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

	public List<SelectItem> getListaPlatoSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<TabVtsPlato> listadoPlatos = managerPlato.findAllPlatos();
		for (TabVtsPlato p : listadoPlatos) {
			SelectItem item = new SelectItem(p.getIdplato(), p.getNombreplato());
			listadoSI.add(item);
		}
		return listadoSI;
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

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public boolean isEntregapedido() {
		return entregapedido;
	}

	public void setEntregapedido(boolean entregapedido) {
		this.entregapedido = entregapedido;
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

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public boolean isEntrega() {
		return entrega;
	}

	public void setEntrega(boolean entrega) {
		this.entrega = entrega;
	}

	public BigDecimal getValorpedido() {
		return valorpedido;
	}

	public void setValorpedido(BigDecimal valorpedido) {
		this.valorpedido = valorpedido;
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

	public List<TabVtsPlato> getListamenu() {
		return listamenu;
	}

	public void setListamenu(List<TabVtsPlato> listamenu) {
		this.listamenu = listamenu;
	}

	
}
