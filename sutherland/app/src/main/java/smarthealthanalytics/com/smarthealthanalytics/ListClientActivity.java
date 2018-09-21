package smarthealthanalytics.com.smarthealthanalytics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Locale;

public class ListClientActivity extends Activity {
    // Declare variables
    ListView listView;
    EditText editsearch;
    ArrayAdapter<String> adapter;
    TextView client_txt;
    ImageView backArrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_client);

        // Store string resources into an Array
        String[] SamsungPhones = new String[] { "Happy Health Hospital", "Happy Health Physician","Client 3", "Client 4", "Client 5", "Client 6", "Client 7", "Client 8", "Client 9", "Client 10", "Client 11", "Client 12", "Client 13", "Client 14", "Client 15", "Client 16" };

        // Locate ListView in listview_main.xml
        listView = (ListView) findViewById(R.id.list_view);
        editsearch = (EditText) findViewById(R.id.search);
        client_txt = (TextView) findViewById(R.id.txt_selectclient);
        backArrow = (ImageView) findViewById(R.id.back_arrow);
        // Bind array strings into an adapter
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                SamsungPhones);
        listView.setAdapter(adapter);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/arial.ttf");
        client_txt.setTypeface(tf);

        // Capture Text in EditText

        // Capture ListView item click
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListClientActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i=new Intent(ListClientActivity.this,Dashboard.class);
                startActivity(i);



            }
        });

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_view, menu);
        return true;
    }
}