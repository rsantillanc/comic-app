package comics.core.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.Locale;

import comics._utility.C;
import comics._utility.DateUtility;
import comics._utility.Navigator;
import comics.core.model.entity.CharacterSummary;
import comics.core.model.entity.Comic;
import comics.core.model.entity.CreatorSummary;
import comics.core.model.manager.ComicDataManager;
import comics.core.view.DetailContract;
import io.realm.RealmList;
import pe.nextdots.comics.R;

/**
 * Created by Renzo D. Santillán Chavez on 22/03/2017.
 */

public class DetailPresenter extends BasePresenter<DetailContract.DetailView> implements DetailContract.ViewAction {

    private Comic comic;
    private ComicDataManager comicDataManager;

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
        comicDataManager = ComicDataManager.newInstance(this);
    }

    private void showComicDetails() {
        if (comic == null) return;
        showComicPicture();
        showComicTitle();
        showComicDescription();
        showComicDate();
        showComicIndicator();
        showComicPages();
        showComicSeries();
        /**
         * Those will be better using and interface or "abstraction" called Summary.
         * e.g. CharacterSummary can implement Summary interface.
         */
        showComicCreators();
        showComicCharacters();
    }

    private void showComicCharacters() {
        if (comic.getCharacters().getAvailable() > 0) {
            RealmList<CharacterSummary> items = comic.getCharacters().getItems();
            for (CharacterSummary item : items) {
                View itemView = View.inflate(mvpView.context(), R.layout.item, null);
                //title
                ((AppCompatTextView) itemView.findViewById(R.id.title))
                        .setText(item.getName());
                //subtitle
                ((AppCompatTextView) itemView.findViewById(R.id.subtitle))
                        .setText(String.format(Locale.getDefault(), "%s%s%s", item.getRole(), "\n", item.getResourceUri()));
                //Add view
                mvpView.addComicCharacter(itemView);
            }
        } else
            mvpView.showCharacters(false);
    }

    private void showComicCreators() {
        if (comic.getCreators().getAvailable() > 0) {
            RealmList<CreatorSummary> items = comic.getCreators().getItems();
            for (CreatorSummary item : items) {
                View itemView = View.inflate(mvpView.context(), R.layout.item, null);
                //title
                ((AppCompatTextView) itemView.findViewById(R.id.title))
                        .setText(item.getName());
                //subtitle
                ((AppCompatTextView) itemView.findViewById(R.id.subtitle))
                        .setText(String.format(Locale.getDefault(), "%s%s%s", item.getRole(), "\n", item.getResourceUri()));
                //Add view
                mvpView.addComicCreator(itemView);
            }
        } else
            mvpView.showCreators(false);
    }

    private void showComicSeries() {
        //Show series
        mvpView.setComicNameSeries(comic.getSeries().getName());
        boolean isNull = comic.getSeries().getResourceUri() == null;
        mvpView.setComicUrlSeries(isNull ? C.EMPTY : comic.getSeries().getResourceUri());
    }

    private void showComicPages() {
        //show pages
        mvpView.setComicPages(String.format(Locale.getDefault(), "%s%s", comic.getPageCount(), " pages"));
    }

    private void showComicIndicator() {
        //show price and indicator
        if (comic.getPrices().get(0).price > 0) {
            mvpView.setComicPrice(String.format(Locale.getDefault(), "%s%s", comic.getPrices().get(0).price, C.USD));
            mvpView.updateProgressIndicator(comic.getPrices().get(0).price);
        }
    }

    private void showComicDate() {
        //show date
        if (comic.getDate() != null) {
            mvpView.setComicDate(DateUtility.F1.format(comic.getDate().getTime()));
        }
    }

    private void showComicDescription() {
        //show description
        //description may be null
        if (comic.getDescription() != null)
            mvpView.setComicDescription(comic.getDescription());
    }

    private void showComicTitle() {
        //show title
        mvpView.setComicTitle(comic.getTitle());
    }

    private void showComicPicture() {
        //show picture
        mvpView.loadComicPicture(comic.getThumbnail().getCompleteUrl());
    }

    @Override
    public void onCreate() {

    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onPictureClicked() {
        Navigator.goToPictureActivity(mvpView.context(), comic.getThumbnail().getCompleteUrl());
    }

    @Override
    public void onButtonFavouriteClicked(View view) {
        comicDataManager.saveChangesComic(comic);
        ((FloatingActionButton) view).setImageResource(comic.isFavourite()
                ? R.drawable.ic_favorite_checked :
                R.drawable.ic_favorite_unchecked);
        SharedPreferences pre = mvpView.context().getSharedPreferences(C.DEFAULT_DATE, Context.MODE_PRIVATE);
        pre.edit().putBoolean(C.Key.IS_FAVOURITE, comic.isFavourite()).apply();
    }


}
