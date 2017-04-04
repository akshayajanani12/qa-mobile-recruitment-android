package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.app.ActivityScope;

@ActivityScope
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    private String email;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void validateLoginData(String email, String password) {
        this.email = email;
        if (!isEmailValid(email)) {
            getView().showInvalidEmailError();
        } else if (!isPasswordValid(password)) {
            getView().showPasswordToShortError();
        } else {
            signInUSer();
        }
    }

    private void signInUSer() {
        getView().signInUser(email);
    }

    private boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() > 6;
    }
}
