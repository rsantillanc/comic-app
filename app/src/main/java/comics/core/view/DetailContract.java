package comics.core.view;

/**
 * Created by Renzo D. Santillán Chavez on 22/03/2017.
 */

public interface DetailContract {
    interface ViewAction {
        void onPictureClicked();

        void onButtonFavouriteClicked(android.view.View view);
    }

    interface DetailView extends View {
        void loadComicPicture(String _url);

        void setComicTitle(String _title);

        void setComicDescription(String _description);

        void setComicDate(String _date);

        void setComicPrice(String _price);

        void setComicPages(String _pages);

        void setComicNameSeries(String _name_series);

        void setComicUrlSeries(String _url_series);

        void updateProgressIndicator(float _indicator_progress);

        void addComicCharacter(android.view.View _character_child);

        void addComicCreator(android.view.View _creator_child);

        void showCharacters(boolean _is_true);

        void showCreators(boolean _is_true);
    }
}
