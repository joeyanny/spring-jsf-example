package example.spring.jsf.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@FacesValidator("characterValidator")
public class CharacterValidator implements Validator {

    private Pattern regexPattern;

    public CharacterValidator(String regex) {
        regexPattern = Pattern.compile(regex);
    }

    /**
     * Validate the field contains valid characters only.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value != null) {
            String name = value.toString();

            if(name == null || name.isEmpty()) {
                FacesMessage message = new FacesMessage("Field is mandatory: " + component.getId());
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }

            if(!regexPattern.matcher(name).matches()) {
                FacesMessage message = new FacesMessage("Invalid value: " + component.getId());
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }

            return;
        }

        FacesMessage message = new FacesMessage("Field is mandatory: " + component.getId());
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }
}
