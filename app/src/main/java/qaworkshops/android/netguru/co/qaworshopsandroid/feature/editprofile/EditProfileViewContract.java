package qaworkshops.android.netguru.co.qaworshopsandroid.feature.editprofile;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.Date;

public interface EditProfileViewContract {

    interface View extends MvpView {

        void setFirstName(String firstName);

        void setLastName(String lastName);

        void setEmail(String email);

        void setBirthday(String birthday);

        void setBirthdayDate(Date birthday);

        void setCountry(String country);

        void setGender(String gender);

        void closeView();

    }

    interface Presenter extends MvpPresenter<EditProfileViewContract.View> {

        void editUserData(String firstName, String lastName, String email,
                          Date birthday, String country, String gender);

        void loadUserDataFromDb(String email);

    }
}
