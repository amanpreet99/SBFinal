package com.sb.syllabibuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OnlineVideosAdapter extends RecyclerView.Adapter<OnlineVideosAdapter.OVView> {

    ArrayList<Content> OVList;

    public OnlineVideosAdapter(ArrayList<Content> OVList) {
        this.OVList = OVList;
    }

    @NonNull
    @Override
    public OnlineVideosAdapter.OVView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_online_videos_adapter,parent,false);

        return new OVView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OVView holder, int position) {
        Content content = OVList.get(position);
        holder.outputt.setText(content.getTitleedit());
    }

    @Override
    public int getItemCount() {
        return OVList.size();
    }

    public class OVView extends RecyclerView.ViewHolder{

        TextView outputt;
        public OVView(@NonNull View itemView) {
            super(itemView);

            outputt = (TextView)itemView.findViewById(R.id.output_text);

        }
    }

}
