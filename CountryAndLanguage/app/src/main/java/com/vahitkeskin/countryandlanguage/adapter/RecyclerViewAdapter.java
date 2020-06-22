package com.vahitkeskin.countryandlanguage.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vahitkeskin.countryandlanguage.R;
import com.vahitkeskin.countryandlanguage.model.CountryModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.dataHolder> {

    private ArrayList<CountryModel> countryList;
    private String[] colors = {"#666666","#8c8c8c"};

    public RecyclerViewAdapter(ArrayList<CountryModel> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public dataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.data_layout,parent,false);
        return new dataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dataHolder holder, int position) {
        holder.connect(countryList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class dataHolder extends RecyclerView.ViewHolder {
        TextView countryText;
        TextView languageText;

        public dataHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void connect(CountryModel countryModel, String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position%2]));

            countryText = itemView.findViewById(R.id.country_name);
            languageText = itemView.findViewById(R.id.language_name);

            countryText.setText(countryModel.myCountry);
            languageText.setText(countryModel.myLanguage);
        }
    }
}
