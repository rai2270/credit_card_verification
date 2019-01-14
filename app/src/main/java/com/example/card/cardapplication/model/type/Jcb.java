package com.example.card.cardapplication.model.type;

import com.example.card.cardapplication.R;

public class Jcb extends BaseCardType {
    /**
     * Jcb setup class. Used those resources to set it up:
     * https://en.wikipedia.org/wiki/Payment_card_number
     * http://gamon.webfactional.com/regexnumericrangegenerator/
     * https://regexr.com/
     */
    public Jcb() {
        super(R.drawable.jcb, "^(352[89]|35[3-8][0-9])\\d*", 16, 16, R.string.cvv, 3);
    }
}