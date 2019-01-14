package com.example.card.cardapplication.model.type;

import com.example.card.cardapplication.R;

public class Amex extends BaseCardType {
    /**
     * Amex setup class. Used those resources to set it up:
     * https://en.wikipedia.org/wiki/Payment_card_number
     * http://gamon.webfactional.com/regexnumericrangegenerator/
     * https://regexr.com/
     */
    public Amex() {
        super(R.drawable.americanexpress, "^(34|37)\\d*", 15, 15, R.string.cid, 4);
    }
}
