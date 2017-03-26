package comics.core.view;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Renzo D. Santillán Chavez on 09/03/2017.
 */

public interface MainContract {
    interface ViewAction {

        void onGetComics();

        void onGetFavouriteComics();

        void onFilterComicTextQuery(String query);

        void onRefreshCurrentItem();

        void onCloseSession();
    }

    interface MainView extends Loadable {
        RecyclerView getComicRecyclerV();
    }
}
