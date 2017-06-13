package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class User extends RealmObject implements Parcelable {

    @PrimaryKey
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long birthday;
    private String country;
    private String gender;
    private RealmList<ListItem> userItemsList;

    public User() {
    }

    public User(String firstName, String lastName, String email,
                long birthday, String country, String gender) {
        this.id =  System.currentTimeMillis();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.country = country;
        this.gender = gender;
        this.userItemsList = new RealmList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RealmList<ListItem> getUserItemsList() {
        return userItemsList;
    }

    public void setUserItemsList(List<ListItem> list) {
        this.userItemsList = new RealmList<>();
        userItemsList.addAll(list);
    }

    public void addItemToUserItemsList(ListItem userItem) {
        if (userItemsList == null) {
            userItemsList = new RealmList<>();
        }
        userItemsList.add(userItem);
    }

    public void removeItemFromUserItemsList(ListItem userItem) {
        if (userItemsList != null) {
            for (int i = 0; i < userItemsList.size(); i++) {
                if (userItemsList.get(i).getId() == userItem.getId()) {
                    userItemsList.remove(i);
                    break;
                }
            }
        }
    }

    public void addItemsToList(List<ListItem> list) {
        if (userItemsList == null) {
            userItemsList = new RealmList<>();
        }
        userItemsList.addAll(list);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeLong(this.birthday);
        dest.writeString(this.country);
        dest.writeString(this.gender);
        dest.writeTypedList(this.userItemsList);
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.birthday = in.readLong();
        this.country = in.readString();
        this.gender = in.readString();
        this.userItemsList = new RealmList<>();
        userItemsList.addAll(in.createTypedArrayList(ListItem.CREATOR));
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
