package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wk.data.entities.Answer;
import com.wk.data.entities.Question;
import com.wk.data.entities.UserAnswer;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.adapters.AnswerTextFieldListAdapter;
import com.wk.surveys.databinding.FragmentQuestionTypeTextFieldBinding;
import com.wk.surveys.models.ItemAnswerTextField;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeTextFieldFragment extends BaseQuestionFragment {

    FragmentQuestionTypeTextFieldBinding binding;
    List<ItemAnswerTextField> answerTextFieldList;

    public QuestionTypeTextFieldFragment() {
        // Required empty public constructor
    }

    @Override
    public void onNextQuestion() {
        if(currentQuestion == null){
            listener.OnNextQuestion(null,null);
        }else {
            List<String> answerList = new ArrayList<>();
            for (ItemAnswerTextField textField : answerTextFieldList){
                answerList.add(textField.getAnswer());
            }

            String answer = TextUtils.join(",", answerList);

            if(currentQuestion.getIsMandatory() && answer.equalsIgnoreCase("")){
                showMadatoryAlertDialog();
            }else{
                listener.OnNextQuestion(currentQuestion.getId(), new UserAnswer(currentQuestion.getId(), UserAnswer.TYPE_TEXTFIELD, answer));
            }
        }
    }

    public static QuestionTypeTextFieldFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeTextFieldFragment fragment = new QuestionTypeTextFieldFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_text_field, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->onNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        if(getArguments()!=null){
            currentQuestion = Parcels.unwrap(getArguments().getParcelable(CURRENT_QUESTION));
            binding.questionText.setText(currentQuestion.getText());

            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();
            GlideApp
                    .with(this)
                    .load(currentQuestion.getCoverImageUrl()+"l")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(drawableTransitionOptions)
                    .into(binding.groupBackground.coverBackgroundImage);


            answerTextFieldList = new ArrayList<>();
            for (Answer answer : currentQuestion.getAnswers()){
                answerTextFieldList.add(new ItemAnswerTextField(answer.getText(),answer.getInputMaskPlaceholder(),answer.getInputMask(), ""));
            }

            AnswerTextFieldListAdapter adapter = new AnswerTextFieldListAdapter(answerTextFieldList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());

            binding.textfieldList.setHasFixedSize(true);
            binding.textfieldList.setLayoutManager(linearLayoutManager);
            binding.textfieldList.setAdapter(adapter);
        }

        return binding.getRoot();
    }

}
