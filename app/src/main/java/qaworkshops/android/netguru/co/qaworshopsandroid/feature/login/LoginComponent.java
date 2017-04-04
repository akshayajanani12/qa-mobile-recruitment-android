package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import dagger.Subcomponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.ActivityScope;

@ActivityScope
@Subcomponent
public interface LoginComponent {

    void inject(LoginActivity activity);

    LoginPresenter getLoginPresenter();

}
