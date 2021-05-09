package com.sb.syllabibuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReferenceAdapter extends RecyclerView.Adapter<ReferenceAdapter.RBView> {

    ArrayList<Content> RList;

    public ReferenceAdapter(ArrayList<Content> RList) {
        this.RList = RList;
    }

    @NonNull
    @Override
    public ReferenceAdapter.RBView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_reference_adapter,parent,false);

        return new RBView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RBView holder, int position) {
        Content content = RList.get(position);
        holder.outputt.setText(content.getTitleedit());
    }

    @Override
    public int getItemCount() {
        return RList.size();
    }

    public class RBView extends RecyclerView.ViewHolder{

        TextView outputt;
        public RBView(@NonNull View itemView) {
            super(itemView);

            outputt = (TextView)itemView.findViewById(R.id.output_text);

        }
    }

}
