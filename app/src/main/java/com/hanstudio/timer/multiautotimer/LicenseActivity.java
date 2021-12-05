package com.hanstudio.timer.multiautotimer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;


public class LicenseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        TextView text = (TextView) findViewById(R.id.text1);
        TextView fonts = (TextView) findViewById(R.id.fonts);
        Typeface type = Typeface.createFromAsset(getAssets(),"BMJUA_ttf.ttf");
        text.setTypeface(type);
        fonts.setTypeface(type);





    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);

        finish();


    }
}
