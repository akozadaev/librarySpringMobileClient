package ru.tsutmb.libraryclientandroid;

import static ru.tsutmb.libraryclientandroid.fakedb.LibraryFakeDb.BOOK_LIST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import ru.tsutmb.libraryclientandroid.R;

import ru.tsutmb.libraryclientandroid.adapter.BookAdapter;
import ru.tsutmb.libraryclientandroid.domain.Book;
import ru.tsutmb.libraryclientandroid.fragment.AddBookFragment;
import ru.tsutmb.libraryclientandroid.rest.LibraryApi;
import ru.tsutmb.libraryclientandroid.rest.LibraryApiImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MSG_NAME = "bookFromListByPos";

    private AppCompatButton btnAdd;

    private FragmentTransaction transaction;

    private BookAdapter bookAdapter;

    private RecyclerView rvBooks;

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback;

    private final LibraryApi libraryApi = new LibraryApiImpl(this);

    @Override
    protected void onResume() {
        super.onResume();

        libraryApi.fillBook();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        libraryApi.fillGenre();
        libraryApi.fillAuthor();

        rvBooks = findViewById(R.id.rv_books);
        bookAdapter = new BookAdapter(this, BOOK_LIST);
        rvBooks.setAdapter(bookAdapter);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(view -> {

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_main, new AddBookFragment());
            transaction.commit();
        });

        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
        ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                Book book = BOOK_LIST.get(position);

                if (swipeDir == ItemTouchHelper.LEFT) {
                    Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                    libraryApi.deleteBook(book.getId());

                }

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvBooks);
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        int size = fragments.size();
        if (size > 0)
            getSupportFragmentManager().beginTransaction().remove(fragments.get(size-1)).commit();
        else
            finish();
    }

    public void update() {

        bookAdapter.notifyDataSetChanged();
    }

}