package qaworkshops.android.netguru.co.qaworshopsandroid.app;

import javax.inject.Singleton;

import dagger.Component;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.login.LoginComponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.MainViewComponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist.AddToListComponent;

@Singleton
@Component(
        modules = {
                ApplicationModule.class
        })
public interface ApplicationComponent {

    LoginComponent plusLoginComponent();

    AddToListComponent plusAddToListComponent();

    MainViewComponent plusMainViewComponent();
}
