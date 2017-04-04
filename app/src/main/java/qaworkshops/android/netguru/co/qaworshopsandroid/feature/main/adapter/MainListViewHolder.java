package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.BaseViewHolder;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class MainListViewHolder extends BaseViewHolder<ListItem> {

    @BindView(R.id.swipe_layout)
    SwipeLayout swipeLayout;

    @BindView(R.id.bottom_wrapper)
    LinearLayout bottomWrapper;

    @BindView(R.id.item_title_text_view)
    TextView itemTitleTextView;

    private ListItem listItem;
    private ViewGroup view;

    MainListViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false));
        this.view = parent;
    }

    @Override
    public void bind(ListItem item) {
        this.listItem = item;
        setItemTitle();
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
    }

    @OnClick(R.id.image_view)
    public void onImageClick() {
        Toast.makeText(view.getContext(), "Image clicked!", Toast.LENGTH_LONG).show();
    }

    private void setItemTitle() {
        itemTitleTextView.setText(listItem.getTitle());
    }
}
