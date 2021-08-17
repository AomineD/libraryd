package com.dagf.dialogpacka;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.dagf.dialoglibrary.dialog.DialogPackage;
import com.dagf.dialoglibrary.dialog.UtilsDialog;
import com.dagf.dialoglibrary.dialog.WallpaperActivity;
import com.dagf.dialoglibrary.dialog.anti.BottomAnti;
import com.dagf.dialoglibrary.dialog.rate.RatingDialogHMK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  Bugsnag.notify(new RuntimeException("Test error"));
        PopUpShare popUpShare = new PopUpShare(this);


        popUpShare.setTitle("Regala esta app a tus amigos", "Hoy es un gran día para hacer un regalo especial a un amigo");
        popUpShare.setCancel_tn("Luego");
popUpShare.setShare_btn("Regalar");
popUpShare.setUrl_img("https://wallpaperplay.com/walls/full/2/5/d/129759.jpg");
popUpShare.setShareTexT("Prueba ahora esta app gratis");

if(PopUpShare.shouldShow(this, 2))
popUpShare.show();*/

       // Picasso.get().load(Uri.parse("https://jreva.app/cpanel/moreapps/images/background/76110_pelisplus-apk[1].jpg")).fit().into((ImageView) findViewById(R.id.imgv));

/*DialogPackage.setTest(true);
      DialogPackage.setIdApp(67);
      DialogPackage.setUrlServer("https://jreva.app/cpanel/update_apps/");
      DialogPackage.show(this, new DialogPackage.ListenerDialog() {
          @Override
          public void OnOk(boolean maintaneance) {
              finish();
          }

          @Override
          public void OnCancel() {
finish();
          }
      }, "name");


        BottomAnti bottomAnti = new BottomAnti(this);

        bottomAnti.setUrl("https://jreva.app/cpanel/admin-filtro/");
        bottomAnti.load(new BottomAnti.LoadListener() {
            @Override
            public void OnLoad(boolean checked, boolean capt) {
                Log.e("MAIN", "OnLoad: "+checked+" "+capt );
            }

            @Override
            public void OnFail(String erno) {
                Log.e("MAIN", "OnFail: "+erno );
            }
        });

*/
      RatingDialogHMK ratingDialogHMK = new RatingDialogHMK(this, "", "");
      ratingDialogHMK.frecuency_rating = 2;
      ratingDialogHMK.setBlacked();
        ratingDialogHMK.showRating();

     //  WallpaperActivity.openWallpapers(this);

   }

}
