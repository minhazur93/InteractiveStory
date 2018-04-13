package android.minhazur.com.interactivestory.ui;

import android.content.Intent;
import android.minhazur.com.interactivestory.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                if (name.length() > 0) {
                    Intent intent = new Intent(MainActivity.this, StoryActivity.class);
                    intent.putExtra(getString(R.string.key_name), name);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        nameEditText.setText("");
    }



}
