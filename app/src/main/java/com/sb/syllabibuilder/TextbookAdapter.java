package com.sb.syllabibuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TextbookAdapter extends RecyclerView.Adapter<TextbookAdapter.TBView> {

    ArrayList<Content> TList;

    public TextbookAdapter(ArrayList<Content> TList) {
        this.TList = TList;
    }

    @NonNull
    @Override
    public TextbookAdapter.TBView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_textbook_adapter,parent,false);

        return new TBView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TBView holder, int position) {
        Content content = TList.get(position);
        holder.outputt.setText(content.getTitleedit());
    }

    @Override
    public int getItemCount() {
        return TList.size();
    }

    public class TBView extends RecyclerView.ViewHolder{

        TextView outputt;
        public TBView(@NonNull View itemView) {
            super(itemView);

            outputt = (TextView)itemView.findViewById(R.id.output_text);

        }
    }

}
