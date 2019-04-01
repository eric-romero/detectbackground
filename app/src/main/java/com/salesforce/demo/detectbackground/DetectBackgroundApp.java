package com.salesforce.demo.detectbackground;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class DetectBackgroundApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(
                new ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityPaused(Activity activity) {
                        if (activity.getIntent() == null
                                || !activity.getIntent()
                                        .getBooleanExtra(
                                                BackgroundJobService.SKIP_BG_CHECK, false)) {
                            BackgroundJobService.enqueueWork(activity);
                        }
                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                        BackgroundJobService.stopWork();
                    }

                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

                    @Override
                    public void onActivityStarted(Activity activity) {}

                    @Override
                    public void onActivityStopped(Activity activity) {}

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

                    @Override
                    public void onActivityDestroyed(Activity activity) {}
                });
    }
}
