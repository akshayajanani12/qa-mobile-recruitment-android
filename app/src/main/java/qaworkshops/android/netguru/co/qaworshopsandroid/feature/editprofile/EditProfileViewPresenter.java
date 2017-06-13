package qaworkshops.android.netguru.co.qaworshopsandroid.feature.editprofile;

import android.content.Context;
import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.orhanobut.hawk.Hawk;

import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.UserProviderSource;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.shared.Statics;

public class EditProfileViewPresenter extends MvpNullObjectBasePresenter<EditProfileViewContract.View>
        implements EditProfileViewContract.Presenter {

    private final Context context;
    private final UserProviderSource userProviderSource;
    private User user;
    private String email;

    @Inject
    public EditProfileViewPresenter(Context context, UserProviderSource userProviderSource) {
        this.context = context;
        this.userProviderSource = userProviderSource;
    }

    @Override
    public void editUserData(String firstName, String lastName, String email,
                             Date birthday, String country, String gender) {
        userProviderSource.updateUser(new User(
                firstName, lastName, email, birthday.getTime(), country, gender
        ));
        getView().closeView();
    }

    @Override
    public void loadUserDataFromDb(String email) {
        if (TextUtils.isEmpty(email)) {
            email = Hawk.get(Statics.EMAIL_KEY);
        } else {
            this.email = email;
        }

        this.user = userProviderSource.getUserFromDb(email);
        setUserData();
    }

    private void setUserData() {
        getView().setFirstName(user.getFirstName());
        getView().setLastName(user.getLastName());
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        getView().setBirthday(dateFormat.format(user.getBirthday()));
        getView().setBirthdayDate(new Date(user.getBirthday()));
        getView().setEmail(user.getEmail());
        getView().setGender(user.getGender());
        getView().setCountry(user.getCountry());
    }
}
