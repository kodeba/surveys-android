package com.wk.surveys.events;

import com.wk.data.entities.UserAnswer;

/**
 * Created by watsaponk on 18/12/2017 AD.
 */

public interface QuestionViewEventListener {
    void OnNextQuestion(String questionId, UserAnswer userAnswer);
    void OnBackQuestion();
}
