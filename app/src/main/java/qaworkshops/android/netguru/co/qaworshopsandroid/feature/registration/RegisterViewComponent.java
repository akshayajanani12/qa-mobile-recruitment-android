package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import dagger.Subcomponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.ActivityScope;

@ActivityScope
@Subcomponent
public interface RegisterViewComponent {

    void inject(RegisterActivity activity);

    RegisterViewPresenter getRegisterViewPresenter();

}
