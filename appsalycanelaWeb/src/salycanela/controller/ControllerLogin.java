package salycanela.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import salycanela.model.entities.TabAdmUsuario;
import salycanela.model.manager.ManagerLogin;
import salycanela.model.util.ModelUtil;
import salycanela.view.util.JSFUtil;

@SessionScoped
@ManagedBean
public class ControllerLogin {
	private String cedulaUsuario;
	private String passwordUsuario;
	private TabAdmUsuario u;
	private boolean comprobado;

	@EJB
	private ManagerLogin managerLogin;

	public void actionLogin() {

		try {

			FacesContext contex = FacesContext.getCurrentInstance();
			u = managerLogin.findUsuario(cedulaUsuario);
			String encript = DigestUtils.sha1Hex(passwordUsuario);
			comprobado = managerLogin.comprobarUsuario(cedulaUsuario, encript);

			// verificamos si el acceso es con admin:
			if (u.getTabAdmTipoUsuario().getIdtipousuario() == 1) {
				JSFUtil.crearMensajeInfo("Login correcto");
				contex.getExternalContext().redirect("admin/template.xhtml");
			} else if (u.getTabAdmTipoUsuario().getIdtipousuario() == 2) {
				JSFUtil.crearMensajeInfo("Login correcto");
				contex.getExternalContext().redirect("cajero/puntodeventa/pedidos/pedidoalmuerzo.xhtml");
			} else if (u.getTabAdmTipoUsuario().getIdtipousuario() == 3) {
				JSFUtil.crearMensajeInfo("Login correcto");
				contex.getExternalContext().redirect("cocina/listapedidos.xhtml");
			}

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public String actionSalir() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		JSFUtil.crearMensajeError("Ha cerrado sesión");
		return "/login?faces-redirect=true";
	}

	public void actionComprobarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			String path = ec.getRequestPathInfo();
			System.out.println("getRequestContextPath(): " + ec.getRequestContextPath());
			System.out.println("getRequestPathInfo(): " + path);
			System.out.println("Id usuario: " + cedulaUsuario);
			if (path.equals("/login.xhtml"))
				return;
			if (ModelUtil.isEmpty(cedulaUsuario))
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			if (!comprobado) {
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			} else {
				// si hizo login, verificamos que acceda a paginas permitidas:
				if (u.getTabAdmTipoUsuario().getIdtipousuario() == 1) {
					if (!path.contains("/admin/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}
				if (u.getTabAdmTipoUsuario().getIdtipousuario() == 2) {
					if (!path.contains("/cajero/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}
				if (u.getTabAdmTipoUsuario().getIdtipousuario() == 3) {
					if (!path.contains("/cocina/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public TabAdmUsuario getU() {
		return u;
	}

	public void setU(TabAdmUsuario u) {
		this.u = u;
	}

	public boolean isComprobado() {
		return comprobado;
	}

	public void setComprobado(boolean comprobado) {
		this.comprobado = comprobado;
	}

}
