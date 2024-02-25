package ru.tsutmb.libraryclientandroid.domain.mapper;

import ru.tsutmb.libraryclientandroid.domain.Genre;

import org.json.JSONException;
import org.json.JSONObject;

public class GenreMapper {

    public Genre genreFromBookJsonArray(JSONObject jsonObject) {

        Genre genre = null;
        try {

            genre = new Genre(
                    jsonObject.getJSONObject("genreDto").getInt("id"),
                    jsonObject.getJSONObject("genreDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return genre;
    }

    public Genre genreFromJsonArray(JSONObject jsonObject) {

        Genre genre = null;
        try {

            genre = new Genre(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return genre;
    }

}
