package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.02:26 PM
 * http://rsantillanc.pe.hu/me/
 */

public class Price extends RealmObject implements Parcelable {
    public String type;//(string, optional): A description of the price (e.g. print price, digital price).,
    public float price;// (float, optional):


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeFloat(this.price);
    }

    public Price() {
    }

    protected Price(Parcel in) {
        this.type = in.readString();
        this.price = in.readFloat();
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
