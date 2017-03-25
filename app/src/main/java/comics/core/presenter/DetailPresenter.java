package comics.core.presenter;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.Locale;

import comics._utility.C;
import comics._utility.DateUtility;
import comics._utility.Navigator;
import comics.core.model.entity.CharacterSummary;
import comics.core.model.entity.Comic;
import comics.core.model.entity.CreatorSummary;
import comics.core.view.DetailContract;
import io.realm.RealmList;
import pe.nextdots.comics.R;

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
        mvpView.setComicUrlSeries(comic.getSeries().getResourceUri());
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
        mvpView.setComicDate(DateUtility.F1.format(comic.getDate()));
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
        Navigator.goToPictureActivity(mvpView.context(),comic.getThumbnail().getCompleteUrl());
    }


}
