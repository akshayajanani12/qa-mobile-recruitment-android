package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.exceptions.InterfaceNotImplementedException;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.shared.BaseMvpDialogFragment;

public class AddToListDialogFragment extends BaseMvpDialogFragment<AddToListContract.View,
        AddToListContract.Presenter> implements AddToListContract.View {

    public static final String TAG = AddToListDialogFragment.class.getSimpleName();

    @BindView(R.id.name_text_input_layout)
    TextInputLayout nameTextInputLayout;

    private Button createButton;
    private Button cancelButton;

    public static AddToListDialogFragment newInstance() {
        return new AddToListDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View dialogContentView = View.inflate(getContext(), R.layout.fragment_add_item, null);
        super.onViewCreated(dialogContentView, savedInstanceState);
        Dialog dialog = new AlertDialog.Builder(getContext(), R.style.NoTitleDialog)
                .setTitle(R.string.dialog_fragment_add_new_item_title)
                .setPositiveButton(R.string.action_create, null)
                .setNegativeButton(R.string.action_cancel, null)
                .setView(dialogContentView)
                .create();

        setupDialogButtons(dialog);
        return dialog;
    }


    @NonNull
    @Override
    public AddToListContract.Presenter createPresenter() {
        return App.getAppComponent(getContext()).plusAddToListComponent().getPresenter();
    }
    @Override
    public void passResultAndCloseFragment(String listItemName) {
        Activity targetActivity = getActivity();
        try {
            AddToListDialogFragment.ItemAddedListener listener
                    = (AddToListDialogFragment.ItemAddedListener) targetActivity;
            listener.onItemAdded(listItemName);
            dismiss();
        } catch (ClassCastException e) {
            throw new InterfaceNotImplementedException(e,
                    targetActivity.getClass().getSimpleName(),
                    AddToListDialogFragment.ItemAddedListener.class.getSimpleName());
        }
    }

    @Override
    public void showItemNameRequired() {
        nameTextInputLayout.setError(getString(R.string.error_field_required));
        nameTextInputLayout.requestFocus();
    }

    private void setupDialogButtons(Dialog dialog) {
        dialog.setOnShowListener(shownDialog -> {
            createButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
            cancelButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
            createButton.setOnClickListener(v -> {
                getPresenter().validateItemName(nameTextInputLayout.getEditText().getText().toString());
            });
            cancelButton.setOnClickListener(v -> {
                dismiss();
            });
        });
    }

    @FunctionalInterface
    public interface ItemAddedListener {

        void onItemAdded(String listItemName);
    }
}
