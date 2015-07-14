package br.com.giftList.mb;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.giftList.dao.UsuarioDAO;
import br.com.giftList.entity.Usuario;
import br.com.locadora.security.Cripto;

@ManagedBean
public class AutenticacaoMB {

	private String email, senha;
	
	public String autentica() throws NoSuchAlgorithmException{
		FacesContext context = FacesContext.getCurrentInstance();
		if(!email.trim().equals("") && !senha.trim().equals("")){
			UsuarioDAO dao = new UsuarioDAO();
			Usuario u = dao.buscaPorLogin(email);
			
			Cripto c = new Cripto();
			senha = c.encript(senha);
			
			if(u.getId() != 0 && this.senha.equals(u.getSenha())){
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("id", u.getId());
				
				
				return "/logado/home.jsf";
			}
		}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				"E-mail ou senha inválidos!", ""));
		return "";
	}
	
	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);		
		session.invalidate();
		
		return "/index.jsf";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
