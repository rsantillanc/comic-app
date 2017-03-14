package comics.core.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import comics._utility.C;
import comics.core.model.entity.Comic;
import comics.core.model.entity.ComicDataWrapper;
import comics.core.model.manager.ComicDataManager;
import comics.core.view.MainContract;
import comics.core.view.ViewCallback;
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
    private final ViewCallback<Comic> onFavouriteClick = this::saveComicAsFavourite;
    private boolean isAutoSave = true;

    public MainPresenter() {
    }


    @Override
    public void start() {
        if (comicManager == null)
            comicManager = ComicDataManager.newInstance(this);
    }

    @Override
    public void onGetComics() {
        mvpView.showLoader(true);
        comicManager.addLimit(generateRandomLimit());
        comicManager.getComicsFromServer();
        isAutoSave = true;
    }

    @Override
    public void onGetFavouriteComics() {
        mvpView.showLoader(true);
        comicManager.getComicsFromDatabase();
        isAutoSave = false;
    }

    @Override
    public void filterComicTextQuery(String query) {
        comicAdapter.getFilter().filter(query);
    }


    private int generateRandomLimit() {
        final int MAX = 100;
        final int MIN = 30;
        return (int) (Math.random() * (MAX - MIN) + MIN);
    }

    @Override
    public void onDone(Object object) {
        if (object instanceof ComicDataWrapper) {
            comicList.clear();
            comicList.addAll(((ComicDataWrapper) object).getComics());
        } else if (object instanceof List) {
            comicList.clear();
            comicList.addAll((Collection<? extends Comic>) object);
        } else {
            int position = comicAdapter.getCurrentPosition();
            comicList.remove(position);
            comicList.add(position, (Comic) object);
            comicAdapter.notifyItemChanged(position);
            return;
        }
        notifyComicAdapter();
    }

    private void notifyComicAdapter() {
        Log.d(C.Tag.MAIN, "Total comics: " + comicList.size());
        if (comicAdapter == null) {
            comicAdapter = new ComicAdapter(comicList, new GlideLoader(mvpView.context(), R.drawable.marvel_default));
            comicAdapter.setOnFavouriteClick(onFavouriteClick);

            mvpView.getComicRecyclerV().setAdapter(comicAdapter);
            comicAdapter.notifyDataSetChanged();
            return;
        }
        comicAdapter.notifyDataSetChanged();
        comicAdapter.setAutoSave(isAutoSave);

    }

    private void saveComicAsFavourite(Comic comic) {
        comicManager.saveChangesComic(comic);
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
