package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.todo.databinding.ActivityAddListBinding;

import java.util.List;

public class Add_List_Activity extends AppCompatActivity {

    ActivityAddListBinding binding;
    private int status;

    private RV_Adapter mAdapter;



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
            int rdbtn = getIntent().getIntExtra("priority", 0);
            if(rdbtn == 1){
                binding.high.setChecked(true);
            } else if(rdbtn == 2){
                binding.med.setChecked(true);
            } else if(rdbtn == 3){
                binding.low.setChecked(true);
            }
            binding.addbtn.setText("Update Task");
            // when update btn is clicked, updates the data
            binding.addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.addTitle.getText().toString());
                    intent.putExtra("descp", binding.addDetails.getText().toString());

                    int checkRadio = binding.radioGroup.getCheckedRadioButtonId();
                    if (checkRadio == R.id.high){
                        status = 1;
                    } else if (checkRadio == R.id.med){
                        status = 2;
                    } else if (checkRadio == R.id.low){
                        status = 3;
                    }

                    intent.putExtra("priority", status);
                    intent.putExtra("id", id); // send the value of old id

                    setResult(RESULT_OK, intent);


                    // Notify the adapter about the changes
                    int position = getIntent().getIntExtra("position", -1);
                    if (position != -1) {
                        mAdapter.notifyItemChanged(position);
                        // Update the list of notes in the adapter
                        List<Note> notes = mAdapter.getCurrentList();
                   ;

                        mAdapter.submitList(notes);
                    }


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

                    int checkRadio = binding.radioGroup.getCheckedRadioButtonId();
                    if (checkRadio == R.id.high){
                        status = 1;
                    } else if (checkRadio == R.id.med){
                        status = 2;
                    } else if (checkRadio == R.id.low){
                        status = 3;
                    }


                    intent.putExtra("priority",status);
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