package com.itcse.beerrecepies.view.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itcse.beerrecepies.R;
import com.itcse.beerrecepies.model.data.BeerDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerListRecyclerAdapter extends RecyclerView.Adapter<BeerListRecyclerAdapter.ViewHolder> {

    private List<BeerDetails> beerDetailsList;

    BeerListRecyclerAdapter(@NonNull final List<BeerDetails> beerDetailsList) {
        this.beerDetailsList = beerDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_beer_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BeerDetails beerDetails = beerDetailsList.get(position);
        holder.tvName.setText(beerDetails.name);
        holder.tvDescription.setText(beerDetails.description);
        String abvValue = String.valueOf(beerDetails.abv);
        if (!TextUtils.isEmpty(abvValue)) {
            abvValue += " %";
            holder.tvABVValue.setText(abvValue);
        } else {
            holder.tvABVValue.setText(holder.tvABVValue.getContext().getString(R.string.not_application));
        }

        String ibuValue = String.valueOf(beerDetails.ibu);
        if (!TextUtils.isEmpty(ibuValue)) {
            ibuValue += " %";
            holder.tvIBUValue.setText(ibuValue);
        } else {
            holder.tvIBUValue.setText(holder.tvIBUValue.getContext().getString(R.string.not_application));
        }
        if (!TextUtils.isEmpty(beerDetails.image_url)) {
            Glide.with(holder.ivBeerImage.getContext())
                    .load(beerDetails.image_url)
                    .into(holder.ivBeerImage);
        } else {
            holder.ivBeerImage.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return beerDetailsList.size();
    }

    /**
     * Function to update list of beers.
     * @param beerList contains new list of data.
     */
    void updateList(@NonNull final List<BeerDetails> beerList) {
        this.beerDetailsList.clear();
        this.beerDetailsList.addAll(beerList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivBeerImage)
        ImageView ivBeerImage;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvABVValue)
        TextView tvABVValue;
        @BindView(R.id.tvIBUValue)
        TextView tvIBUValue;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
