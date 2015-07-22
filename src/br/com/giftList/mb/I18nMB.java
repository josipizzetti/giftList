package br.com.giftList.mb;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean
public class I18nMB {

	private String pais;
	private String linguagem;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String mudarIdioma(){
		if (!"".equals(pais)){
			mudarLocalidade(new Locale(linguagem, pais));
		}else {
			mudarLocalidade(new Locale(linguagem));
		}
		return null;
	}

	private void mudarLocalidade(Locale locale){
		//Retorna o componente de raiz que está associado com o presente pedido.
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		//Retorna a instância do aplicativo associado com este aplicativo web.
		FacesContext.getCurrentInstance().getApplication().setDefaultLocale(locale);
	}

}
