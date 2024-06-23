package mate.academy.bookstore.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.BookRepository;
import mate.academy.bookstore.service.BookService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        Book book = bookRepository.save(bookMapper.toModel(bookRequestDto));
        return bookMapper.toDto(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book employee = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find employee by id " + id)
        );
        return bookMapper.toDto(employee);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
