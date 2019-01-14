package com.example.card.cardapplication.model.type;

import com.example.card.cardapplication.R;

public class DefaultType extends BaseCardType {
    /**
     * setup class for DefaultType. Used those resources to set it up:
     * https://en.wikipedia.org/wiki/Payment_card_number
     * http://gamon.webfactional.com/regexnumericrangegenerator/
     * https://regexr.com/
     */
    public DefaultType() {
        super(R.drawable.empty, "\\d+", 12, 19, R.string.cvv, 3);
    }
}
