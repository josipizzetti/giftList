package br.com.giftList.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.giftList.dao.EventoDAO;
import br.com.giftList.dao.GenericDAO;
import br.com.giftList.dao.PresenteDAO;
import br.com.giftList.dao.UsuarioDAO;
import br.com.giftList.entity.Evento;
import br.com.giftList.entity.Presente;
import br.com.giftList.entity.Usuario;

@ManagedBean
@ViewScoped
public class MeusPresentesMB implements Serializable{
	
	private Usuario usuario;
	private Presente presente;
	private List<Presente> presentes;
	private PresenteDAO presenteDAO = new PresenteDAO();
	private List<Evento> eventos= new ArrayList<Evento>();
	private Date dtEvento;
	private StreamedContent imagem;

	public MeusPresentesMB(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;

		HttpSession session = (HttpSession) request.getSession();
		Integer idUsuario = (Integer) session.getAttribute("id");

		UsuarioDAO dao= new UsuarioDAO();
		usuario = dao.buscaId(idUsuario, Usuario.class);
		presentes = presenteDAO.listar(Presente.class);
		presente = new Presente();
		listarEventos();

	}
	
	public void upload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		byte[] foto = uploadedFile.getContents();
		/*try {
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		this.presente.setImagem(foto);
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
	
	private void listarEventos() {
		EventoDAO eventoDAO = new EventoDAO();
		this.eventos = eventoDAO.listar(Evento.class);
	}
	
	public void salvar(){
		presente.setUsuario(usuario);//o construtor desta sessao tem o id usuario, para salvar chamamos aqui
		if(presente.getId() == 0){
			presenteDAO.salvar(presente);
		}else{
			presenteDAO.atualizar(presente);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Presente: "+presente.getNome()+" salvo com sucesso!", ""));
		usuario = new UsuarioDAO().buscaId(usuario.getId(), Usuario.class); //traz o ultimo presente inserido
		limpaPresente();
	}
	
	private void limpaPresente() {
		presente = new Presente();
		
	}

	public void deletar(Presente presente){
		
		Presente entity = presenteDAO.buscaId(presente.getId(), Presente.class);
		
		if(entity != null){
			presenteDAO.deletar(entity);
			GenericDAO<Usuario> dao = new GenericDAO<Usuario>();
			usuario = dao.buscaId(usuario.getId(), Usuario.class);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"Excluido com sucesso!", ""));
			limpaPresente();
		}
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

	public List<Presente> getPresentes() {
		return presentes;
	}

	public void setPresentes(List<Presente> presentes) {
		this.presentes = presentes;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Date getDtEvento() {
		return dtEvento;
	}

	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}

	public StreamedContent getImagem() {
		
		
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

}
