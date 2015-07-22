package br.com.giftList.mb;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.giftList.dao.PresenteDAO;
import br.com.giftList.dao.UsuarioDAO;
import br.com.giftList.entity.Mensagem;
import br.com.giftList.entity.Presente;
import br.com.giftList.entity.Usuario;

@ManagedBean
@ViewScoped
public class UserPresentesMB implements Serializable{

	
	Usuario usuario = new Usuario();
	Mensagem mensagem = new Mensagem();
	private Presente presente;
	
	public UserPresentesMB() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String parameter = req.getParameter("usuarioSelecionadoId");
		UsuarioDAO dao = new UsuarioDAO();
		usuario = dao.buscaId(Integer.parseInt(parameter), Usuario.class);
		
		String presenteSelecionadoId = req.getParameter("presenteSelecionadoId");
		PresenteDAO presenteDAO = new PresenteDAO();
		if(presenteSelecionadoId != null){
			presente = presenteDAO.buscaId(Integer.parseInt(presenteSelecionadoId), Presente.class);
		}
		
	}
	

	public String passaId(){
		if(usuario.getPresentes() != null){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		Map<String, String> id = context.getExternalContext().getRequestParameterMap();
		session.setAttribute("idPresente", id.get("idPresente"));
		return "aviso.jsf";
		}else{
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String parameter = req.getParameter("usuarioSelecionadoId");
			UsuarioDAO dao = new UsuarioDAO();
			usuario = dao.buscaId(Integer.parseInt(parameter), Usuario.class);
			return "aviso.jsf";
		}
	}
	
	public void sendEmail() throws EmailException {
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;

		HttpSession session = (HttpSession) request.getSession();
		Integer idUsuario = (Integer) session.getAttribute("id");

		UsuarioDAO dao= new UsuarioDAO();
		Usuario usuarioLogado = dao.buscaId(idUsuario, Usuario.class);
		System.out.println(usuarioLogado);
		
		String msg = "Olá, " + usuario.getNome() + "! \n\nVocê esta recebendo o presente " + presente.getNome() + " de " + usuarioLogado.getNome()+"! "
				+ "\n\n\nAconselhamos que após receber seu presente remova o mesmo de sua lista para mantê-la sempre atualizada. ";
	    
		SimpleEmail email = new SimpleEmail();
	   System.out.println("alterando hostname...");
	   email.setHostName("smtp.gmail.com");
	   email.setSmtpPort(465);
	   email.addTo(usuario.getEmail());
	   email.setFrom("giftlist.aviso@gmail.com", "Gift List");
	   email.setSubject("Surpresa!!");
	   email.setMsg(msg);
	   System.out.println("autenticando...");
	   email.setSSL(true);
	   email.setAuthentication("giftlist.aviso@gmail.com", "Giftlist2015*");
	   System.out.println("enviando...");
	   email.send();
	   FacesContext context = FacesContext.getCurrentInstance();
	   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso enviado com sucesso!",""));
	   
	   System.out.println("Email enviado!");
	}


	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Presente getPresente() {
		return presente;
	}


	public void setPresente(Presente presente) {
		this.presente = presente;
	}
}
