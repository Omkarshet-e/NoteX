package com.example.note_app_refined;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    EditText edtxtTitle,edtxtBody;
    Button btnAdd;
    NoteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingnote);

        edtxtTitle=findViewById(R.id.edtxtTitle);
        edtxtBody=findViewById(R.id.edtxtBody);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=edtxtTitle.getText().toString();
                String body = edtxtBody.getText().toString();
                db=NoteDatabase.getInstance(getApplicationContext());
                db.noteDao().addNote(new Note(title,body));
                Intent i = new Intent(AddNoteActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
