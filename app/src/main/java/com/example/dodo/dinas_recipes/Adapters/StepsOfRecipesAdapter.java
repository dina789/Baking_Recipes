package com.example.dodo.dinas_recipes.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dodo.dinas_recipes.Models.Steps;
import com.example.dodo.dinas_recipes.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsOfRecipesAdapter extends RecyclerView.Adapter<StepsOfRecipesAdapter.ViewHolder>  {


    private ArrayList<Steps> stepsModelList;
    private final StepItemClickListener itemClickListener;

    public interface StepItemClickListener {
        void onClickStep(int position);
    }

    public StepsOfRecipesAdapter(ArrayList<Steps> stepsArrayList ,StepItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.stepsModelList=stepsArrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.steps_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.stepDescription.setText(stepsModelList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (stepsModelList == null) {
            return -1;
        }
        return stepsModelList.size();
    }

   //public void setStepsModelList(ArrayList<Steps> stepsModelList) {
       //this.stepsModelList = stepsModelList;
   //    notifyDataSetChanged();
  //  }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.step_description)
        TextView stepDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClickStep(getAdapterPosition());
        }
    }

}
