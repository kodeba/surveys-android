package com.wk.surveys.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wk.data.entities.Question;
import com.wk.surveys.views.fragments.QuestionTypeChoiceFragment;
import com.wk.surveys.views.fragments.QuestionTypeDropdownFragment;
import com.wk.surveys.views.fragments.QuestionTypeIntroFragment;
import com.wk.surveys.views.fragments.QuestionTypeNpsFragment;
import com.wk.surveys.views.fragments.QuestionTypeOutroFragment;
import com.wk.surveys.views.fragments.QuestionTypeRatingFragment;
import com.wk.surveys.views.fragments.QuestionTypeTextAreaFragment;
import com.wk.surveys.views.fragments.QuestionTypeTextFieldFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by watsaponk on 18/12/2017 AD.
 */

public class QuestionViewPagerAdapter extends SmartFragmentStatePagerAdapter {
    private static final String DISPLAY_TYPE_STAR = "star";
    private static final String DISPLAY_TYPE_HEART = "heart";
    private static final String DISPLAY_TYPE_SMILEY = "smiley";
    private static final String DISPLAY_TYPE_CHOICE = "choice";
    private static final String DISPLAY_TYPE_TEXTAREA = "textarea";
    private static final String DISPLAY_TYPE_TEXTFIELD = "textfield";
    private static final String DISPLAY_TYPE_NPS = "nps";
    private static final String DISPLAY_TYPE_DROPDOWN = "dropdown";
    private static final String DISPLAY_TYPE_INTRO = "intro";
    private static final String DISPLAY_TYPE_OUTRO = "outro";

    private List<Fragment> fragments = new ArrayList<>();

    public QuestionViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public static final class Builder {
        private FragmentManager fragmentManager;
        private List<Fragment> fragments;

        public Builder(@NotNull FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        public Builder setQuestions(@NonNull List<Question> questions) {
            this.fragments = new ArrayList<>();

            for (Question question : questions){
                switch (question.getDisplayType()){
                    case DISPLAY_TYPE_CHOICE :
                        this.fragments.add(QuestionTypeChoiceFragment.newInstance(question));
                        break;
                    case DISPLAY_TYPE_HEART :
                        this.fragments.add(QuestionTypeRatingFragment.newInstance(question, QuestionTypeRatingFragment.HEART_RATING));
                        break;
                    case DISPLAY_TYPE_SMILEY :
                        this.fragments.add(QuestionTypeRatingFragment.newInstance(question, QuestionTypeRatingFragment.SMILEY_RATING));
                        break;
                    case DISPLAY_TYPE_STAR :
                        this.fragments.add(QuestionTypeRatingFragment.newInstance(question, QuestionTypeRatingFragment.STAR_RATING));
                        break;
                    case DISPLAY_TYPE_TEXTAREA :
                        this.fragments.add(QuestionTypeTextAreaFragment.newInstance(question));
                        break;
                    case DISPLAY_TYPE_TEXTFIELD :
                        this.fragments.add(QuestionTypeTextFieldFragment.newInstance(question));
                        break;
                    case DISPLAY_TYPE_INTRO :
                        this.fragments.add(QuestionTypeIntroFragment.newInstance(question));
                        break;
                    case DISPLAY_TYPE_OUTRO:
                        this.fragments.add(QuestionTypeOutroFragment.newInstance(question));
                        break;
                    case DISPLAY_TYPE_NPS :
                        this.fragments.add(QuestionTypeNpsFragment.newInstance(question));
                        break;
                    case DISPLAY_TYPE_DROPDOWN :
                        this.fragments.add(QuestionTypeDropdownFragment.newInstance(question));
                        break;
                    default:
                        break;
                }
            }

            return this;
        }

        public QuestionViewPagerAdapter build() {
            QuestionViewPagerAdapter adapter = new QuestionViewPagerAdapter(fragmentManager);
            adapter.setFragments(this.fragments);
            return adapter;
        }
    }
}
