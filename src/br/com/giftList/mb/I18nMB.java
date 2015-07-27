package br.com.giftList.mb;

import java.util.Locale;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean
public class I18nMB {

	public String mudarIdioma(String locale){
			mudarLocalidade(new Locale("en", "US"));
		
		return "";
	}

	private void mudarLocalidade(Locale locale){
		//Retorna o componente de raiz que está associado com o presente pedido.
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		//Retorna a instância do aplicativo associado com este aplicativo web.
		FacesContext.getCurrentInstance().getApplication().setDefaultLocale(locale);
	}

}
