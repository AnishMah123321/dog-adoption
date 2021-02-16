package com.codefororlando.fyp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codefororlando.fyp.AdoptDogActivity;
import com.codefororlando.fyp.dog.DogListActivity;
import com.codefororlando.fyp.shelter.DogShelterActivity;
import com.codefororlando.fyp.R;
import com.codefororlando.fyp.model.ListData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    Context context;

    public interface OnItemClickListener {
        void onItemClick(ListData item);
    }

    private ListData[] listData;

    public DashboardAdapter(ListData[] listData, OnItemClickListener listener) {
        this.listData = listData;

    }

    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.recycler_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.ViewHolder holder, int position) {
        final ListData myList = listData[position];
        holder.textView.setText(listData[position].getDescription());
        holder.imageView.setImageResource(listData[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myList==listData[0]) {
                    Intent intent = new Intent(context, DogListActivity.class);
                    context.startActivity(intent);

                }
                else if (myList==listData[1]){
                    Intent intent = new Intent(context, DogShelterActivity.class);
                    context.startActivity(intent);

                }else if(myList==listData[2]){
                    Intent intent = new Intent(context, AdoptDogActivity.class);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(view.getContext(),"Click on item: " + myList.getDescription(),Toast.LENGTH_LONG).show();


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
