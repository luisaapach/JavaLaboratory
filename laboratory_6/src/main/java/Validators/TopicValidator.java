/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="topicValidator")
public class TopicValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String topicName = String.valueOf(o);
        if (topicName.length()<=2)
            throw new ValidatorException(new FacesMessage("Expected a longer validator"));
    }
}
