package com.sb.syllabibuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PartAAdapter extends RecyclerView.Adapter<PartAAdapter.partAView> {

    ArrayList<PartABContent> partAList;

    public PartAAdapter(ArrayList<PartABContent> partAList) {
        this.partAList = partAList;
    }

    @NonNull
    @Override
    public PartAAdapter.partAView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_part_a_adapter,parent,false);

        return new partAView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull partAView holder, int position) {

        PartABContent partABContent = partAList.get(position);
        holder.outputt.setText(partABContent.getTitleedit());
        holder.outputc.setText(partABContent.getContentedit());
        holder.outputh.setText(partABContent.getHourstitle());

    }

    @Override
    public int getItemCount() {
        return partAList.size();
    }

    public class partAView extends RecyclerView.ViewHolder{

        TextView outputt,outputc,outputh;
        public partAView(@NonNull View itemView) {
            super(itemView);

            outputt = (TextView)itemView.findViewById(R.id.output_title);
            outputc = (TextView)itemView.findViewById(R.id.output_content);
            outputh = (TextView)itemView.findViewById(R.id.output_hours);

        }
    }

}
