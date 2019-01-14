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
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class CardValidateTest {
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
    public void testValidVisaCard() {
        Card card = new Card("4012888888881881");
        boolean valid = card.validate();
        assertEquals("testValidVisaCard", valid, true);
    }

    @Test
    public void testValidAmexCard() {
        Card card = new Card("378282246310005");
        boolean valid = card.validate();
        assertEquals("testValidAmexCard", valid, true);
    }

    @Test
    public void testValidDiscoverCard() {
        Card card = new Card("6011000990139424");
        boolean valid = card.validate();
        assertEquals("testValidDiscoverCard", valid, true);
    }

    @Test
    public void testValidJcbCard() {
        Card card = new Card("3566002020360505");
        boolean valid = card.validate();
        assertEquals("testValidJcbCard", valid, true);
    }

    @Test
    public void testValidMastercardCard() {
        Card card = new Card("5105105105105100");
        boolean valid = card.validate();
        assertEquals("testValidMastercardCard", valid, true);
    }

    @Test
    public void testValidDefaultCard() {
        Card card = new Card("30569309025904");
        boolean valid = card.validate();
        assertEquals("testValidDefaultCard", valid, true);
    }

    @Test
    public void testNotValidCard() {
        Card card = new Card("30569309025911");
        boolean valid = card.validate();
        assertNotEquals("testNotValidCard", valid, true);
    }
}
