package com.wk.surveys.models;

/**
 * Created by watsaponk on 19/12/2017 AD.
 */

public class ItemAnswerTextField {
    private String title;
    private String hint;
    private String answer;
    private String filter;

    public ItemAnswerTextField(String title, String hint, String filter, String answer) {
        this.title = title;
        this.hint = hint;
        this.answer = answer;
        this.filter = filter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
