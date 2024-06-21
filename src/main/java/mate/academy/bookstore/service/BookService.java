package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<Book> getAll();

    BookDto getBookById(Long id);

    void deleteById(Long id);
}
