package com.example.eplanettask.alram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eplanettask.R;
import com.example.eplanettask.databinding.AlaramListRowBinding;
import java.util.List;

public class SaveAlaramAdapter extends RecyclerView.Adapter<SaveAlaramAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<AlaramBean> alaramkList;

    public SaveAlaramAdapter(Context mCtx, List<AlaramBean> alaramList) {
        this.mCtx = mCtx;
        this.alaramkList = alaramList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       AlaramListRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.alaram_list_row,parent,false);
        return new TasksViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        AlaramBean bean = alaramkList.get(position);

        holder.binding.textTime.setText(bean.getTimeString());
        holder.binding.textViewDesc.setText(bean.getDesc());

        if(bean.getTimeMills() > 0){
            ((AlaramDataAct)mCtx).startAlarm(bean.getTimeMills());
        }
        long time= System.currentTimeMillis();
        if (bean.getTimeMills() < System.currentTimeMillis())
            holder.binding.textStautus.setText("Completed");
        else
            holder.binding.textStautus.setText("Not Completed");
    }

    @Override
    public int getItemCount() {
        return alaramkList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder  {

        AlaramListRowBinding binding;
        public TasksViewHolder(AlaramListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
