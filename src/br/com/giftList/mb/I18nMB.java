package br.com.giftList.mb;

import java.util.Locale;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean
@ApplicationScoped
public class I18nMB {

	public String mudarIdioma(String locale){
		if (!"".equals(locale)){
			mudarLocalidade(new Locale(locale));
		}else {
			mudarLocalidade(new Locale("pt","BR"));
		}
		return "";
	}

	private void mudarLocalidade(Locale locale){
		//Retorna o componente de raiz que est� associado com o presente pedido.
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		//Retorna a inst�ncia do aplicativo associado com este aplicativo web.
		FacesContext.getCurrentInstance().getApplication().setDefaultLocale(locale);
	}

}
