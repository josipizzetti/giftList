package br.com.giftList.mb;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.giftList.dao.PresenteDAO;
import br.com.giftList.entity.Presente;

@ManagedBean
@SessionScoped
public class FileUploadMB {

	
	
	
	private UploadedFile uploadedFile;
	private PresenteDAO dao = new PresenteDAO();
	private Presente presente= new Presente();
	private List<Presente> presentes = new ArrayList<Presente>();
	private StreamedContent image;
	public FileUploadMB(){
		
	}
	
	public void FileUpload(){

	}
	public void limpa(){
		presente = new Presente();
	}
	
	
	
	public StreamedContent getImage() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
		  //  String presenteId = context.getExternalContext().getRequestParameterMap().get("presenteID");
		    HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		    String presenteId = (String) session.getAttribute("idPresente");
		    session.removeAttribute("idPresente");
		    System.out.print("\npppppp"+presenteId+"\n");	 
			presente = dao.buscaId(Integer.parseInt(presenteId), Presente.class);
			System.out.print("\n"+presente.getId()+"\n");
			byte[] b = presente.getImagem(); 
			
			if(b != null){
				InputStream is = new ByteArrayInputStream(b);
				image = new DefaultStreamedContent(is, "image/jpeg", "fileName.jpg");
				return image; 
			}else{
				image = null;
			}
			
			return image;
        }
	}
/*
	public List<StreamedContent> lista(){
		produtos = dao.lista("Produto");
		BufferedImage buffer;
		File file = new File("C:\\Users\\Titan\\Pictures\\pasta\\foto.jpg");
		FileInputStream input = null;
		try {
			buffer = ImageIO.read(new ByteArrayInputStream(foto.getImagem()));
			boolean write = ImageIO.write(buffer, "image/jpeg", file);
			input = new FileInputStream(file);
			file.deleteOnExit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 
	}*/
	


	public PresenteDAO getDao() {
		return dao;
	}

	public void setDao(PresenteDAO dao) {
		this.dao = dao;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	
	public List<Presente> getPresentes() {
		return presentes;
	}

	public void setPresentes(List<Presente> presentes) {
		this.presentes = presentes;
	}

	public Presente getPresente() {
		return presente;
	}

	public void setPresente(Presente presente) {
		this.presente = presente;
	}
	
	
}