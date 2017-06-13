package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import qaworkshops.android.netguru.co.qaworshopsandroid.exceptions.InterfaceNotImplementedException;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = DatePickerFragment.class.getSimpleName();

    public static DatePickerFragment newInstance() {
        return new DatePickerFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        passResultAndCloseFragment(cal.getTime());
    }

    private void passResultAndCloseFragment(Date date) {
        Activity targetActivity = getActivity();
        try {
            DatePickerFragment.DateSetListener listener
                    = (DatePickerFragment.DateSetListener) targetActivity;
            listener.onDateSet(date);
            dismiss();
        } catch (ClassCastException e) {
            throw new InterfaceNotImplementedException(e,
                    targetActivity.getClass().getSimpleName(),
                    DatePickerFragment.DateSetListener.class.getSimpleName());
        }
    }

    @FunctionalInterface
    public interface DateSetListener {

        void onDateSet(Date date);
    }
}