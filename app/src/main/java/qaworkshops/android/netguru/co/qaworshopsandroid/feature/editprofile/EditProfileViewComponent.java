package qaworkshops.android.netguru.co.qaworshopsandroid.feature.editprofile;

import dagger.Subcomponent;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.ActivityScope;

@ActivityScope
@Subcomponent
public interface EditProfileViewComponent {

    void inject(EditProfileActivity activity);

    EditProfileViewPresenter getEditProfileViewPresenter();

}
