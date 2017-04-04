package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.adapter.MainListAdapter;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist.AddToListDialogFragment;

public class MainActivity extends MvpActivity<MainViewContract.View, MainViewContract.Presenter>
        implements MainViewContract.View, AddToListDialogFragment.ItemAddedListener {

    public static final String EMAIL_KEY = "email_key";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MainListAdapter mainListAdapter;
    private MainViewComponent component;

    public static void startActivity(Context context, String email) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EMAIL_KEY, email);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        setupRecyclerView();
        setSupportActionBar(toolbar);
    }

    @NonNull
    @Override
    public MainViewContract.Presenter createPresenter() {
        return component.getMainViewPresenter();
    }

    @Override
    public void onItemAdded(ListItem listItem) {
        getPresenter().onAddItemToListAdded(listItem);

    }

    @Override
    public void addItemToList(ListItem listItem) {
        mainListAdapter.addNewItem(listItem);
    }

    @OnClick(R.id.fab)
    public void showDialogFragment() {
        AddToListDialogFragment
                .newInstance()
                .show(getSupportFragmentManager(), AddToListDialogFragment.TAG);
    }

    private void setupRecyclerView() {
        mainListAdapter = new MainListAdapter();
        recyclerView.setAdapter(mainListAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initComponent() {
        component = App.getAppComponent(this)
                .plusMainViewComponent();
        component.inject(this);
    }
}
