package com.example.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Note_ViewModel note_viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        note_viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(Note_ViewModel.class);


        binding.floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_List_Activity.class);
                intent.putExtra("type", "addTask");
                startActivityForResult(intent, 1);  // calls the activity result
            }
        });


        binding.myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.myRecyclerView.setHasFixedSize(true);

        RV_Adapter adapter = new RV_Adapter(MainActivity.this);
        binding.myRecyclerView.setAdapter(adapter);

        note_viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });

        // for deleting the notes from the room database
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction == ItemTouchHelper.RIGHT)
                {
                    note_viewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                }
                else { // for updating - goes from mainactivity to addlistactivity
                    Intent intent = new Intent(MainActivity.this, Add_List_Activity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("priority", adapter.getNote(viewHolder.getAdapterPosition()).getPriority());
                    intent.putExtra("id", adapter.getNote(viewHolder.getAdapterPosition()).getId());
                    intent.putExtra("title", adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("descp", adapter.getNote(viewHolder.getAdapterPosition()).getDescription());
                    startActivityForResult(intent, 2);
                }



            }


        }).attachToRecyclerView(binding.myRecyclerView);



    }

    // get the results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
        {
            String title = data.getStringExtra("title");
            String descp = data.getStringExtra("descp");
            int priority = data.getIntExtra("priority",0);

            Note note = new Note(title, descp,priority);
            note_viewModel.insert(note);
            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();

        } else if (requestCode == 2) {
            String title = data.getStringExtra("title");
            String descp = data.getStringExtra("descp");
            int priority = data.getIntExtra("priority", 0);

            Note note = new Note(title, descp, priority);
            note.setId(data.getIntExtra("id",0));
            note_viewModel.update(note);
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();

        }




    }



}