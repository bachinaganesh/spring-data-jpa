package com.ganesh.bcs.controller;

import com.ganesh.bcs.dtos.request.BookRequestDTO;
import com.ganesh.bcs.dtos.response.BookResponseDTO;
import com.ganesh.bcs.models.Book;
import com.ganesh.bcs.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO bookResponseDTO = this.bookService.createBook(bookRequestDTO);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO bookResponseDTO = this.bookService.getBookById(id);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> bookResponseDTOList = this.bookService.getAllBooks();
        return new ResponseEntity<>(bookResponseDTOList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO bookResponseDTO = this.bookService.updateBookById(id, bookRequestDTO);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        String response = this.bookService.deleteBookById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
