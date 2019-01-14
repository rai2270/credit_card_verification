package com.example.card.cardapplication.model;

import android.text.TextUtils;

import com.example.card.cardapplication.model.type.BaseCardType;

import java.util.Date;

public class Card {

    private String mNumber;
    private Date mExpDate;
    private String mSecCode;
    private BaseCardType mType;

    /**
     * setup initial card an unknown.
     */
    public Card() {
        detectCardType("");
    }

    /**
     * setup card with number and detect type.
     */
    public Card(String number) {
        setNumber(number);
    }

    /**
     * find card type based on card number.
     *
     * @param cardNumber card number to detect
     */
    private void detectCardType(String cardNumber) {
        mType = CardTypes.getInstance().detectType(cardNumber);
    }

    /**
     * set card number and detect card type
     *
     * @param number card number to set
     */
    public void setNumber(String number) {
        mNumber = number;
        detectCardType(mNumber);
    }

    /**
     * validation test on card number
     *
     * @return if card is valid
     */
    public boolean validate() {
        boolean validCard;

        if (TextUtils.isEmpty(mNumber) ||
                (mNumber.length() < mType.getmMinLen() || mNumber.length() > mType.getMaxLen())) {
            return false;
        }

        if (!mType.getPattern().matcher(mNumber).matches()) {
            return false;
        }

        validCard = luhnTest(mNumber);
        return validCard;
    }

    /**
     * https://www.rosettacode.org/wiki/Luhn_test_of_credit_card_numbers#Java
     *
     * @param cardNumber card number to validate
     * @return if cardNumber pass luhn validation
     */
    private boolean luhnTest(String cardNumber) {
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(cardNumber).reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            int digit = Character.digit(reverse.charAt(i), 10);
            if (i % 2 == 0) {//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            } else {//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if (digit >= 5) {
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }

    /**
     * @return card type
     */
    public BaseCardType getType() {
        return mType;
    }

}
