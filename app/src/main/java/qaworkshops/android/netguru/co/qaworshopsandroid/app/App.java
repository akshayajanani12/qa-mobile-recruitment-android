package qaworkshops.android.netguru.co.qaworshopsandroid.app;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.orhanobut.hawk.Hawk;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.RealmInitialData;

public class App extends Application {

    private ApplicationComponent appComponent;

    public static ApplicationComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();

        initializeRealm();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // init dagger appComponent
        this.appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmInitialData realmInitialData = new RealmInitialData();
        realmInitialData.execute(Realm.getDefaultInstance());
    }

}
