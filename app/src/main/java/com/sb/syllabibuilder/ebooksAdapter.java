package com.sb.syllabibuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ebooksAdapter extends RecyclerView.Adapter<ebooksAdapter.EBView> {

    ArrayList<Content> EBList;

    public ebooksAdapter(ArrayList<Content> EBList) {
        this.EBList = EBList;
    }

    @NonNull
    @Override
    public ebooksAdapter.EBView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ebooks_adapter,parent,false);

        return new EBView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EBView holder, int position) {
        Content content = EBList.get(position);
        holder.outputt.setText(content.getTitleedit());
    }

    @Override
    public int getItemCount() {
        return EBList.size();
    }

    public class EBView extends RecyclerView.ViewHolder{

        TextView outputt;
        public EBView(@NonNull View itemView) {
            super(itemView);

            outputt = (TextView)itemView.findViewById(R.id.output_text);

        }
    }

}
