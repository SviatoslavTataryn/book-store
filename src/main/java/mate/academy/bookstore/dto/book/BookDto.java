package mate.academy.bookstore.dto.book;

import java.math.BigDecimal;

public record BookDto(Long id,
                      String title,
                      String author,
                      String isbn,
                      BigDecimal price,
                      String description,
                      String coverImage,
                      boolean deleted) {
}
