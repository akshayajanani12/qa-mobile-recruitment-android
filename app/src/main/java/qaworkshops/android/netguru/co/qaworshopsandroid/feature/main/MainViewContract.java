package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;


public interface MainViewContract {

    interface View extends MvpView {

        void updateList(List<ListItem> list);

        void onUserItemListLoaded(List<ListItem> list);

        void showLoginView();

        void showEditProfileView(String email);

    }

    interface Presenter extends MvpPresenter<MainViewContract.View> {

        void onAddItemToListAdded(String listItemName);

        void onRemoveListItem(ListItem listItem);

        void onActivityStarted(String email);

        void onLogoutAction();

        void onShowEditProfileViewClicked();

    }
}
