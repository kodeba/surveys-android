package com.wk.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by watsaponk on 20/12/2017 AD.
 */

public class UserAnswer {
    public static final int TYPE_RATING = 0;
    public static final int TYPE_CHOICE = 1;
    public static final int TYPE_NPS = 2;
    public static final int TYPE_DROPDOWN = 3;
    public static final int TYPE_TEXTAREA = 4;
    public static final int TYPE_TEXTFIELD = 5;

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("question_type")
    @Expose
    private Integer questionType;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public UserAnswer(String questionId, Integer questionType, String answer) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.answer = answer;
    }


    public UserAnswer(Long id, String questionId, Integer questionType, String answer, String userId) {
        this.id = id;
        this.questionId = questionId;
        this.questionType = questionType;
        this.answer = answer;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
