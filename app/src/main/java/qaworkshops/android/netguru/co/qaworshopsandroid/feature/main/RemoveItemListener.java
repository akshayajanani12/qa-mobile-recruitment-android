package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

@FunctionalInterface
public interface RemoveItemListener {
    void onListItemRemove(ListItem listItem);
}
