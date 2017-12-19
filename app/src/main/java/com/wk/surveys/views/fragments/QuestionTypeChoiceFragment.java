package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wk.data.entities.Question;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeChoiceBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeChoiceFragment extends BaseQuestionFragment {


    public QuestionTypeChoiceFragment() {
        // Required empty public constructor
    }

    public static QuestionTypeChoiceFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeChoiceFragment fragment = new QuestionTypeChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuestionTypeChoiceBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_choice, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->listener.OnNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        return binding.getRoot();
    }

}
