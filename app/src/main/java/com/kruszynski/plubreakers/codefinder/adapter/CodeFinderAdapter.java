package com.kruszynski.plubreakers.codefinder.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.kruszynski.plubreakers.R;
import com.kruszynski.plubreakers.codefinder.model.Product;
import com.kruszynski.plubreakers.decoder.ImageDecoder;

import java.util.ArrayList;
import java.util.List;

public class CodeFinderAdapter extends RecyclerView.Adapter<CodeFinderAdapter.ViewHolder> implements Filterable {
    private List<Product> products;
    private List<Product> productsFull;
    private ImageView productImageIv;
    private TextView productNameTv, codePluTv, codeTv, codePluDescriptionTv, codeDescriptionTv;

    public CodeFinderAdapter(List<Product> products) {
        this.products = products;
        this.productsFull = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CodeFinderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        productImageIv = holder.itemView.findViewById(R.id.product_image_product_item);
        productNameTv = holder.itemView.findViewById(R.id.product_name_tv_product_item);
        codePluDescriptionTv = holder.itemView.findViewById(R.id.description_code_plu_product_item);
        codeDescriptionTv = holder.itemView.findViewById(R.id.description_code_product_item);
        codePluTv = holder.itemView.findViewById(R.id.code_plu_tv_product_item);
        codeTv = holder.itemView.findViewById(R.id.code_tv_product_item);
        productImageIv.setImageDrawable(ImageDecoder.bytes2Drawable(products.get(position).getImage()));
        productNameTv.setText(products.get(position).getName());
        codePluTv.setText(products.get(position).getCodePlu());
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Product> filterProducts = new ArrayList<>();
            if (constraint == null || constraint.length() < 0) {
                filterProducts.addAll(productsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                productsFull.stream().filter(p -> p.getName().toLowerCase().contains(filterPattern)).forEach(filterProducts::add);
                productsFull.stream().filter(p -> p.getCodePlu().toLowerCase().startsWith(filterPattern)).forEach(filterProducts::add);
            }
            FilterResults results = new FilterResults();
            results.values = filterProducts;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
