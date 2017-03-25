package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 21/03/2017.12:05 AM
 * http://rsantillanc.pe.hu/me/
 */

public class CreatorList extends RealmObject implements Parcelable {

    protected int available;
    protected int returned;
    @SerializedName("collectionURI")
    protected String collectionUri;
    private RealmList<CreatorSummary> items = new RealmList<>();

    public CreatorList() {
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionUri() {
        return collectionUri;
    }

    public void setCollectionUri(String collectionUri) {
        this.collectionUri = collectionUri;
    }

    public RealmList<CreatorSummary> getItems() {
        return items;
    }

    public void setItems(RealmList<CreatorSummary> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.available);
        dest.writeInt(this.returned);
        dest.writeString(this.collectionUri);
        dest.writeTypedList(this.items);
    }

    protected CreatorList(Parcel in) {
        this.available = in.readInt();
        this.returned = in.readInt();
        this.collectionUri = in.readString();
        this.items.addAll(in.createTypedArrayList(CreatorSummary.CREATOR));
    }

    public static final Creator<CreatorList> CREATOR = new Creator<CreatorList>() {
        @Override
        public CreatorList createFromParcel(Parcel source) {
            return new CreatorList(source);
        }

        @Override
        public CreatorList[] newArray(int size) {
            return new CreatorList[size];
        }
    };
}
