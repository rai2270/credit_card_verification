package com.example.card.cardapplication;

import android.text.TextUtils;

import com.example.card.cardapplication.model.Card;

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
public class CardTypeSecurityLengthTest {
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
    public void testVisaCardTypeSecurityLen() {
        Card card = new Card("4012888888881881");
        assertEquals("testVisaCardTypeSecurityLen", card.getType().getSecurityLen(), 3);
    }

    @Test
    public void testAmexCardTypeSecurityLen() {
        Card card = new Card("378282246310005");
        assertEquals("testAmexCardTypeSecurityLen", card.getType().getSecurityLen(), 4);
    }

    @Test
    public void testJcbCardTypeSecurityLen() {
        Card card = new Card("3566002020360505");
        assertEquals("testJcbCardTypeSecurityLen", card.getType().getSecurityLen(), 3);
    }

    @Test
    public void testMastercardCardTypeSecurityLen() {
        Card card = new Card("5105105105105100");
        assertEquals("testMastercardCardTypeSecurityLen", card.getType().getSecurityLen(), 3);
    }

    @Test
    public void testDiscoverCardTypeSecurityLen() {
        Card card = new Card("6011000990139424");
        assertEquals("testDiscoverCardTypeSecurityLen", card.getType().getSecurityLen(), 3);
    }

    @Test
    public void testDefaultCardTypeSecurityLen() {
        Card card = new Card("30569309025904");
        assertEquals("testDefaultCardTypeSecurityLen", card.getType().getSecurityLen(), 3);
    }
}
