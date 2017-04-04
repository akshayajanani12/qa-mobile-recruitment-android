package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

interface LoginContract {

    interface View extends MvpView {

        void showInvalidEmailError();

        void showPasswordToShortError();

        void signInUser(String email);

    }

    interface Presenter extends MvpPresenter<View> {

        void validateLoginData(String email, String password);

    }
}
