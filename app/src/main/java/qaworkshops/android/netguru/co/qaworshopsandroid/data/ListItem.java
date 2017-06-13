package qaworkshops.android.netguru.co.qaworshopsandroid.data;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListItem extends RealmObject implements Parcelable {

    @PrimaryKey
    private long id;
    private String title;
    private long userId;

    public ListItem() {
    }

    public ListItem(String title, long userId) {
        this.title = title;
        this.id = System.currentTimeMillis();
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeLong(this.userId);
    }

    protected ListItem(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.userId = in.readLong();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel source) {
            return new ListItem(source);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };
}
