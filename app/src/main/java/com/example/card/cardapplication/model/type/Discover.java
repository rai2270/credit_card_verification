package com.example.card.cardapplication.model.type;

import com.example.card.cardapplication.R;

public class Discover extends BaseCardType {
    /**
     * Discover setup class. Used those resources to set it up:
     * https://en.wikipedia.org/wiki/Payment_card_number
     * http://gamon.webfactional.com/regexnumericrangegenerator/
     * https://regexr.com/
     */
    public Discover() {
        super(R.drawable.discover, "^(6011|65|64)\\d*", 16, 16, R.string.cid, 3);
    }
}