package com.salesforce.demo.detectbackground;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class BackgroundJobService extends JobIntentService {

    public static final int JOB_ID = 1000;
    public static final String SKIP_BG_CHECK = "skipBackgroundCheck";

    public static volatile boolean shouldContinue = false;

    private static final Object lock = new Object();
    private static final long BACKGROUND_TIMEOUT_WAIT_MS = 5000;

    final Handler mHandler = new Handler();

    public static void enqueueWork(Context context) {
        Intent bgJobService = new Intent(context, BackgroundJobService.class);
        BackgroundJobService.enqueueWork(
                context, BackgroundJobService.class, BackgroundJobService.JOB_ID, bgJobService);
    }

    public static void stopWork() {
        synchronized (lock) {
            shouldContinue = false;
            lock.notifyAll();
        }
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        shouldContinue = true;
        try {
            synchronized (lock) {
                lock.wait(BACKGROUND_TIMEOUT_WAIT_MS);
                if (shouldContinue) {
                    // Do the background work here
                    toast(getString(R.string.background_message));
                }
            }
        } catch (InterruptedException ex) {
            Log.e(
                    BackgroundJobService.class.getName(),
                    "There was an error waiting for background check",
                    ex);
        }
    }

    // Helper for showing tests
    void toast(final CharSequence text) {
        mHandler.post(
                new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BackgroundJobService.this, text, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
