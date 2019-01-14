package com.example.card.cardapplication;

import android.text.TextUtils;

import com.example.card.cardapplication.model.Card;
import com.example.card.cardapplication.model.type.Amex;
import com.example.card.cardapplication.model.type.DefaultType;
import com.example.card.cardapplication.model.type.Discover;
import com.example.card.cardapplication.model.type.Jcb;
import com.example.card.cardapplication.model.type.Mastercard;
import com.example.card.cardapplication.model.type.Visa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class CardTypeTest {

    @Before
    public void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
    }

    // Card numbers taken from: https://www.paypalobjects.com/en_GB/vhelp/paypalmanager_help/credit_card_numbers.htm
    @Test
    public void testVisaCardType() {
        Card card = new Card("4012888888881881");
        assertEquals("testVisaCardType", card.getType().getClass(), Visa.class);
    }

    @Test
    public void testAmexCardType() {
        Card card = new Card("378282246310005");
        assertEquals("testAmexCardType", card.getType().getClass(), Amex.class);
    }

    @Test
    public void testJcbCardType() {
        Card card = new Card("3566002020360505");
        assertEquals("testJcbCardType", card.getType().getClass(), Jcb.class);
    }

    @Test
    public void testMastercardCardType() {
        Card card = new Card("5105105105105100");
        assertEquals("testMastercardCardType", card.getType().getClass(), Mastercard.class);
    }

    @Test
    public void testDiscoverCardType() {
        Card card = new Card("6011000990139424");
        assertEquals("testDiscoverCardType", card.getType().getClass(), Discover.class);
    }

    @Test
    public void testDefaultCardType() {
        Card card = new Card("30569309025904");
        assertEquals("testDefaultCardType", card.getType().getClass(), DefaultType.class);
    }

}
