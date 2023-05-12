package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.databinding.RecyclerViewRowBinding;

import java.io.Serializable;
import java.util.List;

public class RV_Adapter extends ListAdapter<Note, RV_Adapter.ViewHolder> {

    private Context context;

    private int updatedPosition = -1;


    public RV_Adapter(Context context)
    {
        super(CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.binding.titleRv.setText(note.getTitle());
        holder.binding.descpRv.setText(note.getDescription());
        holder.binding.prio.setText(String.valueOf(note.getPriority()));


        // Set the click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click event
                Intent intent = new Intent(context, Add_List_Activity.class);
                intent.putExtra("position", (Serializable) note);
                context.startActivity(intent);
            }
        });



    }


    public Note getNote(int pos){
        return getItem(pos);
    }

    public void updateNoteAtPosition(Note note, int position) {
        // Update the data source with the new note
        List<Note> notes = getCurrentList();
        notes.set(position, note);
        // Notify the adapter that the item has changed
        notifyItemChanged(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerViewRowBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerViewRowBinding.bind(itemView);

        }



    }


}
