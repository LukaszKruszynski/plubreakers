package com.kruszynski.plubreakers.codetest.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kruszynski.plubreakers.R;
import com.kruszynski.plubreakers.codetest.reply.TestReply;

import java.util.List;

public class TestReplyAdapter extends RecyclerView.Adapter<TestReplyAdapter.ViewHolder> {
    private List<TestReply> replies;
    private TextView correctCode;
    private TextView insertedCode;
    private ImageView image;
    private TextView isCorrect;
    private TextView productName;
    private TextView insertedCodeDescription;


    public TestReplyAdapter(List<TestReply> replies) {
        this.replies = replies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TestReplyAdapter.ViewHolder holder, int position) {
        setReplyItem(holder, position);
    }

    @Override
    public int getItemCount() {
        return replies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void setColors(TestReplyAdapter.ViewHolder holder, int position) {
        insertedCode = holder.itemView.findViewById(R.id.item_reply_display_inserted_code);
        isCorrect = holder.itemView.findViewById(R.id.item_reply_display_is_correct);
        insertedCodeDescription = holder.itemView.findViewById(R.id.item_reply_final_text_inserted_code);
        if (replies.get(position).isCorrect()) {
            isCorrect.setText("V");
            isCorrect.setTextColor(ColorStateList.valueOf((Color.GREEN)));
            insertedCode.setTextColor(ColorStateList.valueOf((Color.GREEN)));
            insertedCodeDescription.setText(R.string.entered_code_description);
        } else {
            isCorrect.setText("X");
            isCorrect.setTextColor(ColorStateList.valueOf(Color.RED));
            insertedCode.setTextColor(ColorStateList.valueOf(Color.RED));
            if (insertedCode.getText().toString().equals("PLU")) {
                insertedCodeDescription.setText(R.string.not_entered_code_description);
            }
        }
    }

    private void setCodes(TestReplyAdapter.ViewHolder holder, int position) {
        insertedCode = holder.itemView.findViewById(R.id.item_reply_display_inserted_code);
        correctCode = holder.itemView.findViewById(R.id.item_reply_display_correct_code);
        insertedCode.setText(replies.get(position).getInsertedCode());
        correctCode.setText(replies.get(position).getCorrectCode());
    }

    private void setImage(TestReplyAdapter.ViewHolder holder, int position) {
        image = holder.itemView.findViewById(R.id.item_reply_iamge_product);
        image.setImageDrawable(replies.get(position).getImage());
    }

    private void setProductName(TestReplyAdapter.ViewHolder holder, int position) {
        productName = holder.itemView.findViewById(R.id.item_reply_display_product_name);
        productName.setText(replies.get(position).getProductName());
    }

    private void setReplyItem(TestReplyAdapter.ViewHolder holder, int position) {
        setCodes(holder, position);
        setImage(holder, position);
        setProductName(holder, position);
        setColors(holder, position);
    }

}
