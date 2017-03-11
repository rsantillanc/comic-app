package comics.core.presenter;

import comics.core.model.manager.ComicDataManager;
import comics.core.view.MainContract;

/**
 * Created by Renzo D. Santillán Ch. on 09/03/2017.12:06 AM
 http://rsantillanc.pe.hu/me/
 */

public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.ViewAction {

    private ComicDataManager comicManager;

    public MainPresenter() {
    }

    @Override
    public void start() {
        if (comicManager == null)
            comicManager = ComicDataManager.newInstance();
    }

    @Override
    public void onGetComics(int _limit) {
        comicManager.addLimit(_limit);
        comicManager.getComicsFromServer();
    }


}
