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