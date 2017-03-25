package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 20/03/2017.11:08 PM
 * http://rsantillanc.pe.hu/me/
 */

public class SeriesSummary extends RealmObject implements Parcelable {

    @SerializedName("resourceURI")
    private String resourceUri;
    private String name;

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceUri);
        dest.writeString(this.name);
    }

    public SeriesSummary() {
    }

    protected SeriesSummary(Parcel in) {
        this.resourceUri = in.readString();
        this.name = in.readString();
    }

    public static final Creator<SeriesSummary> CREATOR = new Creator<SeriesSummary>() {
        @Override
        public SeriesSummary createFromParcel(Parcel source) {
            return new SeriesSummary(source);
        }

        @Override
        public SeriesSummary[] newArray(int size) {
            return new SeriesSummary[size];
        }
    };
}
