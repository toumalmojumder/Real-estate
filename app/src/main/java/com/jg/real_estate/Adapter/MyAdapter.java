package com.jg.real_estate.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jg.real_estate.Model.House;
import com.jg.real_estate.R;

import java.util.ArrayList;

//recycler view adapter
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{




    private OnItemClickListener mListener;
    Context context;
    ArrayList<House> houses;
//creating interface to click each recycle view row.
    public interface OnItemClickListener{
        void onItemClickek(int position);
    }
    //creating own onclick listener.
    public void  setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    //constructor
    public MyAdapter(Context context, ArrayList<House> houses){
        this.context = context;
        this.houses = houses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final House hou = this.houses.get(position);

        //seting data in each row
        holder.houID.setText(String.valueOf(hou.getID()));
        holder.houName.setText(hou.getEstate_name());
        holder.houPrice.setText("$"+hou.getEstate_price()+"/-");
        String location = "Location: "+hou.getStreet()+", "+hou.getCity()+", "+hou.getProvince()+".";
        holder.houLocation.setText(location);
        String phoneEmail = "Phone: "+hou.getPhone()+",Email: "+hou.getEmail();
        holder.houPhone.setText(phoneEmail);
        holder.houimage.setImageBitmap(hou.getEstate_image1());
    }

    @Override
    public int getItemCount() {
        //table row size
        return this.houses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView houID,houName,houPrice,houLocation,houPhone;
ImageView houimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           //pointing place holder
            houID = itemView.findViewById(R.id.row_id);
            houName = itemView.findViewById(R.id.row_name);
            houPrice = itemView.findViewById(R.id.row_price);
            houLocation = itemView.findViewById(R.id.row_location);
            houPhone = itemView.findViewById(R.id.row_phone_email);
            houimage = itemView.findViewById(R.id.row_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
            if(mListener!=null){
           int position = getAdapterPosition();
           if(position!= RecyclerView.NO_POSITION){
               mListener.onItemClickek(position);
           }
       }
                }
            });
        }
    }
}
