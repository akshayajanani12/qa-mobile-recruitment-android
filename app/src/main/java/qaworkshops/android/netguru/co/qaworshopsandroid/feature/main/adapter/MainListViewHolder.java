package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.BaseViewHolder;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.RemoveItemListener;

public class MainListViewHolder extends BaseViewHolder<ListItem> {

    @BindView(R.id.swipe_layout)
    SwipeLayout swipeLayout;

    @BindView(R.id.bottom_wrapper)
    LinearLayout bottomWrapper;

    @BindView(R.id.item_title_text_view)
    TextView itemTitleTextView;

    private final RemoveItemListener removeItemListener;
    private ListItem listItem;
    private ViewGroup view;

    MainListViewHolder(ViewGroup parent, RemoveItemListener removeItemListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false));
        this.view = parent;
        this.removeItemListener = removeItemListener;
    }

    @Override
    public void bind(ListItem item) {
        this.listItem = item;
        setItemTitle();
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
    }

    @OnClick(R.id.image_view)
    public void onImageClick() {
        removeItemListener.onListItemRemove(listItem);
    }

    private void setItemTitle() {
        itemTitleTextView.setText(listItem.getTitle());
    }
}
