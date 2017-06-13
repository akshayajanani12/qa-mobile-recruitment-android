package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.Date;

public interface RegisterViewContract {

    interface View extends MvpView {

        void onEmptyLastNameError();

        void onPasswordToShortError();

        void onIncorrectEmailError();

        void onBirthdayRequiredError();

        void openMainView(String email);

    }

    interface Presenter extends MvpPresenter<RegisterViewContract.View> {

        void checkFieldsCorrectness(String lastName, String password,
                                    String email, String country, String gender,
                                    Date date);
    }
}
