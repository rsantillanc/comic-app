package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;

/**
 * Created by Renzo D. Santillán Ch. on 21/03/2017.12:05 AM
 * http://rsantillanc.pe.hu/me/
 */

public class CreatorList extends BaseMarvelList implements Parcelable {

    private RealmList<CreatorSummary> items = new RealmList<>();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.items);
    }

    public CreatorList() {
    }

    protected CreatorList(Parcel in) {
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
