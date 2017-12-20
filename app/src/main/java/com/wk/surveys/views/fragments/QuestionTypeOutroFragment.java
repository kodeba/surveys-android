package com.wk.surveys.views.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wk.data.entities.Question;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeOutroBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeOutroFragment extends BaseQuestionFragment {

    FragmentQuestionTypeOutroBinding binding;

    public QuestionTypeOutroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onNextQuestion() {
        listener.OnNextQuestion(currentQuestion.getId(), userAnswer);
    }

    public static QuestionTypeOutroFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeOutroFragment fragment = new QuestionTypeOutroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_outro, container, false);

        binding.buttonNavigatoin.groupBackground.setBackgroundResource(R.color.colorPrimary);
        binding.buttonNavigatoin.nextButton.setText(R.string.button_submit);
        binding.buttonNavigatoin.nextButton.setOnClickListener(v->onNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        // TODO: 19/12/2017 AD create else case for display invalide image
        if(getArguments()!=null){
            currentQuestion = Parcels.unwrap(getArguments().getParcelable(CURRENT_QUESTION));

            binding.thankText.setText(currentQuestion.getText());

            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();
            GlideApp
                    .with(this)
                    .load(currentQuestion.getCoverImageUrl()+"l")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(drawableTransitionOptions)
                    .into(binding.groupBackground.coverBackgroundImage);

            binding.groupBackground.coverBackgroundImage.setAlpha(0.6f);

            if(TextUtils.isEmpty(currentQuestion.getFacebookProfile()) && TextUtils.isEmpty(currentQuestion.getTwitterProfile())){
                binding.contactUsGroup.setVisibility(View.GONE);
            }else{
                if(!TextUtils.isEmpty(currentQuestion.getFacebookProfile())){
                    binding.facebookButton.setOnClickListener(v->gotoURL(currentQuestion.getFacebookProfile()));
                }else{
                    binding.facebookButton.setVisibility(View.GONE);
                }

                if(!TextUtils.isEmpty(currentQuestion.getTwitterProfile())){
                    binding.twitterButton.setOnClickListener(v->gotoURL(currentQuestion.getTwitterProfile()));
                }else{
                    binding.twitterButton.setVisibility(View.GONE);
                }
            }
        }


        return binding.getRoot();
    }

    private void gotoURL(String url){
        if(URLUtil.isValidUrl(url)) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

}
