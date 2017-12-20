package com.wk.surveys.views.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.wk.data.entities.Survey;
import com.wk.data.entities.UserAnswer;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.adapters.QuestionViewPagerAdapter;
import com.wk.surveys.databinding.ActivityDetailBinding;
import com.wk.surveys.events.QuestionViewEventListener;
import com.wk.surveys.views.fragments.QuestionTypeRatingFragment;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity implements QuestionViewEventListener {

    private Survey survey;
    private ActivityDetailBinding binding;
    private Map<String, UserAnswer> userAnswerMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Drawable arrowDrawable = new IconicsDrawable(this).icon(GoogleMaterial.Icon.gmd_arrow_back).color(Color.WHITE).actionBar();
        binding.toolbar.setNavigationIcon(arrowDrawable);
        binding.toolbar.setNavigationOnClickListener(v-> finish());

        // TODO: 18/12/2017 AD create else case for hide UI
        if(getIntent().getExtras()!=null){
            survey = Parcels.unwrap(getIntent().getExtras().getParcelable(MainActivity.SELECTED_SURVEY));
            if(survey!=null){
                binding.surveyTitle.setText(survey.getTitle());
                binding.surveyDescription.setText(survey.getDescription());

                DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();
                GlideApp
                        .with(this)
                        .load(survey.getCoverImageUrl()+"l")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .transition(drawableTransitionOptions)
                        .into(binding.coverBackgroundImage);

                QuestionViewPagerAdapter adapter = new QuestionViewPagerAdapter.Builder(getSupportFragmentManager()).setQuestions(survey.getQuestions()).build();

                binding.questionViewpager.setPagingEnabled(false);
                binding.questionViewpager.setOffscreenPageLimit(survey.getQuestions().size());
                binding.questionViewpager.setAdapter(adapter);
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    public void OnNextQuestion(String questionId, UserAnswer userAnswer) {
        if(userAnswer!=null){
            // TODO: 20/12/2017 AD list of user answer allow to keep in local database later 
            userAnswerMap.put(questionId,userAnswer);
        }

        Integer position = binding.questionViewpager.getCurrentItem();
        if(position != (survey.getQuestions().size() -1)){
            viewpagerGoToPosition(position+1);
        }else{
            // TODO: 20/12/2017 AD submit action
            finish();
        }
    }

    @Override
    public void OnBackQuestion() {
        Integer position = binding.questionViewpager.getCurrentItem();
        if(position!=0){
            viewpagerGoToPosition(position-1);
        }
    }

    private void viewpagerGoToPosition(Integer position){
        binding.questionViewpager.setCurrentItem(position);
    }
}
