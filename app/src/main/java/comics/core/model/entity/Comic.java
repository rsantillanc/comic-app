package comics.core.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import comics._utility.MapperUtility;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.11:50 PM
 * http://rsantillanc.pe.hu/me/
 */

public class Comic extends RealmObject implements Parcelable {

    public static final String IS_FAVOURITE = "isFavourite";

    @PrimaryKey
    private Integer id;
    private String title;
    private String description;
    private RealmList<Price> prices;
    private long date;
    private RealmList<Image> images;
    private Image thumbnail;
    private boolean isFavourite = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Image> getImages() {
        return images;
    }


    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", prices=" + prices.size() +
                ", date=" + date +
                ", images=" + images.size() +
                ", thumbnail=" + thumbnail.getCompleteUrl() +
                ", isFavourite=" + isFavourite +
                '}';
    }

    public List<Price> getPrices() {
        return prices;
    }

    public float getTotalPrice() {
        float total = 0;
        for (Price price : getPrices())
            total = total + price.price;
        return total;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public void beginTransaction() {
        Realm.getDefaultInstance().beginTransaction();
    }

    public void commitTransaction() {
        Realm.getDefaultInstance().commitTransaction();
    }

    public void setPrices(RealmList<Price> prices) {
        this.prices = prices;
    }

    public void setImages(RealmList<Image> images) {
        this.images = images;
    }


    public void autoSave() {
        save(Realm.getDefaultInstance());
    }

    private void save(Realm realm) {
        realm.executeTransaction(realm1 -> realm1.createOrUpdateObjectFromJson(Comic.class, MapperUtility.transformModelToJson(Comic.this)));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeList(this.prices);
        dest.writeLong(this.date);
        dest.writeList(this.images);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeByte(this.isFavourite ? (byte) 1 : (byte) 0);
    }

    public Comic() {
    }

    protected Comic(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.prices = new RealmList<>();
        in.readList(this.prices, Price.class.getClassLoader());
        this.date = in.readLong();
        this.images = new RealmList<>();
        in.readList(this.images, Image.class.getClassLoader());
        this.thumbnail = in.readParcelable(Image.class.getClassLoader());
        this.isFavourite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel source) {
            return new Comic(source);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };
}
