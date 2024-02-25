package ru.tsutmb.libraryclientandroid.fakedb;

import ru.tsutmb.libraryclientandroid.domain.Author;
import ru.tsutmb.libraryclientandroid.domain.Book;
import ru.tsutmb.libraryclientandroid.domain.Genre;

import java.util.ArrayList;
import java.util.List;

public class LibraryFakeDb {

    private LibraryFakeDb(){}

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public static final List<Genre> GENRE_LIST = new ArrayList<>();

    public static final List<Author> AUTHOR_LIST = new ArrayList<>();
}
