package com.swallow.architecture.architecture.modules;

import com.swallow.architecture.architecture.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    App getApp() {
        return app;
    }
}
