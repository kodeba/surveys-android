package com.wk.surveys.views.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wk.data.entities.Question;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeTextAreaBinding;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeTextAreaFragment extends BaseQuestionFragment {

    public QuestionTypeTextAreaFragment() {
        // Required empty public constructor
    }

    public static QuestionTypeTextAreaFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeTextAreaFragment fragment = new QuestionTypeTextAreaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuestionTypeTextAreaBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_text_area, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->listener.OnNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        return binding.getRoot();
    }
}
