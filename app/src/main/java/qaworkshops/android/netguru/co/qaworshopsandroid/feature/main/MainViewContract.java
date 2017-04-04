package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;


public interface MainViewContract {

    interface View extends MvpView {

        void addItemToList(ListItem listItem);

    }

    interface Presenter extends MvpPresenter<MainViewContract.View> {

        void onAddItemToListAdded(ListItem listItem);

    }
}
