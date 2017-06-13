package qaworkshops.android.netguru.co.qaworshopsandroid.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.UserProvider;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.UserProviderSource;

@Module
public class ApplicationModule {private Application application;

    public ApplicationModule(Application application) {

        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Singleton
    @Provides
    UserProviderSource provideUserProviderSource() {
        return new UserProvider();
    }
}