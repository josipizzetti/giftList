package br.com.giftList.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.giftList.entity.Evento;

@FacesConverter (value="converterEvento")
public class ConverterEvento implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		List<Evento> eventos= (List<Evento>) arg1.getAttributes().get("lista");
		for (Evento evento : eventos) {
			if(evento.getNome().equals(arg2)){
				return evento;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Evento evento= (Evento) arg2;
		return evento.getNome();
	}
	

}
