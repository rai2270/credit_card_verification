package com.example.card.cardapplication.model.type;

import com.example.card.cardapplication.R;

public class Mastercard extends BaseCardType {
    /**
     * Mastercard setup class. Used those resources to set it up:
     * https://en.wikipedia.org/wiki/Payment_card_number
     * http://gamon.webfactional.com/regexnumericrangegenerator/
     * https://regexr.com/
     */
    public Mastercard() {
        super(R.drawable.mastercard, "^(5[1-5]|(222[1-8][0-9]{2}|2229[0-8][0-9]|22299[0-9]|22[3-9][0-9]{3}|2[3-6][0-9]{4}|27[01][0-9]{3}|2720[0-8][0-9]|27209[0-9]))\\d*",
                16, 16, R.string.cvc, 3);
    }
}