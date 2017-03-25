package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 20/03/2017.11:23 PM
 * http://rsantillanc.pe.hu/me/
 */

public class CharacterList extends RealmObject implements Parcelable {

    protected int available;
    protected int returned;
    @SerializedName("collectionURI")
    protected String collectionUri;

    private RealmList<CharacterSummary> items = new RealmList<>();


    public CharacterList() {
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

    public RealmList<CharacterSummary> getItems() {
        return items;
    }

    public void setItems(RealmList<CharacterSummary> items) {
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

    protected CharacterList(Parcel in) {
        this.available = in.readInt();
        this.returned = in.readInt();
        this.collectionUri = in.readString();
        this.items.addAll(in.createTypedArrayList(CharacterSummary.CREATOR));
    }

    public static final Creator<CharacterList> CREATOR = new Creator<CharacterList>() {
        @Override
        public CharacterList createFromParcel(Parcel source) {
            return new CharacterList(source);
        }

        @Override
        public CharacterList[] newArray(int size) {
            return new CharacterList[size];
        }
    };
}
