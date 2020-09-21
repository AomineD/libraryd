package com.dagf.dialoglibrary.dialog;

import android.animation.Animator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dagf.dialoglibrary.R;
import com.dagf.dialoglibrary.dialog.adapter.VipagAdapter;
import com.dagf.dialoglibrary.dialog.model.AppliModel;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

//import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DialogPersonalized extends AlertDialog {



    private DialogPackage.ListenerDialog listenerDialog;
    private Lister lister;

    public String tit;

    protected DialogPersonalized(@NonNull Context context) {
        super(context);
    }

    protected DialogPersonalized(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private AppCompatActivity activity;
    public DialogPersonalized(AppCompatActivity c, DialogPackage.ListenerDialog listener, Lister ll){
        super(c);

        this.activity = c;
        this.lister = ll;
        this.listenerDialog = listener;

    }

    private Drawable gfDrawable;

    public DialogPersonalized(Activity c, DialogPackage.ListenerDialog listener, Lister ll, Drawable gifdr){
        super(c);

        this.lister = ll;
        this.listenerDialog = listener;
        this.gfDrawable = gifdr;

    }


    private boolean isUpdate = true;
    private TextView accept;
    private TextView no_acc;


    private TextView desc;
    private TextView title_d;

    public boolean isMaintaneance;

   // private LazyViewPager vip;
    private int positi;

    public String mess = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogpersonalized);

        desc = findViewById(R.id.desc_d);

        String jj = getContext().getString(R.string.dialog_descr)+" " +tit+" "+getContext().getString(R.string.dialog_descr_2);

        desc.setText(jj);


     //   vipagAdapter = new VipagAdapter(activity.getSupportFragmentManager(),getContext(), apps);







        title_d = findViewById(R.id.title_d);

        if(isMaintaneance){
            if(mess == null) {
                desc.setText(getContext().getString(R.string.dialog_desc_maint));
            }else{
                desc.setText(mess);
            }
            title_d.setText(getContext().getString(R.string.dialog_maintaneance));
        }

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.transparent));

        final ImageView v = findViewById(R.id.iconimg);
        accept = findViewById(R.id.accept);
        if(v != null) {
            if (isMaintaneance) {
                accept.setText(getContext().getString(R.string.ok_maint));
                v.setImageDrawable(getContext().getResources().getDrawable(R.drawable.clocko));
            } else {
         //       v.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
        // Glide.with(getContext()).asGif().load(R.drawable.gif_d).fitCenter().into(v);
            }
        }

        if(isMaintaneance) {
            getApps();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {


                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        /*if(v != null)
                            v.setVisibility(View.GONE);*/


                            if (apps.size() < 1) {
                                return;
                            }

                            v.setVisibility(View.VISIBLE);
                            findViewById(R.id.gif_img).setVisibility(View.GONE);
                            findViewById(R.id.anothert).setVisibility(View.VISIBLE);

                            //                       Log.e("MAIN", "run: "+positi + " / "+ apps.size());


                            String texting = apps.get(positi).descApp;

                            desc.setText(texting);

                            title_d.setText(apps.get(positi).nameApp);

                      /*  if(activity.getSupportFragmentManager() != null) {
                         /*   FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

                            ImageFragment imgf = new ImageFragment();

                            imgf.urlimg = apps.get(positi).urlImage;
                            imgf.urlApp = apps.get(positi).urlApp;


                            transaction.replace(R.id.framlay, imgf).commitAllowingStateLoss();

                        }*/

                  /*    Glide.with(activity).asBitmap().load(Uri.parse(apps.get(positi).urlImage)).listener(new RequestListener<Bitmap>() {
                          @Override
                          public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {

                              return false;
                          }

                          @Override
                          public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                              return false;
                          }
                      }).into(v);*/
                            Picasso.get().load(Uri.parse(apps.get(positi).urlImage)).fit().into(v, new Callback() {
                                @Override
                                public void onSuccess() {

                                    YoYo.with(Techniques.SlideInLeft)
                                            .duration(1000)
                                            .repeat(0)
                                            .playOn(v);
                                    initAnim(v);

                                    YoYo.with(Techniques.FadeInUp)
                                            .duration(1000)
                                            .repeat(0)
                                            .playOn(title_d);
                                    initAnimText(title_d);

                                    YoYo.with(Techniques.FadeInUp)
                                            .duration(1000)
                                            .repeat(0)
                                            .playOn(desc);
                                    initAnimText(desc);

                                }

                                @Override
                                public void onError(Exception e) {
                                    Log.e("MAIN", "onError: " + e.getMessage() + apps.get(positi).urlImage);
                                }
                            });

                            final String urlAPP = apps.get(positi).urlApp;

                            v.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    VipagAdapter.downloadApp(getContext(), urlAPP);
                                }
                            });


                            accept.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //  Log.e(TAG, "onClick: " );
                                    VipagAdapter.downloadApp(getContext(), urlAPP);
                                }
                            });
                            accept.setText(getContext().getString(R.string.installapp));


                            if (positi < apps.size() - 1) {
                                positi++;


                            } else {
                                positi = 0;
                            }


                            //  vip.setCurrentItem(positi, true);
                            //  Log.e("MAIN", "run: "+positi );
                        }
                    });


                }
            }, 2000, 7500);
        }





no_acc = findViewById(R.id.cancel);

accept.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dismiss();
        listenerDialog.OnOk(isMaintaneance);
        lister.onClickToDownload();

    }
});

no_acc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dismiss();
        listenerDialog.OnCancel();
    }
});


    }



    /**  ANIMACIONES **/

    private void initAnimText(final View v){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        YoYo.with(Techniques.FadeOutDown)
                                .duration(900)
                                .repeat(0)
                                .onEnd(new YoYo.AnimatorCallback() {
                                    @Override
                                    public void call(Animator animator) {

                                    }
                                })
                                .playOn(v);
                    }
                });
            }
        }, 4500);
    }


    private void initAnim(final View v){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        YoYo.with(Techniques.SlideOutRight)
                                .duration(900)
                                .repeat(0)
                                .onEnd(new YoYo.AnimatorCallback() {
                                    @Override
                                    public void call(Animator animator) {

                                    }
                                })
                                .playOn(v);
                    }
                });
            }
        }, 4500);
    }

   public interface Lister{
        void onClickToDownload();
   }

   private ArrayList<AppliModel> apps = new ArrayList<>();
 //   private VipagAdapter vipagAdapter;

   private void getApps(){
       RequestQueue queue = Volley.newRequestQueue(getContext());
       StringRequest request = new StringRequest(Request.Method.GET, DialogPackage.URL_SERVER+"api.php?allvidios", new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               try {
                   JSONArray arr = new JSONArray(response);

                   for(int i=0; i < arr.length(); i++){
                       JSONObject o = arr.getJSONObject(i);
                       AppliModel mo = new AppliModel();

                       mo.nameApp = o.getString("name_app");
                       mo.urlApp = o.getString("url_app");
                       mo.descApp = o.getString("desc_app");
                       mo.urlImage = o.getString("url_img");


if(!mo.urlApp.contains(activity.getPackageName())) {
    apps.add(mo);
}


                   }
//                   vipagAdapter.notifyDataSetChanged();

               } catch (JSONException e) {
                   e.printStackTrace();
                   Log.e("MAIN", "onResponse: "+e.getMessage() );
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.e("MAIN", "Error "+error.getMessage());
           }
       });

       queue.add(request);
   }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.e("MAIN", "key "+keyCode);


        if(keyCode == 4){
            activity.finish();
        }

        return super.onKeyDown(keyCode, event);
    }
}
