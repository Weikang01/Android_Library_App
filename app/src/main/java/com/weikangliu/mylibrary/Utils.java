package com.weikangliu.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_KEY = "already_read";
    private static final String CURRENTLY_READING_KEY = "currently_reading";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";
    private static final String WANT_TO_READ_KEY = "want_to_read";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternative_db", Context.MODE_PRIVATE);

        if (getAllBooks() == null) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadReadBooks() == null) {
            editor.putString(ALREADY_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getFavoriteBooks() == null) {
            editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }


    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();

        String longDesc = "The events of 1Q84 take place in Tokyo during a fictionalized year of 1984, with the first volume set between April and June, the second between July and September, and the third between October and December.\n" +
                "\n" +
                "The book opens with a female character named Aomame (あおまめ) as she rides a taxi in Tokyo on her way to a work assignment. When the taxi gets stuck in a traffic jam on the Shibuya Route of the Shuto Expressway. The driver suggests that she get out of the car and climb down an emergency escape in order to make it to her important meeting, though he warns her that doing so might change the very nature of reality. After some hesitation, Aomame eventually makes her way to a hotel in Shibuya and poses as a hotel attendant in order to kill a hotel guest. She performs the murder with an ice pick that leaves almost no trace on its victim, leading investigators to conclude that he died a natural death from heart failure.\n" +
                "\n" +
                "Aomame starts to have bizarre experiences, noticing new details about the world that are subtly different. For instance, she notices Tokyo police officers carrying automatic handguns, when they had previously carried revolvers. Aomame checks her memories against the archives of major newspapers and finds that there were several recent major news stories of which she has no recollection. One of these stories concerned a group of extremists who were engaged in a stand-off with police in the mountains of Yamanashi Prefecture. Upon reading these articles, she concludes that she must be living in an alternative reality, which she calls \"1Q84\", and suspects that she entered it about the time she heard the Janáček Sinfonietta on the taxi radio.\n" +
                "\n" +
                "Other characters are also introduced by then. Tengo is a writer and maths teacher in a local school in Japan. Komatsu, his editor and mentor, asks Tengo to rewrite an awkwardly written but otherwise promising manuscript, Air Chrysalis (空気さなぎ). Komatsu wants to submit the novel for a prestigious literary prize and promote its author as a new literary prodigy. Tengo has reservations about rewriting another author's work, and especially that of a high-school student. He agrees to do so only if he can meet with the original writer, who goes by the strange pen name \"Fuka-Eri\", and ask for her permission. Fuka-Eri, however, tells Tengo to do as he likes with the manuscript.\n" +
                "\n" +
                "Soon it becomes clear that Fuka-Eri, who is dyslexic, neither wrote the manuscript on her own, nor submitted it to the contest herself. Tengo's discomfort with the project deepens upon finding out that others must be involved. To address his concerns of her past, Fuka-Eri takes Tengo to meet her current guardian, a man called Professor Ebisuno-sensei (戎野先生), or simply \"Sensei\" to Fuka-Eri. Tengo learns that Fuka-Eri's parents were members of a commune called \"Takashima\" (タカシマ). Her father, Tamotsu Fukada (深田保) was Ebisuno's friend and colleague, but they did not see eye-to-eye on their subject. Fukada thought of Takashima as a utopia; Ebisuno described the commune as a place where people were turned into unthinking robots. Fuka-Eri, whom Ebisuno-sensei nicknames \"Eri\" (エリ), was only a small child at the time.\n" +
                "\n" +
                "In 1974, Fukada and 30 members founded a new commune called \"Sakigake\" (さきがけ). The young members of the commune worked hard under Fukada's leadership, but eventually disagreements led to the radical faction of \"Sakigake\" (さきがけ) to form a new commune called \"Akebono\" (あけぼの). The Akebono commune eventually has a gunfight with police near Lake Motosu (本栖湖) in Yamanashi Prefecture. Shortly after, Fuka-Eri appears on Ebisuno-sensei's doorstep. She does not speak and will not explain what happened to her. When Ebisuno attempts to contact Fukada at Sakigake, he is told that Fukada is unavailable. Ebisuno thereby becomes Fuka-Eri's guardian, and by the time of 1Q84's present, they have not heard from her parents for seven years, leading Ebisuno to fear the worst.";

        books.add(new Book(1, "1Q84", "Murakami Haruki", 1350, "https://kbimages1-a.akamaihd.net/8f68d74a-d91f-40ba-a4ce-8b3fe88cff60/1200/1200/False/1q84-6.jpg", "A global event in itself, [which] passionately defends the power of the novel", longDesc));
        books.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://kbimages1-a.akamaihd.net/cdfba115-03be-4d70-b7ef-e5d69c446f2b/1200/1200/False/the-myth-of-sisyphus-and-other-essays-1.jpg", "One of the most influential works of this century, this is a crucial exposition of existentialist thought.", longDesc));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static synchronized Utils getInstance(Context c) {
        if (instance == null) {
            instance = new Utils(c);
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
    }

    public ArrayList<Book> getAlreadReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(ALREADY_READ_KEY, null), type);
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(WANT_TO_READ_KEY, null), type);    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_KEY, null), type);    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null), type);    }

    public Book getBookByID(int id) {
        ArrayList<Book> books = getAllBooks();
        if (books != null){
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyReadBooks(Book b) {

        ArrayList<Book> books = getAlreadReadBooks();
        if (books != null){
            if (books.add(b)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_KEY);
                editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToReadBooks(Book b) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null){
            if (books.add(b)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_KEY);
                editor.putString(WANT_TO_READ_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadBooks(Book b) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null){
            if (books.add(b)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_KEY);
                editor.putString(CURRENTLY_READING_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavoriteBooks(Book b) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null){
            if (books.add(b)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean delFromAlreadyReadBooks(Book b) {
        ArrayList<Book> books = getAlreadReadBooks();
        if (books != null){
            for (Book b_: books){
                if (b_.getId() == b.getId()){
                    if (books.remove(b_)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_KEY);
                        editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean delFromWantToReadBooks(Book b) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null){
            for (Book b_: books){
                if (b_.getId() == b.getId()){
                    if (books.remove(b_)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_KEY);
                        editor.putString(WANT_TO_READ_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean delFromCurrentlyReadBooks(Book b) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null){
            for (Book b_: books){
                if (b_.getId() == b.getId()){
                    if (books.remove(b_)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_KEY);
                        editor.putString(CURRENTLY_READING_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean delFromFavoriteBooks(Book b) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null){
            for (Book b_: books){
                if (b_.getId() == b.getId()){
                    if (books.remove(b_)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}