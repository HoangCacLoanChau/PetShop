package com.example.project_se150664;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    String data1[];
    String data2[];
    int images[];
    Context context;
    private int position;
    CardView card;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Adapter(Context ct, String[] s1, String[] s2, int img[]) {
        data1 = s1;
        data2 = s2;
       images = img;
        context = ct;
    }

    //đổ nội dung mylayout vào
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.mylayout, viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.d1.setText(data1[i]);
        viewHolder.d2.setText(data2[i]);
        viewHolder.myImage.setImageResource(images[i]);
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                viewHolder.itemView.showContextMenu();
                return  true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return images.length-1;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView d1,d2;
        ImageView myImage;
        CardView card = itemView.findViewById(R.id.cardShopping);



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            d1 = itemView.findViewById(R.id.catagory_txt);
            d2= itemView.findViewById(R.id.description_txt);
            myImage = itemView.findViewById(R.id.image111);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(),R.id.context_add, 0,"Add to your wishlst");
            menu.add(getAdapterPosition(),R.id.context_share, 1,"Share to your friend");
        }





    }

}
