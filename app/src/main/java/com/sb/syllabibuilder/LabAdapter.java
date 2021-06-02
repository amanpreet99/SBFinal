package com.sb.syllabibuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.LVView> {

    ArrayList<Labuser> LVList;

    public LabAdapter(ArrayList<Labuser> LVList) {
        this.LVList = LVList;
    }

    @NonNull
    @Override
    public LabAdapter.LVView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lab_adapter,parent,false);

        return new LVView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LVView holder, int position) {
        Labuser lu = LVList.get(position);
        holder.outputn.setText(lu.getPracnumber());
        holder.outputname.setText(lu.getPracname());
    }

    @Override
    public int getItemCount() {
        return LVList.size();
    }

    public class LVView extends RecyclerView.ViewHolder{

        TextView outputn,outputname;
        public LVView(@NonNull View itemView) {
            super(itemView);

            outputn = (TextView)itemView.findViewById(R.id.output_no);
            outputname = (TextView)itemView.findViewById(R.id.output_name);

        }
    }

}
