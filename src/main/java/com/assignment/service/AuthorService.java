package com.assignment.service;

import com.assignment.entity.Author;
import com.assignment.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    public Author findAuthorById(int id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can not find an author with this id"));
    }
}
