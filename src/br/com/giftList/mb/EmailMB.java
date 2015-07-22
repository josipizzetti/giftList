package br.com.giftList.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.giftList.dao.UsuarioDAO;
import br.com.giftList.entity.Mensagem;
import br.com.giftList.entity.Usuario;

@ManagedBean
@ViewScoped
public class EmailMB {
	
	Mensagem mensagem = new Mensagem();
	
	public EmailMB(){
		
	}
	
	
	public void sendEmail() throws EmailException {
	    
			SimpleEmail email = new SimpleEmail();
		   System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinatários
		   email.addTo(mensagem.getDestino());
		   //Configure o seu email do qual enviará
		   email.setFrom("josipizzetti@gmail.com", "Gift List");
		   //Adicione um assunto
		   email.setSubject("Aviso de presente, Gift List!");
		   //Adicione a mensagem do email
		   email.setMsg(mensagem.getMsg());
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("josipizzetti@gmail.com", "manekineko37");
		   System.out.println("enviando...");
		   email.send();
		   FacesContext context = FacesContext.getCurrentInstance();
		   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso enviado com sucesso!",""));
		   System.out.println("Email enviado!");
		}


	public Mensagem getMensagem() {
		return mensagem;
	}


	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}
