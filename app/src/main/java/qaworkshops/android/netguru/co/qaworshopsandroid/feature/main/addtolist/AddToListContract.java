package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public interface AddToListContract {

    interface View extends MvpView {

        void passResultAndCloseFragment(ListItem listItem);

        void showItemNameRequired();
    }

    interface Presenter extends MvpPresenter<AddToListContract.View> {

        void addToUserList(ListItem listItem);

        void validateItemName(String itemName);
    }
}
