package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.RemoveItemListener;

public class MainListAdapter extends RecyclerView.Adapter<MainListViewHolder> {

    @NonNull
    private List<ListItem> itemList;

    private final RemoveItemListener removeItemListener;

    public MainListAdapter(RemoveItemListener removeItemListener) {
        this.itemList = new ArrayList<>();
        this.removeItemListener = removeItemListener;
    }

    @Override
    public MainListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainListViewHolder(parent, removeItemListener);
    }

    @Override
    public void onBindViewHolder(MainListViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public List<ListItem> getData() {
        return itemList;
    }

//    public void addNewItem(ListItem itemToAdd) {
//        this.itemList.add(itemToAdd);
//        notifyDataSetChanged();
//    }

//    public void addAllItemsToList(List<ListItem> list) {
//        itemList.addAll(list);
//    }

    public void setListItem(List<ListItem> list) {
        itemList = list;
        notifyDataSetChanged();
    }
}
