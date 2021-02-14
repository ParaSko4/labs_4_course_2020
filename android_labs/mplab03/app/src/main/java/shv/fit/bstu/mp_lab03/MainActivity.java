package shv.fit.bstu.mp_lab03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import shv.fit.bstu.mp_lab03.models.DataItems;
import shv.fit.bstu.mp_lab03.models.MessageModel;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private Button saveButton;
    private String fileName = "data.json";
    private FileOutputStream outputStream;
    private TextView userText;
    private Gson json;

    private String curDate = "";
    private String chosenDate = "";

    private ArrayList<MessageModel> listMessage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = (Button)findViewById(R.id.saveButton);
        userText = (TextView)findViewById(R.id.userText);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        curDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
        chosenDate = curDate;
        json = (new GsonBuilder()).create();

        ReadData();

        ((CalendarView)findViewById(R.id.calendarView)).setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                userText.setText("");
                if (month < 10){
                    chosenDate = String.format("%d/0%d/%d", dayOfMonth, month + 1, year);
                }else{
                    chosenDate = String.format("%d/%d/%d", dayOfMonth, month + 1, year);
                }

                for (MessageModel item:listMessage){
                    if (item.date.equals(chosenDate)){
                        userText.setText(item.message);
                        break;
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    if(sdf.parse(curDate).after(sdf.parse(chosenDate))){
                        userText.setEnabled(false);
                        saveButton.setEnabled(false);
                    }
                    else{
                        userText.setEnabled(true);
                        saveButton.setEnabled(true);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = userText.getText().toString();
                boolean IsThere = false;

                for (MessageModel item:listMessage){
                    if (item.date.equals(chosenDate)){
                        if (!item.message.equals(message)){
                            try {
                                item.message = message;
                                SaveData();

                                Toast.makeText(MainActivity.this, "Message was changed and saved", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Your message already saved.", Toast.LENGTH_SHORT).show();
                        }
                        IsThere = true;
                        break;
                    }
                }
                if (!IsThere){
                    if (message.length() != 0){
                        try {
                            listMessage.add(new MessageModel(chosenDate, message));
                            SaveData();

                            Toast.makeText(MainActivity.this, "Message was saved", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Enter your message in field.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ((Button)findViewById(R.id.removeButton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boolean IsThere = false;

                for (MessageModel item:listMessage){
                    if (item.date.equals(chosenDate)){
                        try {
                            listMessage.remove(item);
                            userText.setText("");
                            SaveData();

                            Toast.makeText(MainActivity.this, "Message was deleted", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        IsThere = true;
                        break;
                    }
                }

                if (!IsThere){
                    Toast.makeText(MainActivity.this, "Can't remove. Field wasn't saved.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    checkBox.setText("External mem");
                }else{
                    checkBox.setText("Internal mem");
                }
                ReadData();
            }
        });
    }

    private void SaveData() throws IOException {
        DataItems dt = new DataItems();
        dt.setList(listMessage);

        if (checkBox.isChecked()){
            SaveExternalFile(dt);
        }else{
            SaveInternalFile(dt);
        }
    }

    private void ReadData(){
        try{
            if (checkBox.isChecked()){
                ReadExternalFile();
            }else{
                ReadInternalFile();
            }

            userText.setText("");

            if (chosenDate.length() != 0){
                for (MessageModel item:listMessage){
                    if (item.date.equals(chosenDate)){
                        userText.setText(item.message);
                        break;
                    }
                }
            }else{
                for (MessageModel item:listMessage){
                    if(item.date.equals(curDate)){
                        userText.setText(item.message);
                        break;
                    }
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void ReadInternalFile() throws FileNotFoundException {
        InputStreamReader streamReader = new InputStreamReader(openFileInput(fileName));
        listMessage = null;
        listMessage = (json.fromJson(streamReader, DataItems.class)).getList();

        Toast.makeText(MainActivity.this, "Internal file was readed.", Toast.LENGTH_SHORT).show();
    }

    private void ReadExternalFile() throws IOException {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        FileInputStream fstream = new FileInputStream(new File(folder, fileName));
        StringBuffer sbuffer = new StringBuffer();
        int i;
        while ((i = fstream.read())!= -1){
            sbuffer.append((char)i);
        }
        fstream.close();

        listMessage = null;
        listMessage = (json.fromJson(sbuffer.toString(), DataItems.class)).getList();

        Toast.makeText(MainActivity.this, "External file was readed.", Toast.LENGTH_SHORT).show();
    }

    private void SaveExternalFile(DataItems dt) throws IOException {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        FileOutputStream fstream = new FileOutputStream(new File(folder, fileName));
        fstream.write(json.toJson(dt).getBytes());
        fstream.close();
    }

    private void SaveInternalFile(DataItems dt) throws IOException {
        outputStream = openFileOutput(fileName , Context.MODE_PRIVATE);
        outputStream.write(json.toJson(dt).getBytes());
        outputStream.close();
    }
}