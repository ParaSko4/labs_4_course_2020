package shv.fit.bstu.by;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean humanOrNot = false;
    private final String[] cities = {
            "Сидячий образ жизни",
            "Умеренная активность",
            "Средняя активность",
            "Активные люди",
            "Спортсмены и люди, выполняющие сходные нагрузки"
    };

    private EditText weight;
    private EditText height;
    private EditText years;

    private Spinner spinner;
    private EditText result;
    private Switch swch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.editTextTextPersonName3);
        height = findViewById(R.id.height);
        years = findViewById(R.id.editTextTextPersonName);
        spinner = findViewById(R.id.spinner);
        result = findViewById(R.id.editTextTextPersonName4);

        swch = findViewById(R.id.switch1);
        swch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    swch.setText("Male");
                }
                else{
                    swch.setText("Female");
                }
                humanOrNot = isChecked;
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float amr = 0;
                switch((String)spinner.getSelectedItem()){
                    case "Сидячий образ жизни":
                        amr = 1.2f;
                        break;
                    case "Умеренная активность":
                        amr = 1.375f;
                        break;
                    case "Средняя активность":
                        amr=1.55f;
                        break;
                    case "Активные люди":
                        amr = 1.725f;
                        break;
                    case "Спортсмены и люди, выполняющие сходные нагрузки":
                        amr = 1.9f;
                        break;
                }

                if (!CheckValue(height.getText().toString())){
                    ShowAlert("Wrong height", "Check your height value.");
                }
                else if (!CheckValue(weight.getText().toString())){
                    ShowAlert("Wrong weight", "Check your weight value.");
                }
                else if (!CheckValue(years.getText().toString())){
                    ShowAlert("Wrong age", "Check your age value.");
                }
                else{
                    double bmrValue = HumanBMR(Float.parseFloat(weight.getText().toString()),
                            Float.parseFloat(height.getText().toString()),
                            Float.parseFloat(years.getText().toString()),
                            amr);

                    result.setText(Double.toString(Math.round(bmrValue * 100) / 100), TextView.BufferType.EDITABLE);
                }
            }
        });
    }

    private boolean CheckValue(String value){
        if (value.length() != 0){
            float fValue = Float.parseFloat(value);
            if (fValue > 0){
                if(fValue < 10000){
                    return true;
                }
            }
        }
        result.setText("err", TextView.BufferType.EDITABLE);
        return false;
    }

    private void ShowAlert(String title, String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setPositiveButton("OK", null);
        builder.setMessage(text);

        builder.create().show();
    }

    private double HumanBMR(float weight, float height, float years, float amr){
        if (humanOrNot){
            return (66.4730 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * years)) * amr;
        }
        return (655.0955 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * years)) * amr;
    }
}