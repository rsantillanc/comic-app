package comics.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import comics._utility.C;
import comics.core.presenter.DetailPresenter;
import comics.core.view.DetailContract;
import comics.ui.BaseActivity;
import comics.ui.custom.widget.MarvelTextView;
import comics.ui.custom.widget.VerticalRectangleImageView;
import pe.nextdots.comics.R;

public class DetailActivity extends BaseActivity implements DetailContract.DetailView {

    @BindView(R.id.picture_square_image_v)
    VerticalRectangleImageView pictureVerticalImageV;

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

    @BindView(R.id.name_series_text_v)
    AppCompatTextView nameSeriesTextV;

    @BindView(R.id.url_series_text_v)
    AppCompatTextView urlSeriesTextV;

    @BindView(R.id.characters_linear_l)
    LinearLayout charactersLinearL;

    @BindView(R.id.creators_linear_l)
    LinearLayout creatorsLinearL;

    @BindView(R.id.favorite_floating_b)
    FloatingActionButton favoriteFloatingB;


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
        setupVerticalImageView();
        setupFloatingButton();
    }

    private void setupFloatingButton() {
        favoriteFloatingB.setOnClickListener(this::onFavouriteClick);
        boolean isFavourite = intent().getBooleanExtra(C.Key.IS_FAVOURITE, false);
        favoriteFloatingB.setImageResource(isFavourite ? R.drawable.ic_favorite_checked : R.drawable.ic_favorite_unchecked);
    }

    private void onFavouriteClick(View view) {
        presenter.onButtonFavouriteClicked(view);
    }

    private void setupVerticalImageView() {
        pictureVerticalImageV.setOnClickListener(this::onClickPicture);
    }

    public void onClickPicture(View v) {
        presenter.onPictureClicked();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void loadComicPicture(String _url) {
        Glide.with(this)
                .load(_url)
                .into(pictureVerticalImageV);
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
    public void setComicNameSeries(String _name_series) {
        nameSeriesTextV.setText(_name_series.trim());
    }

    @Override
    public void setComicUrlSeries(String _url_series) {
        urlSeriesTextV.setText(_url_series.trim());
    }

    @Override
    public void updateProgressIndicator(float _indicator_progress) {
        indicatorProgressB.setProgress((int) _indicator_progress);
    }

    @Override
    public void addComicCharacter(View _character_child) {
        charactersLinearL.addView(_character_child);
    }

    @Override
    public void addComicCreator(View _creator_child) {
        creatorsLinearL.addView(_creator_child);
    }

    @Override
    public void showCharacters(boolean _is_true) {
        charactersLinearL.setVisibility(_is_true ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showCreators(boolean _is_true) {
        creatorsLinearL.setVisibility(_is_true ? View.VISIBLE : View.GONE);
    }
}
