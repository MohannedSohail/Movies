package com.example.midexam_networking;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.Myviewholder>{

    ArrayList<Attributes> data;

    Context context;


    public Recycler_Adapter(Context c,ArrayList<Attributes> data) {
        this.data = data;
        this.context=c;

    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_design,parent,false);
        Myviewholder mvh=new Myviewholder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {

        Attributes at=data.get(position);

        Picasso.get().load("https://image.tmdb.org/t/p/w185/"+at.getImage()).into(holder.iv_film);

        holder.tv_name.setText(at.getName());





    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView tv_name;
        ImageView iv_film;

        public Myviewholder(@NonNull final View itemView) {
            super(itemView);
      tv_name=itemView.findViewById(R.id.rec_tv_name);
      iv_film=itemView.findViewById(R.id.rec_iv);





        }
    }
}
