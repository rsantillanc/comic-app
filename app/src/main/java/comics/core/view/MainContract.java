package comics.core.view;

import comics._utility.Loadable;

/**
 * Created by Renzo D. Santillán Chavez on 09/03/2017.
 */

public interface MainContract {
    interface ViewAction {
        void onGetComics();
    }

    interface MainView extends Loadable {
        void refreshComicList();
    }
}
