package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.app.ActivityScope;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.UserProviderSource;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.shared.Statics;

@ActivityScope
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    private String email;
    private final UserProviderSource userProviderSource;

    @Inject
    public LoginPresenter(UserProviderSource userProviderSource) {
        this.userProviderSource = userProviderSource;
    }

    @Override
    public void validateLoginData(String email, String password) {
        this.email = email;
        if (TextUtils.isEmpty(email)) {
            getView().showEmailRequired();
        } else if (TextUtils.isEmpty(password)) {
            getView().showPasswordRequired();
        } else {
            signInUSer();
        }
    }

    private void signInUSer() {
        if (userProviderSource.doesUserExistInDb(email)) {
            Hawk.put(Statics.EMAIL_KEY, email);
            getView().signInUser(email);
        } else {
            getView().onLoginDataIncorrect();
        }
    }
}
