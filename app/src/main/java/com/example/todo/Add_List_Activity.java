package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todo.databinding.ActivityAddListBinding;

public class Add_List_Activity extends AppCompatActivity {

    ActivityAddListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")){
            setTitle("Update Task");
            int id = getIntent().getIntExtra("id",0);
            binding.addTitle.setText(getIntent().getStringExtra("title"));
            binding.addDetails.setText(getIntent().getStringExtra("descp"));

            binding.addbtn.setText("Update Task");
            // when update btn is clicked, updates the data
            binding.addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.addTitle.getText().toString());
                    intent.putExtra("descp", binding.addDetails.getText().toString());

                    intent.putExtra("id", id); // send the value of old id
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }
        else {
            setTitle("Add Task");
            binding.addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.addTitle.getText().toString());
                    intent.putExtra("descp", binding.addDetails.getText().toString());

                    setResult(RESULT_OK, intent);
                    finish();

                }
            });
        }




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Add_List_Activity.this, MainActivity.class));
    }
}