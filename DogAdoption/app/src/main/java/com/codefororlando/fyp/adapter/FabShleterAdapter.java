package com.codefororlando.fyp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codefororlando.fyp.R;
import com.codefororlando.fyp.model.EmailList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FabShleterAdapter extends RecyclerView.Adapter<FabShleterAdapter.ViewHolder> {
    private List<EmailList> emailLists;
    Context mcontext;
    private FabShleterAdapter.OnAddClickListener listener;

    public FabShleterAdapter(Context mcontext, List<EmailList> emailLists) {
        this.mcontext = mcontext;
        this.emailLists = emailLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fab_email, parent, false);
        return new FabShleterAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String emailShelter = emailLists.get(position).getEmailShelter();
        String titleShelter = emailLists.get(position).getTitleShelter();
        holder.setData(emailShelter, titleShelter);
    }

    @Override
    public int getItemCount() {
        return emailLists.size();
    }

    public void FabShelterRecycler(OnAddClickListener listener) {
        this.listener = listener;
    }

    public interface OnAddClickListener {
        void onAddClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView emailShelter, nameEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            emailShelter = itemView.findViewById(R.id.emailShelter);
            nameEmail = itemView.findViewById(R.id.nameEmail);

            emailShelter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener !=null)
                    {
                        listener.onAddClick(position);
                    }
                }
            });
        }

        public void setData(String emailShelter1, String titleShelter) {
            emailShelter.setText(emailShelter1);
            nameEmail.setText(titleShelter);
        }
    }
}
