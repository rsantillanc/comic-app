package comics.core.presenter;

import comics._utility.C;
import comics.core.model.entity.Comic;
import comics.core.view.DetailContract;

/**
 * Created by Renzo D. Santillán Chavez on 22/03/2017.
 */

public class DetailPresenter extends BasePresenter<DetailContract.DetailView> implements DetailContract.ViewAction {

    private Comic comic;

    @Override
    public void onDone(Object object) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onConnectError() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void initialize() {
        mvpView.setupUiElements();
        comic = mvpView.intent().getParcelableExtra(C.Extra.COMIC);
        showComicDetails();
    }

    private void showComicDetails() {
        if (comic == null) return;
        mvpView.loadComicPicture(comic.getThumbnail().getCompleteUrl());
        mvpView.setComicTitle(comic.getTitle());
        //description may be null
        if (comic.getDescription() != null)
            mvpView.setComicDescription(comic.getDescription());
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
