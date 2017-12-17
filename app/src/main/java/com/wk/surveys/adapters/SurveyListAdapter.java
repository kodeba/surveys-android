package com.wk.surveys.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wk.data.entities.Survey;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by watsaponk on 17/12/2017 AD.
 */

public class SurveyListAdapter extends PagerAdapter {
    private final List<Survey> surveys;
    private final Context context;
    private final LayoutInflater inflater;

    public SurveyListAdapter(Context context) {
        this.context = context;
        this.surveys = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setSurveys(List<Survey> list, Boolean clear){
        if(clear){
            surveys.clear();
        }
        surveys.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return surveys == null ? 0 : surveys.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Survey survey = surveys.get(position);
        View view = inflater.inflate(R.layout.list_item_survey, container, false);
        TextView title = view.findViewById(R.id.survey_title);
        TextView description = view.findViewById(R.id.survey_description);
        ImageView background = view.findViewById(R.id.image_background);

        if(survey!=null){
            Timber.i("adapter %s ",survey.getCoverImageUrl());
            title.setText(survey.getTitle());
            description.setText(survey.getDescription());
            RequestOptions requestOptions = new RequestOptions().centerCrop();
            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();

            GlideApp
                    .with(view)
                    .load(survey.getCoverImageUrl()+"l")
                    .centerCrop()
                    .transition(drawableTransitionOptions)
                    .into(background);
        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(((RelativeLayout)object));
    }
}
