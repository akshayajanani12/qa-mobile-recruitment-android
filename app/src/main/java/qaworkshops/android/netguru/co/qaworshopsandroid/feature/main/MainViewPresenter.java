package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class MainViewPresenter extends MvpNullObjectBasePresenter<MainViewContract.View>
        implements MainViewContract.Presenter {

    private ListItem listItem;

    @Inject
    public MainViewPresenter() {
    }

    @Override
    public void onAddItemToListAdded(ListItem listItem) {
        this.listItem = listItem;
        getView().addItemToList(listItem);
    }
}
