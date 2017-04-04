package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.MainActivity;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;

public class LoginActivity extends MvpActivity<LoginContract.View, LoginContract.Presenter>
        implements LoginContract.View  {

    @BindView(R.id.email)
    EditText mEmailView;

    @BindView(R.id.password)
    EditText mPasswordView;

    private LoginComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter() {
        return component.getLoginPresenter();
    }

    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        getPresenter().validateLoginData(email, password);
    }

    @Override
    public void showInvalidEmailError() {
        mEmailView.setError(getString(R.string.error_invalid_email));
        mEmailView.requestFocus();
    }

    @Override
    public void showPasswordToShortError() {
        mPasswordView.setError(getString(R.string.error_invalid_password));
        mPasswordView.requestFocus();
    }

    @Override
    public void signInUser(String email) {
        MainActivity.startActivity(this, email);
    }

    private void initComponent() {
        component = App.getAppComponent(this)
                .plusLoginComponent();
        component.inject(this);
    }
}

