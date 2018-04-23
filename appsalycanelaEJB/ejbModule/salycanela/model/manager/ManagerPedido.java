package salycanela.model.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import salycanela.model.entities.TabAdmUsuario;
import salycanela.model.entities.TabParametro;
import salycanela.model.entities.TabVtsCaja;
import salycanela.model.entities.TabVtsCocina;
import salycanela.model.entities.TabVtsDetallePedido;
import salycanela.model.entities.TabVtsPedido;
import salycanela.model.entities.TabVtsPlato;

import salycanela.model.entities.TabVtsTransaccion;

/**
 * Session Bean implementation class ManagerPedido
 */
@Stateless
@LocalBean
public class ManagerPedido {

	@PersistenceContext(unitName = "restaurante_salycanelaDS")
	private EntityManager em;

	public ManagerPedido() {
		// TODO Auto-generated constructor stub
	}

	public TabVtsTransaccion crearTransaccionTmp() {
		TabAdmUsuario usuario = em.find(TabAdmUsuario.class, "1003529672");// cambiar por id de usuario
		TabVtsCaja caja = em.find(TabVtsCaja.class, "Restaurante");
		TabVtsTransaccion transaccionTmp = new TabVtsTransaccion();
		transaccionTmp.setTabAdmUsuario(usuario);
		transaccionTmp.setTabVtsCaja(caja);
		transaccionTmp.setFechatransaccion(new Date());
		transaccionTmp.setAnulatransaccion(false);
		transaccionTmp.setDescripciontransaccion("pedido hecho en la fecha " + new Date());
		transaccionTmp.setTipotransaccion(true);
		transaccionTmp.setTabVtsPedidos(new ArrayList<TabVtsPedido>());
		return transaccionTmp;
	}

	public TabVtsPedido crearPedidoTmp(TabVtsTransaccion transaccionTmp) {
		TabAdmUsuario usuario = em.find(TabAdmUsuario.class, "1003529672");// cambiar por id de usuario
		TabVtsPedido pedidoTmp = new TabVtsPedido();
		pedidoTmp.setTabAdmUsuario(usuario);
		pedidoTmp.setTabVtsTransaccion(transaccionTmp);
		// pedidoTmp.setFechapedido(new Date());
		pedidoTmp.setEntregapedido(false);
		pedidoTmp.setAnulapedido(false);
		pedidoTmp.setTabVtsDetallePedidos(new ArrayList<TabVtsDetallePedido>());
		transaccionTmp.getTabVtsPedidos().add(pedidoTmp);
		return pedidoTmp;
	}

	public void agregarDetallePedidoTmp(TabVtsPedido pedidoTmp, Integer idplato, Integer cantidad, boolean segundo,
			boolean llevar, boolean tarjeta) throws Exception {
		double valorTotal = 0;
		double precio = 0;
		if (pedidoTmp == null)
			throw new Exception("Error primero debe crear un nuevo Pedido.");
		if (idplato == null || idplato.intValue() < 0)
			throw new Exception("Error debe especificar el codigo del plato.");
		if (cantidad == null || cantidad.intValue() <= 0)
			throw new Exception("Error debe especificar la cantidad del producto.");

		// buscamos el plato:
		TabVtsPlato p = em.find(TabVtsPlato.class, idplato);

		// creamos un nuevo detalle y llenamos sus propiedades:
		TabVtsDetallePedido d = new TabVtsDetallePedido();
		d.setTabVtsPedido(pedidoTmp);
		d.setTabVtsPlato(p);
		d.setCantidaddp(cantidad);
		d.setSegundo(segundo);
		d.setLlevar(llevar);
		d.setTarjeta(tarjeta);

		if (!tarjeta) {
			if (segundo) {
				precio = p.getPrecioespecialplato().doubleValue();
			} else {
				precio = p.getPrecioplato().doubleValue();
			}
			if (llevar)
				precio = precio + 0.25;
		}
		valorTotal = cantidad * precio;
		d.setValorunitariodp(new BigDecimal(precio));
		d.setValortotaldp(new BigDecimal(valorTotal));

		pedidoTmp.getTabVtsDetallePedidos().add(d);
		// verificamos los campos calculados:
		calcularPedidoTmp(pedidoTmp);
	}

	public void eliminarDetallePedidoTmp(TabVtsPedido pedidoTmp, int iddp) throws Exception {
		if (pedidoTmp == null)
			throw new Exception("Error primero debe crear un nuevo Pedido.");
		pedidoTmp.getTabVtsDetallePedidos().remove(iddp);
		// verificamos los campos calculados:
		calcularPedidoTmp(pedidoTmp);
	}

	public void asignarMesaPedidoTmp(TabVtsPedido pedidoTmp, Integer mesa) throws Exception {
		try {
			if (mesa == null || mesa == 0)
				throw new Exception("Error debe especificar la mesa.");
			pedidoTmp.setMesapedido(mesa);
		} catch (Exception e) {
			throw new Exception("Error al asignar mesa: " + e.getMessage());
		}
	}

	public void asignarCocinaPedidoTmp(TabVtsPedido pedidoTmp, int cocina) throws Exception {
		try {
			if (cocina == 0)
				throw new Exception("Error debe especificar la cocina.");
			TabVtsCocina c = em.find(TabVtsCocina.class, cocina);
			pedidoTmp.setTabVtsCocina(c);
		} catch (Exception e) {
			throw new Exception("Error al asignar cocina: " + e.getMessage());
		}
	}

	public void guardarPedidoTemporal(TabVtsTransaccion transaccionTmp, TabVtsPedido pedidoTmp) throws Exception {

		if (pedidoTmp == null)
			throw new Exception("Debe crear un pedido primero.");
		if (pedidoTmp.getTabVtsDetallePedidos() == null || pedidoTmp.getTabVtsDetallePedidos().size() == 0)
			throw new Exception("Debe ingresar los productos en el pedido.");
		if (pedidoTmp.getMesapedido() == 0)
			throw new Exception("Debe registrar la mesa.");
		if (pedidoTmp.getTabVtsCocina() == null)
			throw new Exception("Debe registrar la cocina.");

		pedidoTmp.setFechapedido(new Date());
		transaccionTmp.setValortransaccion(pedidoTmp.getValorpedido());

		// obtenemos el numero del nuevo pedido:
		int contPedidos;
		contPedidos = getContPedidos();
		contPedidos++;
		pedidoTmp.setIdpedido(contPedidos);

		// verificamos los campos calculados:
		calcularPedidoTmp(pedidoTmp);
		quitarStock(pedidoTmp);

		// guardamos el pedido completa en la bdd:
		em.persist(transaccionTmp);

		// actualizamos los parametros contadores del pedido:
		actualizarContPedidos(contPedidos);
		pedidoTmp = null;

	}

	public TabVtsPedido findPedidoByID(int idpedido) throws Exception {
		TabVtsPedido p = em.find(TabVtsPedido.class, idpedido);
		return p;
	}

	public List<TabVtsPedido> findAllPedidosXentregar(int idcocina) {
		Query q;
		List<TabVtsPedido> listado;
		String sentenciaSQL = null;
		if (idcocina != 0) {

			sentenciaSQL = "SELECT p FROM TabVtsPedido p WHERE p.entregapedido=false AND p.anulapedido=false AND p.tabVtsCocina.idcocina="
					+ idcocina + "ORDER BY p.idpedido";
		} else if (idcocina == 0) {
			sentenciaSQL = "SELECT p FROM TabVtsPedido p WHERE p.entregapedido=false AND p.anulapedido=false ORDER BY p.idpedido";
		}
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;

	}

	public List<TabVtsPedido> findAllPedidosEntregado(String fecha) {
		Query q;
		List<TabVtsPedido> listado;
		String sentenciaSQL;

		//SimpleDateFormat date_format = new SimpleDateFormat("DD-MM-YYYY");
		//String f = date_format.format(fecha);
		sentenciaSQL = "SELECT p FROM TabVtsPedido p WHERE p.entregapedido=true AND  TO_CHAR(p.fechapedido,'DD-MM-YYYY')='"+fecha+"'";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;

	}

	public void entregarPedido(int idpedido, boolean entregapedido) throws Exception {
		TabVtsPedido p = em.find(TabVtsPedido.class, idpedido);
		if (p == null)
			throw new Exception("No existe pedido especificado.");
		p.setEntregapedido(entregapedido);
		em.merge(p);
	}

	public void anularPedido(int idpedido, boolean anulapedido) throws Exception {
		TabVtsPedido p = em.find(TabVtsPedido.class, idpedido);
		if (p == null)
			throw new Exception("No existe pedido especificado.");
		p.setAnulapedido(anulapedido);
		em.merge(p);
	}

	// diferentes metodos que sirven como cálculos

	private void calcularPedidoTmp(TabVtsPedido pedidoTmp) {
		double sumaTotales = 0;
		for (TabVtsDetallePedido det : pedidoTmp.getTabVtsDetallePedidos()) {
			sumaTotales += det.getCantidaddp().doubleValue() * det.getValorunitariodp().doubleValue();
		}

		pedidoTmp.setValorpedido(new BigDecimal(sumaTotales));
	}

	public void quitarStock(TabVtsPedido pedidoTmp) {
		for (TabVtsDetallePedido det : pedidoTmp.getTabVtsDetallePedidos()) {
			TabVtsPlato p = em.find(TabVtsPlato.class, det.getTabVtsPlato().getIdplato());
			p.setStock(p.getStock() - det.getCantidaddp());
			em.merge(p);
		}
	}

	private int getContPedidos() throws Exception {
		TabParametro parametro = null;
		try {
			parametro = em.find(TabParametro.class, "cont_pedido");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Revise el parametro 'cont_pedido': " + e.getMessage());
		}
		return parametro.getValorparametro();
	}

	private void actualizarContPedidos(int nuevocontador) throws Exception {
		TabParametro parametro = null;
		try {
			parametro = em.find(TabParametro.class, "cont_pedido");
			parametro.setValorparametro(nuevocontador);
			em.merge(parametro);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al actualizar el parametro 'cont_pedido': " + e.getMessage());
		}
	}

	public List<TabVtsDetallePedido> mostrarDetalles(int idpedido) {
		Query q;
		List<TabVtsDetallePedido> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT d FROM TabVtsDetallePedido d WHERE d.tabVtsPedido.idpedido=" + idpedido;
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}
}
