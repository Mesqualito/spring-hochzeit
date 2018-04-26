package rocks.gebsattel.hochzeit.commands.validators

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import rocks.gebsattel.hochzeit.commands.CustomerForm

import javax.validation.metadata.BeanDescriptor

@Component
class CustomerFormValidator implements Validator {

    @Override
    boolean supports(Class<?> someClass) {
        CustomerForm.class.equals(someClass)
    }

    @Override
    void validate(Object target, Errors errors) {

        CustomerForm customerForm = (CustomerForm) target

        if(!customerForm.getPasswordText().equals(customerForm.getPasswordTextConf())){
            errors.rejectValue("passwordText", "PasswordsDontMatch.customerForm.passwordText", "Passwords don't match!")
            errors.rejectValue("passwordTextConf", "PasswordsDontMatch.customerForm.passwordTextConf", "Passwords don't match!")
        }
    }

}
