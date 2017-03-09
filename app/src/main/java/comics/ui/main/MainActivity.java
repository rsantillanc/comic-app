package comics.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.Menu;
import android.view.MenuItem;

import comics.core.view.MainContract;
import comics.ui.BaseActivity;
import pe.nextdots.comics.R;

public class MainActivity extends BaseActivity implements MainContract.MainView{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshComicList() {
        
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
        //// TODO: 09/03/2017  
    }

    @Override
    public void finishActivity() {

    }
}
