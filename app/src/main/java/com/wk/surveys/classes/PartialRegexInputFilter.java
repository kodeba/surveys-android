package com.wk.surveys.classes;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by watsaponk on 19/12/2017 AD.
 */

public class PartialRegexInputFilter implements InputFilter {

    private Pattern mPattern;

    public PartialRegexInputFilter(String pattern){
        mPattern = Pattern.compile(pattern);
    }

    @Override
    public CharSequence filter(CharSequence source,
                               int sourceStart, int sourceEnd,
                               Spanned destination, int destinationStart,
                               int destinationEnd)
    {
        String textToCheck = destination.subSequence(0, destinationStart).
                toString() + source.subSequence(sourceStart, sourceEnd) +
                destination.subSequence(
                        destinationEnd, destination.length()).toString();

        Matcher matcher = mPattern.matcher(textToCheck);

        if(!matcher.matches()){
            if(!matcher.hitEnd()){
                return "";
            }
        }

        return null;
    }

}
