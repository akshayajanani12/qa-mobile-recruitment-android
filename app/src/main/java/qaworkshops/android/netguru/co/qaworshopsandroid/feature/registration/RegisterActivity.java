package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.login.LoginActivity;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.MainActivity;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist.AddToListDialogFragment;

import static qaworkshops.android.netguru.co.qaworshopsandroid.R.id.email;

public class RegisterActivity extends MvpActivity<RegisterViewContract.View, RegisterViewContract.Presenter>
        implements RegisterViewContract.View, DatePickerFragment.DateSetListener {

    @BindView(R.id.first_name)
    TextInputEditText firstNameInputEditText;

    @BindView(R.id.last_name)
    TextInputEditText lastNameInputEditText;

    @BindView(email)
    TextInputEditText emailInputEditText;

    @BindView(R.id.password)
    TextInputEditText passwordInputEditText;

    @BindView(R.id.select_country_spinner)
    Spinner countrySpinner;

    @BindView(R.id.sign_up_button)
    Button signUpButton;

    @BindView(R.id.sign_in_button)
    Button signInButton;

    @BindView(R.id.birthday_text_view)
    TextView birthdayTextView;

    private RegisterViewComponent component;
    private String country;
    private String gender;
    private Date birthday;

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setupSpinner();

        signUpButton.setVisibility(View.VISIBLE);
        signInButton.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public RegisterViewContract.Presenter createPresenter() {
        return component.getRegisterViewPresenter();
    }

    @OnClick(R.id.set_birthday_button)
    public void openDatePicker() {
        DatePickerFragment
                .newInstance()
                .show(getFragmentManager(), AddToListDialogFragment.TAG);
    }

    @OnClick(R.id.sign_up_button)
    public void attemptRegister() {
        getPresenter().checkFieldsCorrectness(
                lastNameInputEditText.getEditableText().toString(),
                passwordInputEditText.getEditableText().toString(),
                emailInputEditText.getEditableText().toString(),
                country,
                gender,
                birthday
        );
    }

    @OnClick(R.id.sign_in_button)
    public void showLoginView() {
        LoginActivity.startActivity(this);
        finish();
    }

    @OnItemSelected(R.id.select_country_spinner)
    public void spinnerItemSelected(Spinner spinner, int position) {
        this.country = spinner.getItemAtPosition(position).toString();
    }

    @OnClick({R.id.male_radio_button, R.id.female_radio_button})
    public void onRadioButtonClicked(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();
        if (checked) {
            gender = radioButton.getText().toString();
        }
    }

    @Override
    public void onEmptyLastNameError() {
        lastNameInputEditText.setError(getString(R.string.error_field_required));
        lastNameInputEditText.requestFocus();
    }

    @Override
    public void onPasswordToShortError() {
        passwordInputEditText.setError(getString(R.string.error_invalid_password));
        passwordInputEditText.requestFocus();
    }

    @Override
    public void onIncorrectEmailError() {
        emailInputEditText.setError(getString(R.string.error_invalid_email));
        emailInputEditText.requestFocus();
    }

    @Override
    public void onBirthdayRequiredError() {
        Toast.makeText(this, getString(R.string.set_birthday_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void openMainView(String email) {
        MainActivity.startActivity(this, email);
        finish();
    }

    @Override
    public void onDateSet(Date date) {
        this.birthday = date;
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        birthdayTextView.setText(String.format("%s: %s", getString(R.string.your_birthday), dateFormat.format(date)));
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
    }

    private void initComponent() {
        component = App.getAppComponent(this)
                .plusRegisterViewComponent();
        component.inject(this);
    }
}
