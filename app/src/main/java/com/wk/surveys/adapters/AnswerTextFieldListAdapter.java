package com.wk.surveys.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wk.surveys.R;
import com.wk.surveys.classes.PartialRegexInputFilter;
import com.wk.surveys.models.ItemAnswerTextField;

import java.util.List;

import timber.log.Timber;

/**
 * Created by watsaponk on 19/12/2017 AD.
 */

public class AnswerTextFieldListAdapter extends RecyclerView.Adapter<AnswerTextFieldListAdapter.AnswerTextFieldViewHolder> {

    private final List<ItemAnswerTextField> list;

    public AnswerTextFieldListAdapter(List<ItemAnswerTextField> list) {
        this.list = list;
    }


    @Override
    public AnswerTextFieldViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_answer_type_textfield, parent, false);
        AnswerTextFieldViewHolder viewHolder = new AnswerTextFieldViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnswerTextFieldViewHolder holder, int position) {
        ItemAnswerTextField itemAnswerTextField = list.get(position);
        if(itemAnswerTextField!=null){
            holder.setupView(itemAnswerTextField);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class AnswerTextFieldViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        EditText answerEditText;

        public AnswerTextFieldViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title_text);
            answerEditText = itemView.findViewById(R.id.answer_textfield);

        }

        public void setupView(ItemAnswerTextField item){
            titleTextView.setText(item.getTitle());
            answerEditText.setHint(item.getHint());

            if(!TextUtils.isEmpty(item.getFilter())){
                answerEditText.setFilters(new InputFilter[]{new PartialRegexInputFilter(item.getFilter())});
            }

            answerEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    item.setAnswer(answerEditText.getText().toString().trim());
                }
            });
        }

    }
}
