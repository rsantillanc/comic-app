package comics.core.presenter;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import comics._utility.C;
import comics._utility.Navigator;
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
    private final ViewCallback<Pair<Integer, Comic>> onFavouriteClick = this::saveComicAsFavourite;
    private boolean isAutoSave = true;

    public MainPresenter() {
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
        comicAdapter.setAutoSave(false);
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
    @SuppressWarnings("unchecked")
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

    /**
     * Callback from Adapter
     * @param pair (first = {@link Integer} action and second = {@link Comic})
     */
    private void saveComicAsFavourite(Pair<Integer, Comic> pair) {
        switch (pair.first) {
            case ComicAdapter.ITEM:
                Navigator.goToDetailActivity(mvpView.context(), pair.second);
                break;
            case ComicAdapter.FAVORITE:
                comicManager.saveChangesComic(pair.second);
                break;
        }

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


    @Override
    public void initialize() {
        mvpView.setupUiElements();
        if (comicManager == null)
            comicManager = ComicDataManager.newInstance(this);
    }

    @Override
    public void onCreate() {
        mvpView.setupUiElements();
    }

    @Override
    public void onDestroy() {

    }
}
