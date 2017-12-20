package com.wk.surveys.views.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.wk.data.entities.Question;
import com.wk.data.entities.UserAnswer;
import com.wk.surveys.R;
import com.wk.surveys.events.QuestionViewEventListener;

/**
 * Created by watsaponk on 18/12/2017 AD.
 */

public abstract class BaseQuestionFragment extends Fragment {
    public static final String CURRENT_QUESTION = "current_question";

    protected Question currentQuestion;
    protected QuestionViewEventListener listener;
    protected UserAnswer userAnswer;

    public BaseQuestionFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof QuestionViewEventListener){
            listener = (QuestionViewEventListener) context;
        }else{
            throw new RuntimeException("Activity must implement "+QuestionViewEventListener.class.getSimpleName());
        }
    }

    protected void showMadatoryAlertDialog(){
        new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setMessage(R.string.madatory_question_alert_text)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    public abstract void onNextQuestion();

}
