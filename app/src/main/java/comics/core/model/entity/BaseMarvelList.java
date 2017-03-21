package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 21/03/2017.12:06 AM
 * http://rsantillanc.pe.hu/me/
 */

public class BaseMarvelList extends RealmObject implements Parcelable {

    protected int available;
    protected int returned;
    @SerializedName("collectionURI")
    protected String collectionUri;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.available);
        dest.writeInt(this.returned);
        dest.writeString(this.collectionUri);
    }

    public BaseMarvelList() {
    }

    protected BaseMarvelList(Parcel in) {
        this.available = in.readInt();
        this.returned = in.readInt();
        this.collectionUri = in.readString();
    }

    public static final Parcelable.Creator<BaseMarvelList> CREATOR = new Parcelable.Creator<BaseMarvelList>() {
        @Override
        public BaseMarvelList createFromParcel(Parcel source) {
            return new BaseMarvelList(source);
        }

        @Override
        public BaseMarvelList[] newArray(int size) {
            return new BaseMarvelList[size];
        }
    };
}
