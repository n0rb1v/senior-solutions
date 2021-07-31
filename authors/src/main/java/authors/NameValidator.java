package authors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<IsValidName, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null &&
                s.length() > 3 &&
                Character.isUpperCase(s.charAt(0)) &&
                s.contains(" ");
    }
}
