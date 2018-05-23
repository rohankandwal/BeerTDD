package com.itcse.beerrecepies.view.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.itcse.beerrecepies.R;
import com.itcse.beerrecepies.model.data.BeerDetails;
import com.itcse.beerrecepies.model.repository.ApiClient;
import com.itcse.beerrecepies.utils.EndlessScrollListener;
import com.itcse.beerrecepies.utils.GridSpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import timber.log.Timber;

public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeScreenContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.progress)
    View progress;
    @BindView(R.id.rvBeerRecepies)
    RecyclerView rvBeerRecepies;
    @BindView(R.id.tvEmptyList)
    View tvEmptyList;

    private HomeScreenPresenter presenter;

    private Handler searchHandler = new Handler();
    private Runnable searchRunnable;
    private String searchString;
    private EndlessScrollListener endlessScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        presenter = new HomeScreenPresenter(this, ApiClient.getAPI());
        presenter.getBeers(1);

        searchRunnable = new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(searchString)) {
                    searchBeerByName(searchString);
                }
            }
        };

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                searchBeerByName(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchString = newText;
                searchHandler.removeCallbacks(searchRunnable);
                searchHandler.postDelayed(searchRunnable, 500);
                return true;
            }
        });
        myActionMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Clearing the existing beer list
                loadNewBeerData(new ArrayList<BeerDetails>());
                // loading new data
                presenter.getBeers(1);
                searchString = null;
                return true;
            }
        });
        return true;
    }

    private void searchBeerByName(@NonNull final String query) {
        Timber.d(query);
        presenter.searchBeerByName(query);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.fab})
    void onClick(final View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void setBeerList(@NonNull final List<BeerDetails> beerList) {
        if (beerList.size() > 0) {
            if (rvBeerRecepies.getAdapter() == null) {
                final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
                endlessScrollListener = new EndlessScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        presenter.getBeers(page);
                    }
                };
                rvBeerRecepies.setLayoutManager(layoutManager);
                rvBeerRecepies.addItemDecoration(new GridSpacesItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.content_padding), true));
                rvBeerRecepies.setAdapter(new BeerListRecyclerAdapter(beerList));
                rvBeerRecepies.addOnScrollListener(endlessScrollListener);
            } else {
                final BeerListRecyclerAdapter adapter = (BeerListRecyclerAdapter) rvBeerRecepies.getAdapter();
                adapter.addMore(beerList);
            }
            tvEmptyList.setVisibility(View.GONE);
        }
    }

    @Override
    public void noBeerFound() {
        tvEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(@NonNull final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noBeerWithNameFound(String beerName) {
        loadNewBeerData(new ArrayList<BeerDetails>());
        tvEmptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void searchedBeerListFound(@NonNull final List<BeerDetails> beerDetails) {
        loadNewBeerData(beerDetails);
        tvEmptyList.setVisibility(View.GONE);
    }

    /**
     * Function to clear the existing beer list and load new beer list data
     *
     * @param beerDetails List containing new Beer list
     */
    private void loadNewBeerData(@NonNull List<BeerDetails> beerDetails) {
        if (rvBeerRecepies.getAdapter() != null) {
            ((BeerListRecyclerAdapter) rvBeerRecepies.getAdapter()).refreshData(beerDetails);
            if (endlessScrollListener != null) {
                endlessScrollListener.resetState();
            }
        }
    }

    @Override
    protected void onStop() {
        presenter.destroy();
        super.onStop();
    }

    @Override
    public void showProgress(boolean showProgress) {
        progress.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }

    @Override
    public Observable<?> showError(@Nullable int errorMessage, final Observable throwable) {
        showToast(getString(errorMessage));
        return Observable.just(null);
    }
}
