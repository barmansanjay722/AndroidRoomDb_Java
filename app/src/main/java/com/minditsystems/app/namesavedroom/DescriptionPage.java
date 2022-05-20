package com.minditsystems.app.namesavedroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.minditsystems.app.namesavedroom.db.AppDatabase;

public class DescriptionPage extends AppCompatActivity {

    EditText recive_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_page);

        recive_msg = findViewById(R.id.descriptiondetail);
        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        int uid = intent.getIntExtra("uid",-1);
        recive_msg.setText(description);

        EditText descriptionValue = findViewById(R.id.descriptiondetail);
        Button saveDescbutton = findViewById(R.id.saveDescbutton);
//        saveDescbutton.setEnabled(false);
        saveDescbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(descriptionValue.getText().toString(),uid);
                alertDialog();
//                saveDescbutton.setEnabled(true);
            }
        });
    }

    private void alertDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Data Saved !!");
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(600,400);
    }

    public void update(String description, int uid)
    {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        db.userDao().update(description,uid);
    }
}