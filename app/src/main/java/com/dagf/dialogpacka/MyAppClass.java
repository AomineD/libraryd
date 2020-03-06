package com.dagf.dialogpacka;

import android.app.Application;
import android.content.Context;
import android.util.Log;


public class MyAppClass extends Application {

    public static Context myAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
myAppContext = this;

      /*  FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                // Do whatever you want with your token now
                // i.e. store it on SharedPreferences or DB
                // or directly send it to server
                Log.e("MAIN", "token "+token);

                FlurryMessagingListener myFlurryMessagingListener = new MyFlurryMessagingListener(MyAppClass.this);

                FlurryMarketingOptions flurryMessagingOptions = new FlurryMarketingOptions.Builder()

                        .setupMessagingWithManualIntegration(token)

                        .withDefaultNotificationIconResourceId(R.drawable.clocko)

                        .withDefaultNotificationIconAccentColor(getResources().getColor(R.color.pop_up_color))

                        .withFlurryMessagingListener(myFlurryMessagingListener)

                        .build();

                FlurryMarketingModule marketingModule = new FlurryMarketingModule(flurryMessagingOptions);

                new FlurryAgent.Builder()
                        .withLogEnabled(true)
                        .withModule(marketingModule)
                        .build(MyAppClass.this, "4GYJQFPKSJWM2ZR65XJ6");
            }
        });*/

       // Pushwoosh.getInstance().registerForPushNotifications();

    }
}
