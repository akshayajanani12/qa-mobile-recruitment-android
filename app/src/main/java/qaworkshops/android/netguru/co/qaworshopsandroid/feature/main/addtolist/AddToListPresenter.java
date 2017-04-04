package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist;

import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.app.FragmentScope;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

@FragmentScope
public class AddToListPresenter extends MvpNullObjectBasePresenter<AddToListContract.View>
        implements AddToListContract.Presenter {


    @Inject
    public AddToListPresenter() {
    }

    @Override
    public void addToUserList(ListItem listItem) {
        //TODO 7.03 Add to database
    }

    @Override
    public void validateItemName(String itemName) {
        if (TextUtils.isEmpty(itemName)) {
            getView().showItemNameRequired();
        } else {
            getView().passResultAndCloseFragment(new ListItem(itemName));
        }
    }
}
