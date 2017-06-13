package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;


import java.util.List;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public interface UserProviderSource {

    void createUser(User user);

    boolean doesUserExistInDb(String email);

    void addItemToUserItemList(String email, ListItem listItem);

    void removeItemFromUserItemList(String email, long listItemId);

    List<ListItem> getUserItemListFromDb(String email);

    void updateUser(User user);

    User getUserFromDb(String email);

    long getUserId(String email);
}
