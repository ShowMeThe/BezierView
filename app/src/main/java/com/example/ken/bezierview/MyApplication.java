package com.example.ken.bezierview;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Ken on 2017/12/2.
 */
public class MyApplication extends Application {

     @Override
    public void onCreate(){
         super.onCreate();
         registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
             @Override
             public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

             }

             @Override
             public void onActivityStarted(Activity activity) {

             }

             @Override
             public void onActivityResumed(Activity activity) {
             KeepScreenWake(activity);
             }

             @Override
             public void onActivityPaused(Activity activity) {

             }

             @Override
             public void onActivityStopped(Activity activity) {

             }

             @Override
             public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

             }

             @Override
             public void onActivityDestroyed(Activity activity) {

             }
         });
     }


    private static void KeepScreenWake(Activity activity){
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
