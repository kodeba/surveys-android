package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wk.data.entities.Question;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeOutroBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeOutroFragment extends BaseQuestionFragment {


    public QuestionTypeOutroFragment() {
        // Required empty public constructor
    }

    public static QuestionTypeOutroFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeOutroFragment fragment = new QuestionTypeOutroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuestionTypeOutroBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_outro, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->listener.OnNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());


        return binding.getRoot();
    }

}
