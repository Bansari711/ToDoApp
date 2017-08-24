package com.example.bansari.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {

    EditText etUpdateNote;
    Intent intent;
    ArrayList<String> todoItems;
    int notePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etUpdateNote = (EditText) findViewById(R.id.etUpdateNote);
        intent = getIntent();
        etUpdateNote.setText(intent.getStringExtra("noteData").toString());
        todoItems = intent.getStringArrayListExtra("noteList");
        notePosition = intent.getIntExtra("notePosition", 0);


    }

    public void btnUpdateNote(View view) {
        todoItems.remove(notePosition);
        todoItems.add(notePosition, etUpdateNote.getText().toString());
        writeItems();
        Intent viewNoteIntent = new Intent(EditItemActivity.this, MainActivity.class);
        startActivity(viewNoteIntent);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {

        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {

        }
    }

    public void btnDeleteNote(View view) {
        todoItems.remove(notePosition);
        writeItems();
        Intent viewNoteIntent = new Intent(EditItemActivity.this, MainActivity.class);
        startActivity(viewNoteIntent); 
    }
}
