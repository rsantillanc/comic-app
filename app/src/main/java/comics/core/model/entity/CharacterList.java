package comics.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;

/**
 * Created by Renzo D. Santillán Ch. on 20/03/2017.11:23 PM
 * http://rsantillanc.pe.hu/me/
 */

public class CharacterList extends BaseMarvelList implements Parcelable {

    private RealmList<CharacterSummary> items = new RealmList<>();


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

    public CharacterList() {
    }

    protected CharacterList(Parcel in) {
        this.available = in.readInt();
        this.returned = in.readInt();
        this.collectionUri = in.readString();
        this.items.addAll(in.createTypedArrayList(CharacterSummary.CREATOR));
    }

    public static final Parcelable.Creator<CharacterList> CREATOR = new Parcelable.Creator<CharacterList>() {
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
