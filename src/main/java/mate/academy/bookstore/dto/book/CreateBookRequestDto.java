package mate.academy.bookstore.dto.book;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateBookRequestDto(
        @NotBlank(message = "Title is mandatory")
        @Size(max = 255,
                message = "Title should not exceed 255 characters")
        String title,

        @NotBlank(message = "Author is mandatory")
        @Size(max = 255,
                message = "Author should not exceed 255 characters")
        String author,

        @NotBlank(message = "ISBN is mandatory")
        @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
                message = "Invalid ISBN format")
        String isbn,

        @NotNull(message = "Price is mandatory")
        @DecimalMin(value = "0.0", inclusive = false,
                message = "Price must be greater than zero")
        BigDecimal price,

        @Size(max = 1000,
                message = "Description should not exceed 1000 characters")
        String description,

        @NotBlank(message = "Cover image URL is mandatory")
        @Pattern(regexp = "^(http(s?):)([/|.\\w\\s-])*.jpg$",
                message = "Invalid URL format for cover image")
        String coverImage
) {
}
