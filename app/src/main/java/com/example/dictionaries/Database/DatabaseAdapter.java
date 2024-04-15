//package com.example.dictionaries.Database;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.DiffUtil;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.dictionaries.R;
//
//import java.util.List;
//
//public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.MyViewHolder> {
////    private List<String> data;
////
////    public DatabaseAdapter(List<String> data) {
////        this.data = data;
////    }
////
////    public void setData(List<String> data) {
////        this.data = data;
////        notifyDataSetChanged();
////    }
////    public List<String> getData() {
////        return data;
////    }
////
////    public static class WordDiffCallback extends DiffUtil.Callback {
////        private final List<String> oldList;
////        private final List<String> newList;
////
////        public WordDiffCallback(List<String> oldList, List<String> newList) {
////            this.oldList = oldList;
////            this.newList = newList;
////        }
////
////        @Override
////        public int getOldListSize() {
////            return oldList.size();
////        }
////
////        @Override
////        public int getNewListSize() {
////            return newList.size();
////        }
////
////        @Override
////        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
////            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
////        }
////
////        @Override
////        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
////            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
////        }
////    }
////    @NonNull
////    @Override
////    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_row, parent, false);
////        return new MyViewHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
////        String itemAnhViet = data.get(position);
////        holder.tvAnhViet.setText(itemAnhViet);
////    }
////
////    @Override
////    public int getItemCount() {
////        return data.size();
////    }
////
////    public static class MyViewHolder extends RecyclerView.ViewHolder {
////        TextView tvAnhViet, tvVietAnh;
////
////        public MyViewHolder(@NonNull View itemView) {
////            super(itemView);
////            tvAnhViet = itemView.findViewById(R.id.tv_anhViet);
////            tvVietAnh = itemView.findViewById(R.id.tv_vietAnh);
////        }
////    }
////
////    public void clearData() {
////        this.data.clear();
////        notifyDataSetChanged();
////    }
//}