package qaworkshops.android.netguru.co.qaworshopsandroid.data;

import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.Realm;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;

public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {
        Date date = new GregorianCalendar(1990, 1, 1).getTime();
        User user = new User("John", "Doe", "john.doe@example.com", date.getTime(), "Poland", "Male");
        realm.executeTransaction(db -> db.copyToRealm(user));
    }

    @Override
    public int hashCode() {
        return RealmInitialData.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof RealmInitialData;
    }
}