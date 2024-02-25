package ru.tsutmb.libraryclientandroid.domain.mapper;



import ru.tsutmb.libraryclientandroid.domain.Author;
import ru.tsutmb.libraryclientandroid.domain.Book;
import ru.tsutmb.libraryclientandroid.domain.Genre;

import org.json.JSONException;
import org.json.JSONObject;

public class BookMapper {

    public Book bookFromJsonArray(JSONObject jsonObject, Author author, Genre genre) {

        Book book = null;

        try {
            book = new Book(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    author,
                    genre,
                    null
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return book;
    }

}
