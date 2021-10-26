package com.kruszynski.plubreakers.codefinder.all;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kruszynski.plubreakers.MainActivity;
import com.kruszynski.plubreakers.R;
import com.kruszynski.plubreakers.codefinder.adapter.CodeFinderAdapter;
import com.kruszynski.plubreakers.codefinder.decorator.GridSpacingItemDecoration;
import com.kruszynski.plubreakers.codefinder.model.Product;
import com.kruszynski.plubreakers.codetest.model.ProductTest;
import com.kruszynski.plubreakers.db.AppDatabase;

import java.util.List;
import java.util.stream.Collectors;

public class CodeFinderAllActivity extends AppCompatActivity {
    private CodeFinderAdapter adapter;
    private List<Product> products;
    private Menu mOptionsMenu;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_finder_all);
        setContentView(R.layout.code_finder_displayer);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);
        getSupportActionBar().setTitle(getString(R.string.all));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
        displayAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_code_finder, menu);
        mOptionsMenu = menu;

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
            return true;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displayAll() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_code_finder);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2
        );
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initComponents() {
        List<ProductTest> productTests = AppDatabase.instance(getApplicationContext()).productTestDao().getAll();
        products = productTests.stream().map(
                l -> new Product(l.getId(), l.getName(), l.getCodePLU(), null, l.getProductType(), l.getImage())).collect(Collectors.toList());
        adapter = new CodeFinderAdapter(products);
    }

    private Menu getMenu() {
        return mOptionsMenu;
    }
}