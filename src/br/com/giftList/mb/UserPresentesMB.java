package br.com.giftList.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.giftList.dao.UsuarioDAO;
import br.com.giftList.entity.Usuario;

@ManagedBean
@ViewScoped
public class UserPresentesMB implements Serializable{

	
	Usuario usuario = new Usuario();
	
	public UserPresentesMB() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String parameter = req.getParameter("usuarioSelecionadoId");
		UsuarioDAO dao = new UsuarioDAO();
		usuario = dao.buscaId(Integer.parseInt(parameter), Usuario.class);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
