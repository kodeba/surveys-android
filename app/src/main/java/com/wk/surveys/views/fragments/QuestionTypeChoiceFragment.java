package com.wk.surveys.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wk.data.entities.Answer;
import com.wk.data.entities.Question;
import com.wk.data.entities.UserAnswer;
import com.wk.surveys.GlideApp;
import com.wk.surveys.R;
import com.wk.surveys.databinding.FragmentQuestionTypeChoiceBinding;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTypeChoiceFragment extends BaseQuestionFragment {
    private static final String PICK_TYPE_ANY = "any";

    FragmentQuestionTypeChoiceBinding binding;
    ArrayList<String> choiceList;

    public QuestionTypeChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onNextQuestion() {
        if(currentQuestion == null){
            listener.OnNextQuestion(null,null);
        }else {
            String answer = "";
            if(currentQuestion.getPick().equalsIgnoreCase(PICK_TYPE_ANY)){
                if(binding.answerList.getCheckedItemPosition() != ListView.INVALID_POSITION){
                    answer = choiceList.get(binding.answerList.getCheckedItemPosition());
                }
            }else{
                SparseBooleanArray checked = binding.answerList.getCheckedItemPositions();
                List<String> checkedList = new ArrayList<>();
                for(int i = 0; i < choiceList.size(); i++){
                    if(checked.get(i)){
                        checkedList.add(checkedList.get(i));
                    }
                }

                answer = TextUtils.join(",",checkedList);
            }

            if(currentQuestion.getIsMandatory() && answer.equalsIgnoreCase("")){
                showMadatoryAlertDialog();
            }else {
                userAnswer = new UserAnswer(currentQuestion.getId(), UserAnswer.TYPE_CHOICE, answer);
                listener.OnNextQuestion(currentQuestion.getId(),userAnswer);
            }
        }
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_type_choice, container, false);

        binding.buttonNavigatoin.nextButton.setOnClickListener(v->onNextQuestion());
        binding.buttonNavigatoin.previousButton.setOnClickListener(v->listener.OnBackQuestion());

        // TODO: 19/12/2017 AD create else case for display incorrect question
        if(getArguments()!=null){
            currentQuestion = Parcels.unwrap(getArguments().getParcelable(CURRENT_QUESTION));
            binding.questionText.setText(currentQuestion.getText());

            if(TextUtils.isEmpty(currentQuestion.getHelpText())){
                binding.helpText.setVisibility(TextView.GONE);
            }else{
                binding.helpText.setText(currentQuestion.getHelpText());
            }

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

            Integer layoutType = android.R.layout.simple_list_item_single_choice;
            Integer choiceMode = ListView.CHOICE_MODE_SINGLE;

            if(currentQuestion.getPick().equalsIgnoreCase(PICK_TYPE_ANY)){
                layoutType = android.R.layout.simple_list_item_multiple_choice;
                choiceMode = ListView.CHOICE_MODE_MULTIPLE;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), layoutType, choiceList);
            binding.answerList.setAdapter(adapter);
            binding.answerList.setItemsCanFocus(true);
            binding.answerList.setChoiceMode(choiceMode);
        }

        return binding.getRoot();
    }

}
