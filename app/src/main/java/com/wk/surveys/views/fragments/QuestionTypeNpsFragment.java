package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
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
import com.wk.surveys.databinding.FragmentQuestionTypeNpsBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeNpsFragment extends BaseQuestionFragment {

    FragmentQuestionTypeNpsBinding binding;

    public QuestionTypeNpsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onNextQuestion() {
        if(currentQuestion == null){
            listener.OnNextQuestion(null,null);
        }else {
            String answer = String.valueOf(binding.npsSeekbar.getProgress());
            listener.OnNextQuestion(currentQuestion.getId(), new UserAnswer(currentQuestion.getId(), UserAnswer.TYPE_NPS, answer));
        }
    }

    public static QuestionTypeNpsFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeNpsFragment fragment = new QuestionTypeNpsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_nps, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->onNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        // TODO: 19/12/2017 AD create else case for display incorrect question
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

            Integer max = currentQuestion.getAnswers().size() - 1;

            binding.npsSeekbar.setMax(max);
            binding.npsMaxNumberText.setText(String.valueOf(max));
        }

        return binding.getRoot();
    }

}
