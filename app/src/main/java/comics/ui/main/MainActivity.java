package comics.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import comics._utility.MenuColorizer;
import comics._utility.NetworkUtility;
import comics.core.presenter.MainPresenter;
import comics.core.view.MainContract;
import comics.ui.BaseActivity;
import comics.ui.custom.dialog.ProfileDialog;
import comics.ui.custom.widget.ItemOffsetDecoration;
import pe.nextdots.comics.R;

public class MainActivity extends BaseActivity implements MainContract.MainView, SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    RecyclerView comicRecyclerV;

    private MainPresenter presenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolbar(true, R.layout.custom_title);
        createPresenter();
        loadComics();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onRefreshCurrentItem();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_action_search).getActionView();
        searchView.setQueryHint(getString(R.string.hint_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        MenuColorizer.showIcons(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.main_refresh:
                loadComics();
                break;
            case R.id.main_favourite:
                loadFavouriteComics(item);
                break;
            case R.id.main_profile:
                showUserProfile();
                break;
            case R.id.main_logout:
                logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showUserProfile() {
        ProfileDialog profileDialog = ProfileDialog.newInstance(view -> logout());
        profileDialog.show(getSupportFragmentManager(), ProfileDialog.class.getSimpleName());
    }

    private void logout() {
        presenter.onCloseSession();
    }

    private void loadFavouriteComics(MenuItem menuItem) {
        if (isThereInternet()) {
            menuItem.setChecked(!menuItem.isChecked());
            if (menuItem.isChecked())
                presenter.onGetFavouriteComics();
            else presenter.onGetComics();
        }
    }

    private void loadComics() {
        if (isThereInternet())
            presenter.onGetComics();
        else presenter.onGetFavouriteComics();
    }

    private void createPresenter() {
        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.initialize();
    }

    private void setupRecyclerView() {
        //comicRecyclerV.setLayoutManager(new LinearLayoutManager(context(), LinearLayoutManager.VERTICAL,false));
        comicRecyclerV.addItemDecoration(new ItemOffsetDecoration(context(), R.dimen.item_decoration));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, //number of grid columns
                GridLayoutManager.VERTICAL);

        //Sets the gap handling strategy for StaggeredGridLayoutManager
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        comicRecyclerV.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public RecyclerView getComicRecyclerV() {
        return this.comicRecyclerV;
    }

    @Override
    public boolean isThereInternet() {
        if (!NetworkUtility.isThereInternetConnection(this)) {
            showNetworkErrorMessage(R.string.error_no_internet);
            return false;
        }
        return true;
    }

    @Override
    public void showNetworkErrorMessage(@StringRes int resId) {
        Snackbar.make(comicRecyclerV, resId, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.offline, view -> {
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .show();
    }

    @Override
    public void showLoader(boolean isLoading) {
        showToast(isLoading ? "Loading..." : "completed!");
    }

    @Override
    public void tryAgain() {
        presenter.onGetComics();
    }

    @Override
    public void showMessage(String message) {
        showSnack(comicRecyclerV, message);
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    @Override
    public Intent intent() {
        return getIntent();
    }

    @Override
    public void setupUiElements() {
        //Enabled instance for all views
        bindActivity(this);
        setupRecyclerView();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public boolean onQueryTextSubmit(String _query) {
        presenter.onFilterComicTextQuery(_query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String _query) {
        presenter.onFilterComicTextQuery(_query);
        return true;
    }
}
