package br.com.giftList.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validarEmail")
public class ValidarEmail implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String regexEmail = "^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$";
		
		String email = arg2.toString();
		
		email = email.replace("[","");
		email = email.replace("]",";");
		
		String [] listEmail = email.split(";");
		
		for (String str : listEmail) {
			str = str.trim();
			
			str = str.replaceAll(regexEmail, "");
			
			if(str.length()>0) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail inválido!",""));
			}
		}
	}
}
