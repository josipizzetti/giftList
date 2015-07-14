package br.com.giftList.mb;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.giftList.dao.UsuarioDAO;
import br.com.giftList.entity.Usuario;
import br.com.locadora.security.Cripto;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable{
	
	UsuarioDAO dao = new UsuarioDAO();
	private List<Usuario> usuarios;
	private Usuario usuario;
	private String filtro;
	private List<Usuario> lista;
	
	public UsuarioMB(){
		usuario = new Usuario();
	}
	
	public List<Usuario> listar(){
		return dao.listar(Usuario.class); 
	}
	
	public String buscaPorNome(){
		lista = dao.buscaPorNome(filtro);
		return ""; 
	}
	
	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String salvar() throws NoSuchAlgorithmException{
		
		Cripto c = new Cripto();
		usuario.setSenha(c.encript(usuario.getSenha()));
		dao.salvar(usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Salvo com sucesso",""));
		
		limpaUsuario();		
		return "login.jsf";
	}

	private void limpaUsuario() {
		usuario = new Usuario();
		
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
