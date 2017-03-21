package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import comics._utility.C;
import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.11:48 PM
 * http://rsantillanc.pe.hu/me/
 */

public class Image extends RealmObject implements Parcelable {
    //The directory path of to the image.,
    public String path;
    //The file extension for the image.
    public String extension;

    @Override
    public String toString() {
        return "Image{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }

    public String getCompleteUrl() {
        return path + C.DOT + extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.extension);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.path = in.readString();
        this.extension = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
