package shv.fit.bstu.by;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import shv.fit.bstu.by.newtext.TextFunction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.newtest);
        TextFunction tf = new TextFunction();

        tv.setText(tf.getValue());
    }
}