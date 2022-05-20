package com.minditsystems.app.namesavedroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.minditsystems.app.namesavedroom.db.AppDatabase;
import com.minditsystems.app.namesavedroom.db.User;

public class AddNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        final EditText productNameInput = findViewById(R.id.productNameInput);
        final EditText productBrandInput = findViewById(R.id.productBrandInput);
        final EditText description = findViewById(R.id.description);
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewUser(productNameInput.getText().toString(),productBrandInput.getText().toString(),description.getText().toString());
            }
        });
    }

    private void saveNewUser(String productName, String productBrand, String description)
    {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.productName = productName;
        user.productBrand = productBrand;
        user.description = description;
        db.userDao().insertUser(user);

        finish();
    }
}