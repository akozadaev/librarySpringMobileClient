package ru.tsutmb.libraryclientandroid.rest;

import ru.tsutmb.libraryclientandroid.domain.Book;

public interface LibraryApi {

    void fillBook();

    void fillGenre();

    void fillAuthor();

    void newBook(Book book);

    void updateBook(int id, String newBookName, String newAuthorName, String newGenreName);

    void deleteBook(int id);

}
