package comics.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import comics.core.presenter.DetailPresenter;
import comics.core.view.DetailContract;
import comics.ui.BaseActivity;
import comics.ui.custom.widget.MarvelTextView;
import comics.ui.custom.widget.SquareImageView;
import pe.nextdots.comics.R;

public class DetailActivity extends BaseActivity implements DetailContract.DetailView {

    @BindView(R.id.picture_square_image_v)
    SquareImageView pictureSquareImageV;

    @BindView(R.id.title_text_v)
    AppCompatTextView titleTextV;

    @BindView(R.id.description_text_v)
    AppCompatTextView descriptionTextV;

    @BindView(R.id.date_text_v)
    AppCompatTextView dateTextV;

    @BindView(R.id.price_marvel_text_v)
    MarvelTextView priceMarvelTextV;

    @BindView(R.id.pages_text_v)
    AppCompatTextView pagesTextV;

    @BindView(R.id.indicator_progress_b)
    ProgressBar indicatorProgressB;


    private DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolbar(true, R.layout.custom_toobar_title);
        createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.onDestroy();
    }

    private void createPresenter() {
        presenter = new DetailPresenter();
        presenter.attachView(this);
        presenter.initialize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
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
        bindActivity(this);
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void loadComicPicture(String url) {
        Glide.with(this)
                .load(url)
                .into(pictureSquareImageV);
    }

    @Override
    public void setComicTitle(String _title) {
        titleTextV.setText(_title.trim());
    }

    @Override
    public void setComicDescription(String _description) {
        descriptionTextV.setText(_description.trim());
    }

    @Override
    public void setComicDate(String _date) {
        dateTextV.setText(_date.trim());
    }

    @Override
    public void setComicPrice(String _price) {
        priceMarvelTextV.setText(_price.trim());
    }

    @Override
    public void setComicPages(String _pages) {
        pagesTextV.setText(_pages);
    }

    @Override
    public void updateProgressIndicator(float _indicatorProgress) {
        indicatorProgressB.setProgress((int) _indicatorProgress);
    }
}
