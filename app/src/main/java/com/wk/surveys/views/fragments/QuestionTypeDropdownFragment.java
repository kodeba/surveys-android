package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wk.data.entities.Answer;
import com.wk.data.entities.Question;
import com.wk.data.entities.UserAnswer;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeDropdownBinding;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeDropdownFragment extends BaseQuestionFragment {

    FragmentQuestionTypeDropdownBinding binding;
    ArrayList<String> choiceList;

    public QuestionTypeDropdownFragment() {
        // Required empty public constructor
    }

    @Override
    public void onNextQuestion() {
        if(currentQuestion == null){
            listener.OnNextQuestion(null,null);
        }else {
            String answer = choiceList.get(binding.dropdown.getSelectedItemPosition());
            listener.OnNextQuestion(currentQuestion.getId(), new UserAnswer(currentQuestion.getId(), UserAnswer.TYPE_DROPDOWN, answer));
        }
    }

    public static QuestionTypeDropdownFragment newInstance(Question question) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_QUESTION, Parcels.wrap(question));
        QuestionTypeDropdownFragment fragment = new QuestionTypeDropdownFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_dropdown, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->onNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        // TODO: 19/12/2017 AD create else case for display incorrect question
        if(getArguments()!=null){
            currentQuestion = Parcels.unwrap(getArguments().getParcelable(CURRENT_QUESTION));

            binding.questionText.setText(currentQuestion.getText());

            DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade();
            GlideApp
                    .with(this)
                    .load(currentQuestion.getCoverImageUrl()+"l")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .transition(drawableTransitionOptions)
                    .into(binding.groupBackground.coverBackgroundImage);

            choiceList = new ArrayList<>();
            for (Answer answer : currentQuestion.getAnswers()){
                choiceList.add(answer.getText());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, choiceList);
            binding.dropdown.setAdapter(adapter);
        }

        return binding.getRoot();
    }

}
