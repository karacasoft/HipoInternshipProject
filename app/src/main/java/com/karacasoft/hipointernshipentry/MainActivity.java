package com.karacasoft.hipointernshipentry;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.karacasoft.hipointernshipentry.photos.PhotosFragment;
import com.karacasoft.hipointernshipentry.search.SearchViewInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    SearchView searchView;

    private Unbinder unbinder;

    private SearchViewInterface searchViewInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentLayout, PhotosFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if(searchViewInterface != null) {
                    searchViewInterface.onClose();
                    getSupportFragmentManager().popBackStack();
                    searchViewInterface = null;
                }
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return searchViewInterface != null && searchViewInterface.onSearch(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return searchViewInterface != null && searchViewInterface.onTextChanged(newText);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_search) {
            PhotosFragment fragment = PhotosFragment.newSearchInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentLayout, fragment)
                    .addToBackStack(null)
                    .commit();

            return true;
        }
        return false;
    }

    public void setSearchViewInterface(SearchViewInterface searchViewInterface) {
        this.searchViewInterface = searchViewInterface;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
