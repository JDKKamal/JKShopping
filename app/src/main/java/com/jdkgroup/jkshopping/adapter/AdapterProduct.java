package com.jdkgroup.jkshopping.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jdkgroup.jkshopping.R;
import com.jdkgroup.jkshopping.general.General;
import com.jdkgroup.jkshopping.general.GeneralImplement;
import com.jdkgroup.jkshopping.model.shopping_201.ModelCompany;
import com.jdkgroup.jkshopping.utils.AppKeyword;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    private Activity activity;
    private List<ModelCompany> alCompany;

    private General general;

    public AdapterProduct(Activity activity, List<ModelCompany> alCompany) {
        this.activity = activity;
        this.alCompany = alCompany;

        general = new GeneralImplement();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_product, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        ModelCompany modelCompany = alCompany.get(position);
        viewHolder.apptvProductName.setText(modelCompany.getName());

        Glide.with(activity)
                .load("http://192.168.43.174:25321/JK_Shopping/jk_shopping/company/default_company.jpg")
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        viewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        viewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(viewHolder.appivProductImage);
    }

    @Override
    public int getItemCount() {
        return (null != alCompany ? alCompany.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView apptvProductName;
        private final AppCompatImageView appivProductImage;
        private final ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            appivProductImage = (AppCompatImageView) itemView.findViewById(R.id.appivProductImage);
            apptvProductName = (AppCompatTextView) itemView.findViewById(R.id.apptvProductName);
            this.progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

            general.FontAppCompatTextView(apptvProductName, AppKeyword.sourcesanspro_bold);



        }
    }

}

