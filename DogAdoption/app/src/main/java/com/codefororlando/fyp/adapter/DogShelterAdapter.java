package com.codefororlando.fyp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codefororlando.fyp.R;
import com.codefororlando.fyp.shelter.DetailShelterActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class DogShelterAdapter extends RecyclerView.Adapter<DogShelterAdapter.MyViewHolder> {
    private static final String TAG = "DogShelterAdapter";
    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
    private ArrayList<String> mDogShelter;
    private Context context;



    public DogShelterAdapter(Context context, ArrayList<String> mImageNames, ArrayList<String> mImages, ArrayList<String> mDogShelter) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.context = context;
        this.mDogShelter = mDogShelter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_shelter_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(context)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.images);

        holder.shelterTitle.setText(mImageNames.get(position));



        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + mImageNames.get(position));

                Toast.makeText(context,mImageNames.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailShelterActivity.class);
                intent.putExtra("image_url",mImages.get(position));
                intent.putExtra("image_name",mImageNames.get(position));
                intent.putExtra("dogShelter",mDogShelter.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView shelterTitle;
    CircleImageView images;
    RelativeLayout parentLayout;
    TextView mDogShelter;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            mDogShelter = itemView.findViewById(R.id.shelterD);
            shelterTitle = itemView.findViewById(R.id.shelterName);
            images = itemView.findViewById(R.id.dogShelterImage);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
