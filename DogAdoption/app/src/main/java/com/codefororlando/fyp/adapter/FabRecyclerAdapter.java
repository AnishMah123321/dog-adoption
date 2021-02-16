package com.codefororlando.fyp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codefororlando.fyp.R;
import com.codefororlando.fyp.model.ContactList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FabRecyclerAdapter extends RecyclerView.Adapter<FabRecyclerAdapter.ViewHolder> {
    private List<ContactList> contactLists;
    private Context mcontext;

    private FabRecyclerAdapter.OnAddClickListener addListener;

    public FabRecyclerAdapter(List<ContactList> mContact, Context mcontext) {
        this.contactLists = mContact;
        this.mcontext = mcontext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fab_contact, parent, false);
        return new ViewHolder(itemView);

    }
    public void setonAddClickListener(OnAddClickListener addListener) {
        this.addListener= addListener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String number = contactLists.get(position).getNumber();
        String title = contactLists.get(position).getTitle();
        holder.setData(number, title);
    }
    @Override
    public int getItemCount() {
        return contactLists.size();
    }

    public interface OnAddClickListener {
        void onAddClick(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            textView = itemView.findViewById(R.id.nameTxt);
            number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && addListener !=null)
                    {
                        addListener.onAddClick(position);
                    }

                }
            });

        }
        public void setData(String number1, String title) {
            number.setText(number1);
            textView.setText(title);
        }
    }
}
