package comics.core.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
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
    @SerializedName("modified")
    private Date date;
    private int pageCount;
    private Image thumbnail;
    private CreatorList creators;
    private CharacterList characters;
    private SeriesSummary series;
    private RealmList<Price> prices = new RealmList<>();
    private RealmList<Image> images = new RealmList<>();
    //Custom
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

    public Comic() {
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SeriesSummary getSeries() {
        return series;
    }

    public CreatorList getCreators() {
        return creators;
    }

    public void setCreators(CreatorList creators) {
        this.creators = creators;
    }

    public CharacterList getCharacters() {
        return characters;
    }

    public void setCharacters(CharacterList characters) {
        this.characters = characters;
    }

    public void setSeries(SeriesSummary series) {
        this.series = series;
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
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeInt(this.pageCount);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeParcelable(this.creators, flags);
        dest.writeParcelable(this.characters, flags);
        dest.writeParcelable(this.series, flags);
        dest.writeTypedList(this.prices);
        dest.writeTypedList(this.images);
        dest.writeByte(this.isFavourite ? (byte) 1 : (byte) 0);
    }

    protected Comic(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.pageCount = in.readInt();
        this.thumbnail = in.readParcelable(Image.class.getClassLoader());
        this.creators = in.readParcelable(CreatorList.class.getClassLoader());
        this.characters = in.readParcelable(CharacterList.class.getClassLoader());
        this.series = in.readParcelable(SeriesSummary.class.getClassLoader());
        this.prices.addAll(in.createTypedArrayList(Price.CREATOR));
        this.images.addAll(in.createTypedArrayList(Image.CREATOR));
        this.isFavourite = in.readByte() != 0;
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
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
