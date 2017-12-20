package com.wk.surveys.views.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wk.data.entities.Question;
import com.wk.data.entities.UserAnswer;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeTextAreaBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeTextAreaFragment extends BaseQuestionFragment {

    FragmentQuestionTypeTextAreaBinding binding;

    public QuestionTypeTextAreaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onNextQuestion() {
        if(currentQuestion == null){
            listener.OnNextQuestion(null,null);
        }else {
            String answer = binding.answerTextarea.getText().toString().trim();

            if(currentQuestion.getIsMandatory() && answer.equalsIgnoreCase("")){
                showMadatoryAlertDialog();
            }else{
                userAnswer = new UserAnswer(currentQuestion.getId(), UserAnswer.TYPE_TEXTAREA, answer);
                listener.OnNextQuestion(currentQuestion.getId(), userAnswer);
            }
        }
    }

    public static QuestionTypeTextAreaFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeTextAreaFragment fragment = new QuestionTypeTextAreaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_text_area, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->onNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        if(getArguments()!=null){
            currentQuestion = Parcels.unwrap(getArguments().getParcelable(CURRENT_QUESTION));
            binding.questionText.setText(currentQuestion.getText());

            if(TextUtils.isEmpty(currentQuestion.getHelpText())){
                binding.helpText.setVisibility(TextView.GONE);
            }else{
                binding.helpText.setText(currentQuestion.getHelpText());
            }

            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();
            GlideApp
                    .with(this)
                    .load(currentQuestion.getCoverImageUrl()+"l")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(drawableTransitionOptions)
                    .into(binding.groupBackground.coverBackgroundImage);

            if(!TextUtils.isEmpty(currentQuestion.getShortText())){
                binding.answerTextarea.setHint(currentQuestion.getShortText());
            }
        }

        return binding.getRoot();
    }


}
