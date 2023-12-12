package com.mastercodiing.retrofitpractice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercodiing.retrofitpractice.R;
import com.mastercodiing.retrofitpractice.model.CountryModel;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    ArrayList<CountryModel> countriesList;
    public void setFilteredList(ArrayList<CountryModel>filteredList){
        this.countriesList=filteredList;
        notifyDataSetChanged();
    }

    public CountryAdapter(ArrayList<CountryModel> countriesList) {
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_item,parent,false);
        CountryViewHolder viewHolder=new CountryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.countryName.setText(countriesList.get(position).getName());
        holder.countryCode.setText(countriesList.get(position).getCode());
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{
        TextView countryName;
        TextView countryCode;
       public CountryViewHolder(@NonNull View itemView) {
           super(itemView);
           countryName=itemView.findViewById(R.id.countryName);
           countryCode=itemView.findViewById(R.id.countryCode);
       }
   }
}
