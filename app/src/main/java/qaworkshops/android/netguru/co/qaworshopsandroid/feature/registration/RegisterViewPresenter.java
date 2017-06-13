package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.Date;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.UserProviderSource;

public class RegisterViewPresenter extends MvpNullObjectBasePresenter<RegisterViewContract.View>
        implements RegisterViewContract.Presenter {

    private static final String EMPTY_STRING = " ";
    private final UserProviderSource userProviderSource;
    private String lastName;
    private String password;
    private String email;
    private String country;
    private String gender;
    private Date birthday;

    @Inject
    public RegisterViewPresenter(UserProviderSource userProviderSource) {
        this.userProviderSource = userProviderSource;
    }

    @Override
    public void checkFieldsCorrectness(String lastName, String password,
                                       String email, String country,
                                       String gender, Date birthday) {
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;

        checkCorrectness();
    }

    private void checkCorrectness() {
        if (TextUtils.isEmpty(lastName)) {
            getView().onEmptyLastNameError();
        } else if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getView().onIncorrectEmailError();
        } else if (password.length() < 5) {
            getView().onPasswordToShortError();
        } else if (birthday == null) {
            getView().onBirthdayRequiredError();
        } else {
            registerUserAndOpenMainView();
        }
    }

    private void registerUserAndOpenMainView() {
        userProviderSource.createUser(
                new User(EMPTY_STRING, lastName, email, birthday.getTime(), country, gender)
        );
        getView().openMainView(email);
    }
}
