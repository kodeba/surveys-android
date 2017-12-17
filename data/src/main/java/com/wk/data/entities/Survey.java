package com.wk.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class Survey {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("cover_image_url")
    @Expose
    private String coverImageUrl;
    @SerializedName("cover_background_color")
    @Expose
    private String coverBackgroundColor;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("active_at")
    @Expose
    private String activeAt;
    @SerializedName("inactive_at")
    @Expose
    private String inactiveAt;
    @SerializedName("survey_version")
    @Expose
    private Integer surveyVersion;
    @SerializedName("short_url")
    @Expose
    private String shortUrl;
    @SerializedName("questions")
    @Expose
    private List<Question> questions;

    public Survey(
            String id,
            String title,
            String description,
            Boolean isActive,
            String coverImageUrl,
            String coverBackgroundColor,
            String type,
            String createdAt,
            String activeAt,
            String inactiveAt,
            Integer surveyVersion,
            String shortUrl
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isActive = isActive;
        this.coverImageUrl = coverImageUrl;
        this.coverBackgroundColor = coverBackgroundColor;
        this.type = type;
        this.createdAt = createdAt;
        this.activeAt = activeAt;
        this.inactiveAt = inactiveAt;
        this.surveyVersion = surveyVersion;
        this.shortUrl = shortUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getCoverBackgroundColor() {
        return coverBackgroundColor;
    }

    public void setCoverBackgroundColor(String coverBackgroundColor) {
        this.coverBackgroundColor = coverBackgroundColor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getActiveAt() {
        return activeAt;
    }

    public void setActiveAt(String activeAt) {
        this.activeAt = activeAt;
    }

    public String getInactiveAt() {
        return inactiveAt;
    }

    public void setInactiveAt(String inactiveAt) {
        this.inactiveAt = inactiveAt;
    }

    public Integer getSurveyVersion() {
        return surveyVersion;
    }

    public void setSurveyVersion(Integer surveyVersion) {
        this.surveyVersion = surveyVersion;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
