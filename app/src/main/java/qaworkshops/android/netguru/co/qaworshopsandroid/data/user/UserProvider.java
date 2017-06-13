package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class UserProvider implements UserProviderSource {

    @Inject
    public UserProvider() {
    }

    @Override
    public void createUser(User user) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(db -> db.copyToRealm(user));
        }
    }

    @Override
    public boolean doesUserExistInDb(String email) {
        boolean exists = false;
        try (Realm realm = Realm.getDefaultInstance()) {
            exists = realm.where(User.class)
                    .equalTo("email", email)
                    .findFirst() != null;
        }

        return exists;
    }

    @Override
    public void addItemToUserItemList(String email, ListItem listItem) {
        User user = getUserFromDb(email);
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(db -> user.addItemToUserItemsList(listItem));
        }
    }

    @Override
    public void removeItemFromUserItemList(String email, long listItemId) {
        User user = getUserFromDb(email);
        RealmResults<ListItem> results = getUserItemList(user.getId());
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(db -> {
                for (int i = 0; i < results.size(); i++) {
                    if (results.get(i).getId() == listItemId) {
                        results.get(i).deleteFromRealm();
                        user.setUserItemsList(results.subList(0, results.size()));
                    }
                }
            });
        }
    }

    @Override
    public List<ListItem> getUserItemListFromDb(String email) {
        return getUserFromDb(email).getUserItemsList();
    }

    @Override
    public void updateUser(User user) {
        User userFromDb = getUserFromDb(user.getEmail());
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(db -> {
                user.setId(userFromDb.getId());
                user.addItemsToList(userFromDb.getUserItemsList());
                db.copyToRealmOrUpdate(user);
            });
        }
    }

    @Override
    public User getUserFromDb(String email) {
        User user;
        try (Realm realm = Realm.getDefaultInstance()) {
            user = realm.where(User.class)
                    .equalTo("email", email)
                    .findFirst();
        }

        return user;
    }

    @Override
    public long getUserId(String email) {
        return getUserFromDb(email).getId();
    }

    private RealmResults<ListItem> getUserItemList(long userId) {
        RealmResults<ListItem> results;
        try (Realm realm = Realm.getDefaultInstance()) {
            results = realm.where(ListItem.class).equalTo("userId", userId).findAll();
        }
        return results;
    }
}
