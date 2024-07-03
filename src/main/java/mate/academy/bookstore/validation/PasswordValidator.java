package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordValidator implements ConstraintValidator<FieldMatch, Object> {

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        String firstObj = constraintAnnotation.first();
        String secondObj = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object firstObj,
                           final ConstraintValidatorContext secondObj) {
        return Objects.equals(firstObj, secondObj);
    }
}
