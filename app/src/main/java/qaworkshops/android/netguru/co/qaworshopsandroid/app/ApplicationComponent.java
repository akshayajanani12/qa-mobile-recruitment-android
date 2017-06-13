package qaworkshops.android.netguru.co.qaworshopsandroid.app;

import javax.inject.Singleton;

import dagger.Component;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.editprofile.EditProfileViewComponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.login.LoginComponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.MainViewComponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist.AddToListComponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration.RegisterViewComponent;

@Singleton
@Component(
        modules = {
                ApplicationModule.class
        })
public interface ApplicationComponent {

    LoginComponent plusLoginComponent();

    AddToListComponent plusAddToListComponent();

    MainViewComponent plusMainViewComponent();

    RegisterViewComponent plusRegisterViewComponent();

    EditProfileViewComponent plusEditProfileViewComponent();
}
