package smarthealthanalytics.com.smarthealthanalytics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    EditText edt_username,edt_password;
    Button btn_login,btn_touch_login;
    TextView header_smart,txt_help_login,txt_branding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        process();
    }


    private void init() {
        edt_username=(EditText)findViewById(R.id.edit_user);
        edt_password=(EditText)findViewById(R.id.edit_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_touch_login=(Button)findViewById(R.id.login_touch);
        header_smart=(TextView)findViewById(R.id.txt_smarttitle);
        txt_help_login=(TextView)findViewById(R.id.txt_helplogin);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
        header_smart.setTypeface(tf);
        edt_username.setTypeface(tf);
        edt_password.setTypeface(tf);
        btn_login.setTypeface(tf);
        txt_help_login.setTypeface(tf);
        btn_touch_login.setTypeface(tf);
        edt_username.setText("sutherlanddemo");
        edt_password.setText("P@ssw0rd");

    }

    private void process() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edt_username.getText().toString().trim();
                String password=edt_password.getText().toString().trim();
                if (username.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "Please enter the username",
                            Toast.LENGTH_LONG).show();
                }
                else if (password.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "Please enter the password",
                            Toast.LENGTH_LONG).show();
                }

               else if (username.equals("sutherlanddemo")&&(password.equals("P@ssw0rd"))){
                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);

                }else {

                    Toast.makeText(LoginActivity.this, "Incorrect username and password", Toast.LENGTH_SHORT).show();
                }
                }


        });
    }
}
