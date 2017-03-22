package comics.core.presenter;

import java.util.Locale;

import comics._utility.C;
import comics._utility.DateUtility;
import comics.core.model.entity.Comic;
import comics.core.view.DetailContract;

/**
 * Created by Renzo D. Santillán Chavez on 22/03/2017.
 */

public class DetailPresenter extends BasePresenter<DetailContract.DetailView> implements DetailContract.ViewAction {

    private Comic comic;

    @Override
    public void onDone(Object object) {
        //Do nothing
    }

    @Override
    public void onError(String message) {
        //Do nothing
    }

    @Override
    public void onTimeout() {
        //Do nothing
    }

    @Override
    public void onConnectError() {
        //Do nothing
    }

    @Override
    public void onComplete() {
        //Do nothing
    }

    @Override
    public void initialize() {
        mvpView.setupUiElements();
        comic = mvpView.intent().getParcelableExtra(C.Extra.COMIC);
        showComicDetails();
    }

    private void showComicDetails() {
        if (comic == null) return;
        //show picture
        mvpView.loadComicPicture(comic.getThumbnail().getCompleteUrl());
        //show title
        mvpView.setComicTitle(comic.getTitle());
        //show description
        //description may be null
        if (comic.getDescription() != null)
            mvpView.setComicDescription(comic.getDescription());
        //show date
        mvpView.setComicDate(DateUtility.F1.format(comic.getDate()));
        //show price and indicator
        if (comic.getPrices().get(0).price > 0) {
            mvpView.setComicPrice(String.format(Locale.getDefault(), "%s%s", comic.getPrices().get(0).price, C.USD));
            mvpView.updateProgressIndicator(comic.getPrices().get(0).price);
        }
    }

    @Override
    public void onCreate() {

    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onPictureClick() {

    }


}
