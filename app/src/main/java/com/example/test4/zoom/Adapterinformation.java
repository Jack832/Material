package com.example.test4.zoom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by bridgelabz4 on 8/1/16.
 */
public class Adapterinformation extends RecyclerView.Adapter<Adapterinformation.MyViewHolder> {
    private final LayoutInflater inflator;
    private Context context;
    List<Rcycleinformation> info = Collections.emptyList();

    public Adapterinformation(Context context, List<Rcycleinformation> info) {
        this.context = context;
        inflator = LayoutInflater.from(context);
        this.info = info;

    }

    public void delete(int position) {
        info.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.customrow, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Rcycleinformation inform = info.get(position);
        Log.d("HII", "onbindmethod" + position);
        holder.title.setText(inform.title);
        holder.image.setImageResource(inform.imageid);
       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);

                Toast.makeText(context,"item click at"+position,Toast.LENGTH_SHORT).show();


            }
        });*/

    }

    @Override
    public int getItemCount() {
        return info.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text1);
            image = (ImageView) itemView.findViewById(R.id.listIcon);
            image.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            delete(getPosition());
        }
    }
}
