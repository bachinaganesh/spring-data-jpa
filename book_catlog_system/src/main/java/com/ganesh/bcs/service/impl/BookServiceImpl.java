package com.ganesh.bcs.service.impl;

import com.ganesh.bcs.dtos.request.BookRequestDTO;
import com.ganesh.bcs.dtos.response.BookResponseDTO;
import com.ganesh.bcs.models.Book;
import com.ganesh.bcs.repository.BookRepository;
import com.ganesh.bcs.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = this.modelMapper.map(bookRequestDTO, Book.class);
        Book savedBook = this.bookRepository.save(book);
        return this.modelMapper.map(savedBook, BookResponseDTO.class);
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return this.modelMapper.map(book, BookResponseDTO.class);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return books.stream()
                .map(book -> this.modelMapper.map(book, BookResponseDTO.class))
                .toList();
    }

    @Override
    public BookResponseDTO updateBookById(Long id, BookRequestDTO bookRequestDTO) {
        this.bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        Book book = this.modelMapper.map(bookRequestDTO, Book.class);
        book.setId(id);
        Book savedBook = this.bookRepository.save(book);
        return this.modelMapper.map(savedBook, BookResponseDTO.class);
    }

    @Override
    public String deleteBookById(Long id) {
        Book book =  this.bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        this.bookRepository.delete(book);
        return "Book has been deleted";
    }
}
