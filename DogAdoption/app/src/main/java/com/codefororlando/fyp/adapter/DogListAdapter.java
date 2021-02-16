package com.codefororlando.fyp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codefororlando.fyp.R;
import com.codefororlando.fyp.dog.DogDetailActivity;
import com.codefororlando.fyp.model.UserPet;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DogListAdapter extends RecyclerView.Adapter<DogListAdapter.MyViewHolder> {
    private static final String TAG = "DogListAdapter";

private List<UserPet> userPetList;
private Context context;

    public DogListAdapter(List<UserPet> userPetList, Context context) {
        this.userPetList = userPetList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_list,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final UserPet userPet = userPetList.get(position);

        holder.dogName.setText(userPet.getDogname());
        holder.contactName.setText(userPet.getName());
        holder.contactNumber.setText(userPet.getNumber());
        holder.dBreed.setText(userPet.getBreed());
        holder.contactEmail.setText(userPet.getEmail());
        holder.dogDecription.setText(userPet.getDescription());

        holder.dRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPet userPet1 = userPetList.get(position);
              Intent intent = new Intent(context, DogDetailActivity.class);
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              intent.putExtra("dog",userPet1.getDogname());
              intent.putExtra("name",userPet1.getName());
              intent.putExtra("number",userPet1.getNumber());
              intent.putExtra("email",userPet1.getEmail());
              intent.putExtra("breed",userPet1.getBreed());
              intent.putExtra("description",userPet1.getDescription());
              context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userPetList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView contactName, contactNumber, contactEmail,dogName,dBreed,dogDecription;
public CardView cardView;
RelativeLayout dRl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
cardView = itemView.findViewById(R.id.cardView);
        dogName = itemView.findViewById(R.id.dogName);
        contactName = itemView.findViewById(R.id.contactName);
        contactNumber = itemView.findViewById(R.id.contactNumber);
        contactEmail = itemView.findViewById(R.id.contactEmail);
        dBreed = itemView.findViewById(R.id.Dbreed);
        dogDecription = itemView.findViewById(R.id.DogDescription);
        dRl = itemView.findViewById(R.id.dogRl);
        }
    }
}
