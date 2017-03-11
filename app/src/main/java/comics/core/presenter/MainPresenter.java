package comics.core.presenter;

import java.util.ArrayList;

import comics.core.model.entity.Comic;
import comics.core.model.entity.ComicDataWrapper;
import comics.core.model.manager.ComicDataManager;
import comics.core.view.MainContract;
import comics.ui.custom.loader.GlideLoader;
import comics.ui.main.ComicAdapter;
import pe.nextdots.comics.R;

/**
 * Created by Renzo D. Santillán Ch. on 09/03/2017.12:06 AM
 * http://rsantillanc.pe.hu/me/
 */

public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.ViewAction {

    private ComicDataManager comicManager;
    private ComicAdapter comicAdapter;
    private final ArrayList<Comic> comicList = new ArrayList<>();

    public MainPresenter() {
    }

    @Override
    public void start() {
        if (comicManager == null)
            comicManager = ComicDataManager.newInstance(this);
    }

    @Override
    public void onGetComics(int _limit) {
        mvpView.showLoader(true);
        comicManager.addLimit(_limit);
        comicManager.getComicsFromServer();
    }

    @Override
    public void onDone(Object object) {
        if (object instanceof ComicDataWrapper) {
            comicList.clear();
            comicList.addAll(((ComicDataWrapper) object).getComics());
            notifyComicAdapter();
        }
    }

    private void notifyComicAdapter() {
        if (comicAdapter == null) {
            comicAdapter = new ComicAdapter(comicList, new GlideLoader(mvpView.context(), R.drawable.marvel_default));
            mvpView.getComicRecyclerV().setAdapter(comicAdapter);
            comicAdapter.notifyDataSetChanged();
            return;
        }
        comicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String _message) {
        mvpView.showMessage(_message);
    }

    @Override
    public void onTimeout() {
        mvpView.showNetworkErrorMessage(R.string.error_timeout);
    }

    @Override
    public void onConnectError() {
        mvpView.showNetworkErrorMessage(R.string.error_server_connect);
    }

    @Override
    public void onComplete() {
        mvpView.showLoader(false);
    }
}
