package br.com.giftList.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.giftList.dao.UsuarioDAO;
import br.com.giftList.entity.Usuario;

@ManagedBean
@ViewScoped
public class MeusPresentesMB implements Serializable{
	
	private Usuario usuario;

	public MeusPresentesMB(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;

		HttpSession session = (HttpSession) request.getSession();
		Integer idUsuario = (Integer) session.getAttribute("id");

		UsuarioDAO dao= new UsuarioDAO();
		usuario = dao.buscaId(idUsuario, Usuario.class);

	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
