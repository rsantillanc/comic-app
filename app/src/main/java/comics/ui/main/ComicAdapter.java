package comics.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import comics.core.model.entity.Comic;
import comics.ui.custom.loader.ImageLoader;
import comics.ui.custom.widget.MarvelTextView;
import pe.nextdots.comics.R;

/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.09:47 AM
 * http://rsantillanc.pe.hu/me/
 */

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewH> {

    private final ArrayList<Comic> comicList = new ArrayList<>();
    private final ImageLoader imageLoader;

    public ComicAdapter(ArrayList<Comic> _comicList, ImageLoader imageLoader) {
        this.comicList.addAll(_comicList);
        this.imageLoader = imageLoader;
    }

    @Override
    public ComicViewH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ComicViewH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false));
    }

    @Override
    public void onBindViewHolder(ComicViewH holder, int position) {
        holder.titleMarvelT.setText(comicList.get(position).getTitle());
        imageLoader.load(comicList.get(position).getThumbnail().getCompleteUrl(), holder.photoImageV);
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    static class ComicViewH extends RecyclerView.ViewHolder {

        private ImageView photoImageV;
        private MarvelTextView titleMarvelT;

        ComicViewH(View itemView) {
            super(itemView);
            titleMarvelT = (MarvelTextView) itemView.findViewById(R.id.title_marvel_text_v);
            photoImageV = (ImageView) itemView.findViewById(R.id.photo_image_v);
        }
    }
}
