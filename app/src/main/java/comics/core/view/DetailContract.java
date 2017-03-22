package comics.core.view;

/**
 * Created by Renzo D. Santillán Chavez on 22/03/2017.
 */

public interface DetailContract {
    interface ViewAction{
        void onPictureClick();
    }

    interface DetailView extends View{
        void loadComicPicture(String _url);
        void setComicTitle(String _title);
        void setComicDescription(String _description);
        void setComicDate(String _date);
        void setComicPrice(String _price);
        void setComicPages(String _pages);
        void updateProgressIndicator(float _indicatorProgress);
    }
}
