package com.example.note_app_refined;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> list;
    FloatingActionButton floatbtn;
    RecyclerView recyclerView;
    NoteDatabase db;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatbtn = findViewById(R.id.floatbtn);
        recyclerView = findViewById(R.id.notesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = NoteDatabase.getInstance(this);
        searchView = findViewById(R.id.searchBar);


        showNote();

        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivity(intent);
                finish();

//                showNote();
            }

        });

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });



    }

    private void filter(String newText) {
        List<Note> searchList = new ArrayList<>();
        for(Note note:list){
            if(note.getTitle().toLowerCase(Locale.ROOT).contains(newText.toLowerCase(Locale.ROOT))){
                searchList.add(note);
            }
        }

        SearchAdapter adapter=new SearchAdapter(searchList,MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    public void showNote(){
        list = (ArrayList<Note>) db.noteDao().getAll();

        NoteAdapter adapter = new NoteAdapter(list,MainActivity.this,db);
        recyclerView.setAdapter(adapter);
    }


}