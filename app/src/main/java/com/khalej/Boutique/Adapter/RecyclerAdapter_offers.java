package com.khalej.Boutique.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalej.Boutique.Activity.Show;
import com.khalej.Boutique.Activity.Show_offer;
import com.khalej.Boutique.R;
import com.khalej.Boutique.model.contact_offers_realm;

import java.util.List;

public class RecyclerAdapter_offers extends RecyclerView.Adapter<RecyclerAdapter_offers.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<contact_offers_realm> contactslist;


    public RecyclerAdapter_offers(Context context, List<contact_offers_realm> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        myTypeface = Typeface.createFromAsset(context.getAssets(), "Nasser.otf");
        holder.Name.setText(contactslist.get(position).getname());
        holder.Price.setText(contactslist.get(position).getPrice() +"درهم");
        holder.oldprice.setText(contactslist.get(position).getOldprice()+"درهم");
        holder.Name.setTypeface(myTypeface);
        Glide.with(context).load(contactslist.get(position).getImg()).error(R.drawable.log).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=contactslist.get(position).getId();
                String name=contactslist.get(position).getname();
                String price=contactslist.get(position).getPrice();
                String link=contactslist.get(position).getDescription();
                String image=contactslist.get(position).getImg();
                Intent intent=new Intent(context, Show_offer.class);
                intent.putExtra("name",name);
                intent.putExtra("image",image);
                intent.putExtra("description",link);
                intent.putExtra("id",id);
                intent.putExtra("price",price);
                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Price,oldprice;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            Name=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView)itemView.findViewById(R.id.photo);
            Price=(TextView)itemView.findViewById(R.id.price);
            oldprice=(TextView)itemView.findViewById(R.id.oldprice);

        }
    }}