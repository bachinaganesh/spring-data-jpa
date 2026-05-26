package com.ganesh.bcs.service;

import com.ganesh.bcs.dtos.request.BookRequestDTO;
import com.ganesh.bcs.dtos.response.BookResponseDTO;

import java.util.List;

public interface IBookService {

    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);

    BookResponseDTO getBookById(Long id);

    List<BookResponseDTO> getAllBooks();

    BookResponseDTO updateBookById(Long id, BookRequestDTO bookRequestDTO);

    String deleteBookById(Long id);
}
