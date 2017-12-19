package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.wk.data.entities.Question;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeRatingBinding;

import org.parceler.Parcels;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeRatingFragment extends BaseQuestionFragment {

    public static final Integer STAR_RATING = 0;
    public static final Integer HEART_RATING = 1;
    public static final Integer SMILEY_RATING = 2;
    public static final String RATING_TYPE = "rating_type";

    public QuestionTypeRatingFragment() {
        // Required empty public constructor
    }

    public static QuestionTypeRatingFragment newInstance(Question question, Integer ratingType) {
        Bundle args = new Bundle();

        args.putInt(RATING_TYPE,ratingType);
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));

        QuestionTypeRatingFragment fragment = new QuestionTypeRatingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuestionTypeRatingBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_rating, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->listener.OnNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        if(getArguments()!=null){
            currentQuestion = Parcels.unwrap(getArguments().getParcelable(CURRENT_QUESTION));
            Integer ratingType = getArguments().getInt(RATING_TYPE);
            Timber.i("ratingType %d",ratingType);
            binding.questionText.setText(currentQuestion.getText());

            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();
            GlideApp
                    .with(this)
                    .load(currentQuestion.getCoverImageUrl()+"l")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(drawableTransitionOptions)
                    .into(binding.coverImage);

            RatingBar ratingBar;

            if (ratingType == HEART_RATING) {
                binding.ratingBarHeart.setVisibility(RatingBar.VISIBLE);
                ratingBar = binding.ratingBarHeart;
            }else if(ratingType == SMILEY_RATING){
                binding.ratingBarSmiley.setVisibility(RatingBar.VISIBLE);
                ratingBar = binding.ratingBarSmiley;
            }else{
                binding.ratingBarStar.setVisibility(RatingBar.VISIBLE);
                ratingBar = binding.ratingBarStar;
            }

            ratingBar.setNumStars(currentQuestion.getAnswers().size());
            ratingBar.setStepSize(1.0f);
            ratingBar.setRating(1.0f);
        }

        return binding.getRoot();
    }

}
