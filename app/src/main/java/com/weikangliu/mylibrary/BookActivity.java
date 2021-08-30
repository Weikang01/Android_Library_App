package com.weikangliu.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookID";

    ImageView imageView;
    Button btnCurrentReading, btnWantToRead, btnAlreadyRead, btnAddToFavorite;
    TextView txtBookNameName, txtAuthorName, txtPageNum, txtLongDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            int bookID = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookID != -1) {
                Book book = Utils.getInstance(this).getBookByID(bookID);
                if (book != null) {
                    SetValues(book);

                    handleAlreadyRead(book);
                    handleWantToRead(book);
                    handleCurrentRead(book);
                    handleFavoriteBook(book);
                }
            }
        }
    }

    private void handleFavoriteBook(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();

        boolean exists = false;

        for (Book b: favoriteBooks){
            if (b.getId() == book.getId()){
                exists = true;
                break;
            }
        }
        if (exists){
            btnAddToFavorite.setEnabled(false);
        } else{
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);
                        btnAlreadyRead.setEnabled(false);
                    } else{
                        Toast.makeText(BookActivity.this, "Ahhaha", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentRead(final Book book) {
        ArrayList<Book> currentlyReadingRead = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean exists = false;

        for (Book b: currentlyReadingRead){
            if (b.getId() == book.getId()){
                exists = true;
                break;
            }
        }
        if (exists){
            btnCurrentReading.setEnabled(false);
        } else{
            btnCurrentReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReadBooks(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                        btnCurrentReading.setEnabled(false);
                    } else{
                        Toast.makeText(BookActivity.this, "Ahhaha", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToRead(final Book book) {
        ArrayList<Book> wantToRead = Utils.getInstance(this).getWantToReadBooks();

        boolean exists = false;

        for (Book b: wantToRead){
            if (b.getId() == book.getId()){
                exists = true;
                break;
            }
        }
        if (exists){
            btnWantToRead.setEnabled(false);
        } else{
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWantToReadBooks(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                        btnAlreadyRead.setEnabled(false);
                    } else{
                        Toast.makeText(BookActivity.this, "Ahhaha", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and Disable Button,
     * Add the book to Already Read Book Array
     * @param book
     */
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyRead = Utils.getInstance(this).getAlreadReadBooks();

        boolean exists = false;

        for (Book b : alreadyRead) {
            if (b.getId() == book.getId()) {
                exists = true;
                break;
            }
        }
        if (exists) {
            btnAlreadyRead.setEnabled(false);
        } else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyReadBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                        btnAlreadyRead.setEnabled(false);
                    } else {
                        Toast.makeText(BookActivity.this, "Ahhaha", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void SetValues(Book book) {

        Glide.with(this).asBitmap().load(book.getImageUrl()).into(imageView);
        txtBookNameName.setText(book.getTitle());
        txtAuthorName.setText(book.getAuthor());
        txtPageNum.setText(String.valueOf(book.getPages()));
        txtLongDesc.setText(book.getLongDesc());
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);

        btnCurrentReading = findViewById(R.id.btnCurrentReading);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);

        txtBookNameName = findViewById(R.id.txtBookNameName);
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtPageNum = findViewById(R.id.txtPageNum);
        txtLongDesc = findViewById(R.id.txtLongDesc);
    }
}