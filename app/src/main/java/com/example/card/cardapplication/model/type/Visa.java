package com.example.card.cardapplication.model.type;

import com.example.card.cardapplication.R;

public class Visa extends BaseCardType {
    /**
     * Visa setup class. Used those resources to set it up:
     * https://en.wikipedia.org/wiki/Payment_card_number
     * http://gamon.webfactional.com/regexnumericrangegenerator/
     * https://regexr.com/
     */
    public Visa() {
        super(R.drawable.visa, "^4\\d*", 16, 16, R.string.cvv, 3);
    }
}
