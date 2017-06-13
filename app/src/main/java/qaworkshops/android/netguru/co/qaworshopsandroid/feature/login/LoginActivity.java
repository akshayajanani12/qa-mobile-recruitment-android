package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.MainActivity;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration.RegisterActivity;

public class LoginActivity extends MvpActivity<LoginContract.View, LoginContract.Presenter>
        implements LoginContract.View  {

    @BindView(R.id.email)
    EditText mEmailView;

    @BindView(R.id.password)
    EditText mPasswordView;

    private LoginComponent component;

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

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

    @OnClick(R.id.sign_up_button)
    public void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        getPresenter().validateLoginData(email, password);
    }

    @OnClick(R.id.email_register_button)
    public void openRegisterView() {
        RegisterActivity.startActivity(this);
        finish();
    }

    @Override
    public void showPasswordRequired() {
        mEmailView.setError(getString(R.string.error_field_required));
        mEmailView.requestFocus();
    }

    @Override
    public void showEmailRequired() {
        mPasswordView.setError(getString(R.string.error_field_required));
        mPasswordView.requestFocus();
    }

    @Override
    public void signInUser(String email) {
        MainActivity.startActivity(this, email);
        finish();
    }

    @Override
    public void onLoginDataIncorrect() {
        Toast.makeText(this, getString(R.string.login_data_incorrect), Toast.LENGTH_LONG).show();
    }

    private void initComponent() {
        component = App.getAppComponent(this)
                .plusLoginComponent();
        component.inject(this);
    }
}

