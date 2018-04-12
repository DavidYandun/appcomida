package salycanela.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.apache.commons.codec.digest.DigestUtils;
import salycanela.model.entities.TabAdmTipoUsuario;
import salycanela.model.entities.TabAdmUsuario;
import salycanela.model.manager.ManagerUsuario;
import salycanela.view.util.JSFUtil;

@ManagedBean
@SessionScoped

public class ControllerUsuario {

	private String idUsuario;
	private int idTipoUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String correoUsuario;
	private String passwordUsuario;
	private boolean estadoUsuario = true;

	private String idUsuario1;
	private int idTipoUsuario1;
	private String nombreUsuario1;
	private String apellidoUsuario1;
	private String correoUsuario1;
	private String passwordUsuario1;
	private boolean estadoUsuario1 = true;
	private boolean respuesta;
	private List<TabAdmUsuario> lista;

	@EJB
	private ManagerUsuario managerUsuarios;

	@PostConstruct
	public void iniciar() {
		vaciarCampos();

	}

	public void AgregarUsuario() {
		try {
			String encript = DigestUtils.sha1Hex(passwordUsuario1);
			managerUsuarios.agregarusuario(idUsuario1, idTipoUsuario1, nombreUsuario1, apellidoUsuario1, correoUsuario1,
					encript, estadoUsuario1);
			JSFUtil.crearMensajeInfo("Usuario registrado correctamente.");
			vaciarCampos();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void CargarUsuario(TabAdmUsuario usuario) {
		idUsuario1 = usuario.getIdusuario();
		idTipoUsuario1 = usuario.getTabAdmTipoUsuario().getIdtipousuario();
		nombreUsuario1 = usuario.getNombreusuario();
		apellidoUsuario1 = usuario.getApellidousuario();
		correoUsuario1 = usuario.getCorreousuario();
		passwordUsuario1 = usuario.getPasswordusuario();
		estadoUsuario1 = usuario.getEstadousuario();
	}

	public void EditarUsuario() {
		try {
			String encript = DigestUtils.sha1Hex(passwordUsuario1);
			managerUsuarios.editarUsuario(idUsuario1, idTipoUsuario1, nombreUsuario1, apellidoUsuario1, correoUsuario1,
					encript, estadoUsuario1);
			vaciarCampos();
			JSFUtil.crearMensajeInfo("Usuario" + idUsuario1 + " editado correctamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<SelectItem> getListaUsuariosSI() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<TabAdmTipoUsuario> listadoUsuarios = managerUsuarios.findAllTipoUsuarios();

		for (TabAdmTipoUsuario c : listadoUsuarios) {
			SelectItem item = new SelectItem(c.getIdtipousuario(), c.getNombretipousuario());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public void vaciarCampos() {
		lista = managerUsuarios.findAllUsuarios();
		idUsuario1 = null;
		idTipoUsuario1 = 0;
		nombreUsuario1 = null;
		apellidoUsuario1 = null;
		correoUsuario1 = null;
		passwordUsuario1 = null;
		estadoUsuario1 = true;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public boolean isEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public String getIdUsuario1() {
		return idUsuario1;
	}

	public void setIdUsuario1(String idUsuario1) {
		this.idUsuario1 = idUsuario1;
	}

	public int getIdTipoUsuario1() {
		return idTipoUsuario1;
	}

	public void setIdTipoUsuario1(int idTipoUsuario1) {
		this.idTipoUsuario1 = idTipoUsuario1;
	}

	public String getNombreUsuario1() {
		return nombreUsuario1;
	}

	public void setNombreUsuario1(String nombreUsuario1) {
		this.nombreUsuario1 = nombreUsuario1;
	}

	public String getApellidoUsuario1() {
		return apellidoUsuario1;
	}

	public void setApellidoUsuario1(String apellidoUsuario1) {
		this.apellidoUsuario1 = apellidoUsuario1;
	}

	public String getCorreoUsuario1() {
		return correoUsuario1;
	}

	public void setCorreoUsuario1(String correoUsuario1) {
		this.correoUsuario1 = correoUsuario1;
	}

	public String getPasswordUsuario1() {
		return passwordUsuario1;
	}

	public void setPasswordUsuario1(String passwordUsuario1) {
		this.passwordUsuario1 = passwordUsuario1;
	}

	public boolean isEstadoUsuario1() {
		return estadoUsuario1;
	}

	public void setEstadoUsuario1(boolean estadoUsuario1) {
		this.estadoUsuario1 = estadoUsuario1;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public List<TabAdmUsuario> getLista() {
		return lista;
	}

	public void setLista(List<TabAdmUsuario> lista) {
		this.lista = lista;
	}

}
