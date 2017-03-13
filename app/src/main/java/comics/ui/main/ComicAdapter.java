package comics.ui.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Locale;

import comics._utility.C;
import comics.core.model.entity.Comic;
import comics.core.model.entity.Price;
import comics.core.view.ViewCallback;
import comics.ui.custom.loader.ImageLoader;
import comics.ui.custom.widget.MarvelTextView;
import io.realm.Realm;
import pe.nextdots.comics.R;

/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.09:47 AM
 * http://rsantillanc.pe.hu/me/
 */


public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewH> implements Filterable {

    private ArrayList<Comic> comicList = new ArrayList<>();
    private ArrayList<Comic> toFilterComicList = new ArrayList<>();
    private final ImageLoader imageLoader;
    private Context context;
    private ViewCallback<Comic> onFavouriteClick;

    public ComicAdapter(ArrayList<Comic> _comicList, ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        this.comicList = _comicList;
        this.toFilterComicList.addAll(this.comicList);
    }


    @Override
    public ComicViewH onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ComicViewH(LayoutInflater.from(context).inflate(R.layout.item_comic, parent, false));
    }

    @Override
    public void onBindViewHolder(ComicViewH holder, int position) {
        setComicTitle(holder, position);
        setComicPrice(holder, position);
        setComicPhoto(holder, position);
        setComicFavourite(holder, position);
    }

    private void setComicFavourite(ComicViewH holder, int position) {
        boolean isFavourite = comicList.get(position).isFavourite();
        holder.favouriteImageV.setImageDrawable(ContextCompat.getDrawable(context, isFavourite ?
                R.drawable.ic_favorite_checked :
                R.drawable.ic_favorite_unchecked));
    }

    private void setComicPhoto(ComicViewH holder, int position) {
        imageLoader.load(comicList.get(position).getThumbnail().getCompleteUrl(), holder.photoImageV);
    }

    private void setComicTitle(ComicViewH holder, int position) {
        holder.titleMarvelT.setText(comicList.get(position).getTitle());
    }

    private void setComicPrice(ComicViewH holder, int position) {
        Price price = comicList.get(position).getPrices().get(0);
        if (price.price > 0)
            holder.priceMarvelT.setText(String.format(Locale.getDefault(), "%s%s", price.price, C.USD));
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence query) {
                FilterResults results = new FilterResults();
                if (query == null || query.length() == 0) {
                    results.count = toFilterComicList.size();
                    results.values = toFilterComicList;
                } else {
                    ArrayList<Comic> filterList = new ArrayList<>();
                    for (Comic comic : toFilterComicList)
                        if (comic.getTitle().toLowerCase().contains(query))
                            filterList.add(comic);
                    results.count = filterList.size();
                    results.values = filterList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                comicList = (ArrayList<Comic>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setOnFavouriteClick(ViewCallback<Comic> onFavouriteClick) {
        this.onFavouriteClick = onFavouriteClick;
    }

    // View Holder pattern
    class ComicViewH extends RecyclerView.ViewHolder {

        private ImageView favouriteImageV;
        private ImageView photoImageV;
        private MarvelTextView titleMarvelT;
        private MarvelTextView priceMarvelT;

        ComicViewH(View itemView) {
            super(itemView);
            titleMarvelT = (MarvelTextView) itemView.findViewById(R.id.title_marvel_text_v);
            priceMarvelT = (MarvelTextView) itemView.findViewById(R.id.price_marvel_text_v);
            photoImageV = (ImageView) itemView.findViewById(R.id.photo_image_v);
            favouriteImageV = (ImageView) itemView.findViewById(R.id.is_favourite_image_v);
            favouriteImageV.setOnClickListener(this::saveAsFavourite);
        }

        private void saveAsFavourite(View view) {
            saveChangesComic(comicList.get(getAdapterPosition()));
            notifyItemChanged(getAdapterPosition());
        }

        void saveChangesComic(Comic comic) {
            Realm realm = Realm.getDefaultInstance();
            try {
                realm.beginTransaction();
                comic.setFavourite(!comic.isFavourite());
                realm.copyToRealmOrUpdate(comic);
                realm.commitTransaction();
            } catch (Exception ex) {
                realm.cancelTransaction();
            } finally {
                realm.close();
            }
        }
    }
}
