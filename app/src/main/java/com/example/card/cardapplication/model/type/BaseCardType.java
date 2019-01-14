package com.example.card.cardapplication.model.type;

import java.util.regex.Pattern;

public class BaseCardType {

    private int mIconResourceID;
    private Pattern mPattern;
    private int mMinLen;
    private int mMaxLen;
    private int mSecurityName;
    private int mSecurityLen;

    /**
     *
     * @param iconResourceID image number in resources
     * @param pattern card type pattern
     * @param minLen minimum length of card
     * @param maxLen maximum length of card
     * @param securityName security name
     * @param securityLen security length
     */
    BaseCardType(int iconResourceID,
                 String pattern,
                 int minLen,
                 int maxLen,
                 int securityName,
                 int securityLen) {
        mIconResourceID = iconResourceID;
        mPattern = Pattern.compile(pattern);
        mMinLen = minLen;
        mMaxLen = maxLen;
        mSecurityName = securityName;
        mSecurityLen = securityLen;
    }

    /**
     *
     * @return image number in resources
     */
    public int getIconID() {
        return mIconResourceID;
    }

    /**
     *
     * @return card type pattern to compare
     */
    public Pattern getPattern() {
        return mPattern;
    }

    /**
     *
     * @return minimum length of card
     */
    public int getmMinLen() {
        return mMinLen;
    }

    /**
     *
     * @return maximum length of card
     */
    public int getMaxLen() {
        return mMaxLen;
    }

    /**
     *
     * @return security name
     */
    public int getSecurityName() {
        return mSecurityName;
    }

    /**
     *
     * @return security length
     */
    public int getSecurityLen() {
        return mSecurityLen;
    }
}
