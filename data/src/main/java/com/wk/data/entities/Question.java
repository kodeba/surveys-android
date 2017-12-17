package com.wk.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class Question {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("help_text")
    @Expose
    private String helpText;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("short_text")
    @Expose
    private String shortText;
    @SerializedName("pick")
    @Expose
    private String pick;
    @SerializedName("display_type")
    @Expose
    private String displayType;
    @SerializedName("is_mandatory")
    @Expose
    private Boolean isMandatory;
    @SerializedName("correct_answer_id")
    @Expose
    private String correctAnswerId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("cover_image_url")
    @Expose
    private String coverImageUrl;
    @SerializedName("cover_image_opacity")
    @Expose
    private Double coverImageOpacity;
    @SerializedName("cover_background_color")
    @Expose
    private String coverBackgroundColor;
    @SerializedName("answers")
    @Expose
    private List<Answer> answers;

    public Question(
            String id,
            String text,
            String helpText,
            Integer displayOrder,
            String shortText,
            String pick,
            String displayType,
            Boolean isMandatory,
            String correctAnswerId,
            String imageUrl,
            String coverImageUrl,
            Double coverImageOpacity,
            String coverBackgroundColor
    ) {
        this.id = id;
        this.text = text;
        this.helpText = helpText;
        this.displayOrder = displayOrder;
        this.shortText = shortText;
        this.pick = pick;
        this.displayType = displayType;
        this.isMandatory = isMandatory;
        this.correctAnswerId = correctAnswerId;
        this.imageUrl = imageUrl;
        this.coverImageUrl = coverImageUrl;
        this.coverImageOpacity = coverImageOpacity;
        this.coverBackgroundColor = coverBackgroundColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(String correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Double getCoverImageOpacity() {
        return coverImageOpacity;
    }

    public void setCoverImageOpacity(Double coverImageOpacity) {
        this.coverImageOpacity = coverImageOpacity;
    }

    public String getCoverBackgroundColor() {
        return coverBackgroundColor;
    }

    public void setCoverBackgroundColor(String coverBackgroundColor) {
        this.coverBackgroundColor = coverBackgroundColor;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
