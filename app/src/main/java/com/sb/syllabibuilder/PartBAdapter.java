package com.sb.syllabibuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PartBAdapter extends RecyclerView.Adapter<PartBAdapter.partBView> {

    ArrayList<PartABContent> partBList;

    public PartBAdapter(ArrayList<PartABContent> partBList) {
        this.partBList = partBList;
    }

    @NonNull
    @Override
    public PartBAdapter.partBView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_part_b_adapter,parent,false);

        return new partBView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull partBView holder, int position) {

        PartABContent partABContent = partBList.get(position);
        holder.outputtB.setText(partABContent.getTitleedit());
        holder.outputcB.setText(partABContent.getContentedit());
        holder.outputhB.setText(partABContent.getHourstitle());

    }

    @Override
    public int getItemCount() {
        return partBList.size();
    }

    public class partBView extends RecyclerView.ViewHolder{

        TextView outputtB,outputcB,outputhB;
        public partBView(@NonNull View itemView) {
            super(itemView);

            outputtB = (TextView)itemView.findViewById(R.id.output_titleB);
            outputcB = (TextView)itemView.findViewById(R.id.output_contentB);
            outputhB = (TextView)itemView.findViewById(R.id.output_hoursB);

        }
    }

}
