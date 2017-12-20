package com.wk.surveys.views.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wk.data.entities.Question;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeIntroBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeIntroFragment extends BaseQuestionFragment {

    FragmentQuestionTypeIntroBinding binding;

    public QuestionTypeIntroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onNextQuestion() {
        listener.OnNextQuestion(currentQuestion.getId(),userAnswer);
    }

    public static QuestionTypeIntroFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeIntroFragment fragment = new QuestionTypeIntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_intro, container, false);

        binding.buttonNavigatoin.groupBackground.setBackgroundResource(R.color.colorPrimary);
        binding.buttonNavigatoin.previousButton.setVisibility(Button.GONE);

        binding.buttonNavigatoin.nextButton.setText(getString(R.string.button_start));
        binding.buttonNavigatoin.nextButton.setOnClickListener(v->onNextQuestion());

        // TODO: 19/12/2017 AD create else case for display invalide image
        if(getArguments()!=null){
            currentQuestion = Parcels.unwrap(getArguments().getParcelable(CURRENT_QUESTION));

            binding.greetingText.setText(currentQuestion.getText());

            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();
            GlideApp
                    .with(this)
                    .load(currentQuestion.getCoverImageUrl()+"l")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(drawableTransitionOptions)
                    .into(binding.groupBackground.coverBackgroundImage);

            binding.groupBackground.coverBackgroundImage.setAlpha(0.6f);

            GlideApp
                    .with(this)
                    .load(currentQuestion.getImageUrl()+"l")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .transition(drawableTransitionOptions)
                    .into(binding.mainImage);


        }

        return binding.getRoot();
    }



}
