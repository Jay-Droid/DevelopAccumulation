package com.jay.developaccumulation.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jay.developaccumulation.R;

import java.util.List;

/**
 * Created by Jay on 2018/11/2.
 */
public class DemoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DemoItem> demoList;

    private OnItemClickListener onItemClickListener;

    public DemoListAdapter(List<DemoItem> demoList, OnItemClickListener onItemClickListener) {
        this.demoList = demoList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_demo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bind(demoList.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return demoList.size();
    }

    public static class DemoItem {

        private String title;

        private String description;

        private Class activity;

        public DemoItem(String title, String description, Class activity) {
            this.title = title;
            this.description = description;
            this.activity = activity;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Class getActivity() {
            return activity;
        }

        public void setActivity(Class activity) {
            this.activity = activity;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView description;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            description = view.findViewById(R.id.tv_description);
        }

        void bind(final DemoItem demo,
                  final OnItemClickListener onItemClickListener) {
            title.setText(demo.getTitle());
            description.setText(demo.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(demo);
                }
            });
        }
    }

    public interface OnItemClickListener {

        void onItemClick(DemoItem item);
    }
}
