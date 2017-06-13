package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.UserProviderSource;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.shared.Statics;

public class MainViewPresenter extends MvpNullObjectBasePresenter<MainViewContract.View>
        implements MainViewContract.Presenter {


    private final UserProviderSource userProviderSource;
    private String email;
    private List<ListItem> itemList;

    @Inject
    public MainViewPresenter(UserProviderSource userProviderSource) {
        this.userProviderSource = userProviderSource;
        itemList = new ArrayList<>();
    }

    @Override
    public void onAddItemToListAdded(String listItemName) {
        ListItem listItem = new ListItem(listItemName, getUserId(email));
        userProviderSource.addItemToUserItemList(email, listItem);
        getView().updateList(userProviderSource.getUserItemListFromDb(email));
    }

    @Override
    public void onRemoveListItem(ListItem listItem) {
        userProviderSource.removeItemFromUserItemList(email, listItem.getId());
        getView().updateList(userProviderSource.getUserItemListFromDb(email));
    }

    @Override
    public void onActivityStarted(String email) {
        if (email != null) {
            loadUserData(email);
        } else {
            checkIfUserWasLoggedIn();
        }
    }

    @Override
    public void onLogoutAction() {
        Hawk.delete(Statics.EMAIL_KEY);
        getView().showLoginView();
    }

    @Override
    public void onShowEditProfileViewClicked() {
        getView().showEditProfileView(email);
    }

    private void checkIfUserWasLoggedIn() {
        if (Hawk.get(Statics.EMAIL_KEY) != null) {
            loadUserData(Hawk.get(Statics.EMAIL_KEY));
        } else {
            getView().showLoginView();
        }
    }

    private void loadUserData(String email) {
        this.email = email;
        itemList = new ArrayList<>();
        itemList.addAll(userProviderSource.getUserItemListFromDb(email));
        getView().onUserItemListLoaded(itemList);
    }

    private long getUserId(String email) {
        return userProviderSource.getUserId(email);
    }

}
