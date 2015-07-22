package br.com.giftList.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.giftList.dao.PresenteDAO;
import br.com.giftList.entity.Presente;

@ManagedBean
@ViewScoped
public class PresenteSelecionadoMB {
	
	Presente presente = new Presente();
	

	public PresenteSelecionadoMB(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String parameterPresente = request.getParameter("presenteSelecionadoId");
		PresenteDAO presenteDAO = new PresenteDAO();
		presente = presenteDAO.buscaId(Integer.parseInt(parameterPresente), Presente.class);
	}

	public Presente getPresente() {
		return presente;
	}
	
	public void setPresente(Presente presente) {
		this.presente = presente;
	}
}
