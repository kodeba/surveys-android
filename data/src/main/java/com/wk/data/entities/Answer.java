package com.wk.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */
@Parcel
public class Answer {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("help_text")
    @Expose
    private String helpText;
    @SerializedName("input_mask_placeholder")
    @Expose
    private String inputMaskPlaceholder;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("display_type")
    @Expose
    private String displayType;
    @SerializedName("response_class")
    @Expose
    private String responseClass;

    @ParcelConstructor
    public Answer(
            String id,
            String questionId,
            String text,
            String helpText,
            String inputMaskPlaceholder,
            Integer displayOrder,
            String displayType,
            String responseClass
    ) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.helpText = helpText;
        this.inputMaskPlaceholder = inputMaskPlaceholder;
        this.displayOrder = displayOrder;
        this.displayType = displayType;
        this.responseClass = responseClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getInputMaskPlaceholder() {
        return inputMaskPlaceholder;
    }

    public void setInputMaskPlaceholder(String inputMaskPlaceholder) {
        this.inputMaskPlaceholder = inputMaskPlaceholder;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(String responseClass) {
        this.responseClass = responseClass;
    }
}
