package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mate.academy.bookstore.validation.FieldMatch;
import org.hibernate.validator.constraints.Length;

@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords must match")
public record UserRegistrationRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 8, max = 35)
        String password,
        @NotBlank
        @Length(min = 8, max = 35)
        String repeatPassword,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        @Length(min = 5, max = 100)
        String shippingAddress,
        @NotNull
        String role
) {
}
