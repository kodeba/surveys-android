package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wk.data.entities.Question;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeIntroBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeIntroFragment extends BaseQuestionFragment {


    public QuestionTypeIntroFragment() {
        // Required empty public constructor
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

        FragmentQuestionTypeIntroBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_intro, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->listener.OnNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        return binding.getRoot();
    }

}
