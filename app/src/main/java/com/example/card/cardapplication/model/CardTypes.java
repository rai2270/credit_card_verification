package com.example.card.cardapplication.model;

import android.text.TextUtils;

import com.example.card.cardapplication.model.type.Amex;
import com.example.card.cardapplication.model.type.BaseCardType;
import com.example.card.cardapplication.model.type.Discover;
import com.example.card.cardapplication.model.type.Jcb;
import com.example.card.cardapplication.model.type.Mastercard;
import com.example.card.cardapplication.model.type.DefaultType;
import com.example.card.cardapplication.model.type.Visa;

import java.util.ArrayList;

public class CardTypes {

    static private ArrayList<BaseCardType> mAllCards;

    private DefaultType defaultCardType = new DefaultType();

    private static volatile CardTypes sSoleInstance;

    /**
     *
     * @return instance of CardTypes
     */
    public static CardTypes getInstance() {
        //Double check locking pattern
        if (sSoleInstance == null) { //Check for the first time
            synchronized (CardTypes.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (sSoleInstance == null) sSoleInstance = new CardTypes();
            }
        }
        return sSoleInstance;
    }

    /**
     * setup all cards type
     */
    private CardTypes() {
        mAllCards = new ArrayList<>();
        mAllCards.add(new Visa());
        mAllCards.add(new Mastercard());
        mAllCards.add(new Discover());
        mAllCards.add(new Amex());
        mAllCards.add(new Jcb());
        mAllCards.add(defaultCardType);
    }

    /**
     *
     * @param cardNumber card number to detect
     * @return card type
     */
    public BaseCardType detectType(String cardNumber) {
        if (!TextUtils.isEmpty(cardNumber)) {
            for (BaseCardType type : mAllCards) {
                if (type.getPattern().matcher(cardNumber).matches()) {
                    return type;
                }
            }
        }
        return defaultCardType;
    }
}
