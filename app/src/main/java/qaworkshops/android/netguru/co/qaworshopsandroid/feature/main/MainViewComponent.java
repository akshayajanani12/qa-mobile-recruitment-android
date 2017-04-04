package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import dagger.Subcomponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.ActivityScope;

@ActivityScope
@Subcomponent
public interface MainViewComponent {

    void inject(MainActivity activity);

    MainViewPresenter getMainViewPresenter();

}

