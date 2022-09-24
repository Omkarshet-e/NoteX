package com.example.note_app_refined;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    ArrayList<Note> list;
    Context context;
    NoteDatabase db;

    public NoteAdapter(ArrayList<Note> list, Context context, NoteDatabase db) {
        this.list = list;
        this.context = context;
        this.db=db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_note,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.body.setText(list.get(position).getBody());

        holder.llnote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                db.noteDao().delNote(list.get(holder.getAdapterPosition()));
                list.remove(holder.getAdapterPosition());
//                notifyDataSetChanged();
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(),list.size());


                return true;
            }
        });

        holder.llnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,body;
        LinearLayout llnote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.cardTitle);
            body=itemView.findViewById(R.id.cardBody);
            llnote=itemView.findViewById(R.id.llnote);
        }
    }
}
