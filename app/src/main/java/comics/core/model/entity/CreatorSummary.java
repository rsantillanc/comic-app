package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 20/03/2017.11:08 PM
 * http://rsantillanc.pe.hu/me/
 */

public class CreatorSummary extends RealmObject implements Parcelable {

    @SerializedName("resourceURI")
    private String resourceUri;
    private String name;
    private String role;

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

    public String getRole() {
        return role != null ? role : "\r";
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceUri);
        dest.writeString(this.name);
        dest.writeString(this.role);
    }

    public CreatorSummary() {
    }

    protected CreatorSummary(Parcel in) {
        this.resourceUri = in.readString();
        this.name = in.readString();
        this.role = in.readString();
    }

    public static final Parcelable.Creator<CreatorSummary> CREATOR = new Parcelable.Creator<CreatorSummary>() {
        @Override
        public CreatorSummary createFromParcel(Parcel source) {
            return new CreatorSummary(source);
        }

        @Override
        public CreatorSummary[] newArray(int size) {
            return new CreatorSummary[size];
        }
    };
}
