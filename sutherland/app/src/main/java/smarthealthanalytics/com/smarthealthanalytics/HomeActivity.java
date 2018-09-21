package smarthealthanalytics.com.smarthealthanalytics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
    Button provider,payer;
    TextView txt_domain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        payer=(Button)findViewById(R.id.btn_payer);
        provider=(Button)findViewById(R.id.btn_provider);
        txt_domain=(TextView) findViewById(R.id.txt_domaintxt);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/arial.ttf");
        txt_domain.setTypeface(tf);
        payer.setTypeface(tf);
        provider.setTypeface(tf);

        provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "clicked provider", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HomeActivity.this,ListClientActivity.class);
                startActivity(intent);
            }
        });
        payer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "clicked payer", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HomeActivity.this,ListClientActivity.class);
                startActivity(intent);
            }
        });
    }
}
