package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

interface LoginContract {

    interface View extends MvpView {

        void showEmailRequired();

        void showPasswordRequired();

        void signInUser(String email);

        void onLoginDataIncorrect();

    }

    interface Presenter extends MvpPresenter<View> {

        void validateLoginData(String email, String password);

    }
}
