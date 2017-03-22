package comics.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import comics.core.presenter.DetailPresenter;
import comics.core.view.DetailContract;
import comics.ui.BaseActivity;
import comics.ui.custom.widget.SquareImageView;
import pe.nextdots.comics.R;

public class DetailActivity extends BaseActivity implements DetailContract.DetailView {

    @BindView(R.id.picture_square_image_v)
    SquareImageView pictureSquareImageV;

    @BindView(R.id.title_text_v)
    AppCompatTextView titleTextV;

    @BindView(R.id.description_text_v)
    AppCompatTextView descriptionTextV;


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
}
