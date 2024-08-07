package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.service.BookService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management",
        description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book",
            description = "Endpoint to create a new book in the system.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookRequestDto) {
        return bookService.save(bookRequestDto);
    }

    @GetMapping
    @Operation(summary = "Get all books",
            description = "Retrieve all books with pagination, "
                    + "each page contains 5 books.")
    @PreAuthorize("hasAnyRole('USER')")
    public List<BookDto> getAll(@ParameterObject @PageableDefault Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID",
            description = "Retrieve a book by its ID. "
                    + "Throws an exception if the book is not found.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by ID",
            description = "Delete a book by its ID. "
                    + "If the is_deleted field is true, "
                    + "the book will not be returned in queries.")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
