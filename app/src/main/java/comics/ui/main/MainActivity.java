package comics.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import comics.core.presenter.MainPresenter;
import comics.core.view.MainContract;
import comics.ui.BaseActivity;
import pe.nextdots.comics.R;

public class MainActivity extends BaseActivity implements MainContract.MainView {

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
        createToolbar();
        createPresenter();
        setupUiElements();

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.main_action_refresh:
                refreshComicList();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void createPresenter() {
        presenter = new MainPresenter();
        presenter.attachView(this);
    }

    private void setupRecyclerView() {
        comicRecyclerV.setLayoutManager(new StaggeredGridLayoutManager(0, StaggeredGridLayoutManager.VERTICAL));
        comicRecyclerV.setHasFixedSize(false);
        comicRecyclerV.addItemDecoration(new DividerItemDecoration(context(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void refreshComicList() {
        showToast("Loading marvel comics!");
    }

    @Override
    public boolean isThereInternet() {
        return false;
    }

    @Override
    public void showInternetErrorMessage(@StringRes int noInternetResId) {

    }

    @Override
    public void showLoader(boolean isLoading) {

    }

    @Override
    public void tryAgain() {

    }

    @Override
    public void showMessage(String message) {

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
    }

    @Override
    public void finishActivity() {

    }
}
