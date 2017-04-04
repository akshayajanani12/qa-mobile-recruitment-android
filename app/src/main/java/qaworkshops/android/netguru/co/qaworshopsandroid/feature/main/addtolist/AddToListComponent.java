package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist;

import dagger.Subcomponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.FragmentScope;

@FragmentScope
@Subcomponent
public interface AddToListComponent {

    void inject(AddToListDialogFragment addToListDialogFragment);

    AddToListPresenter getPresenter();
}
