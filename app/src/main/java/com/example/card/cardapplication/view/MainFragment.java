package com.example.card.cardapplication.view;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.card.cardapplication.BuildConfig;
import com.example.card.cardapplication.R;
import com.example.card.cardapplication.model.Card;
import com.example.card.cardapplication.model.type.BaseCardType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static butterknife.OnTextChanged.Callback.BEFORE_TEXT_CHANGED;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    @BindView(R.id.card_number)
    EditText mCarsNumberView;
    @BindView(R.id.card_number_icon)
    ImageView mIcon;
    @BindView(R.id.expiration_date)
    EditText mExpDateView;
    @BindView(R.id.security)
    EditText mSecurityView;
    @BindView(R.id.submit_btn)
    Button mSubmitBtn;

    private Card mCard;
    private int mExpDatePrevLen;
    private boolean mValidCard;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    /**
     * default constructor
     */
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (BuildConfig.PREVENT_SCREENSHOTS) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCard();
    }

    /**
     * start with default/unknown card and setup resources with default image
     */
    private void initCard() {
        mCard = new Card();
        mIcon.setImageResource(mCard.getType().getIconID());
    }

    /**
     * detect card type after user enter a number, and set all related fields for all ui elements
     */
    @OnTextChanged(value = R.id.card_number,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterCardNumberInput(Editable editable) {
        validateCardNumberAndConstraint(editable);
    }

    /**
     * detect card type after user enter a number, and set all related fields for all ui elements
     */
    void validateCardNumberAndConstraint(Editable e) {
        mExecutor.execute(() -> {
            // In "theory" can take time, so run in a thread and results to UI
            mCard.setNumber(mCarsNumberView.getText().toString());

            getActivity().runOnUiThread(() -> {
                // get the card type
                BaseCardType cardType = mCard.getType();

                // set max length based on card type
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(cardType.getMaxLen());
                mCarsNumberView.setFilters(fArray);

                // set the image icon based on the card type
                mIcon.setImageResource(cardType.getIconID());

                // set security length based on the card type
                // set security hint based on the card type
                mSecurityView.setHint(cardType.getSecurityName());
                fArray[0] = new InputFilter.LengthFilter(cardType.getSecurityLen());
                mSecurityView.setFilters(fArray);

                // move focus to exp date based on card exit text length
                int length = mCarsNumberView.getText().toString().trim().length();
                if (length == cardType.getMaxLen()) {
                    mExpDateView.requestFocus(); // auto move to next edittext
                }
            });
        });
    }

    /**
     * detect date after user enter a date, and set all related fields for all ui elements
     */
    @OnTextChanged(value = R.id.expiration_date, callback = BEFORE_TEXT_CHANGED)
    void beforeExpireEtChanged() {
        mExpDatePrevLen = mExpDateView.getText().toString().length();
    }

    /**
     * detect date after user enter a date, and set all related fields for all ui elements
     */
    @OnTextChanged(R.id.expiration_date)
    void autoFixAndMoveToNext() {
        int length = mExpDateView.getText().toString().trim().length();

        if (mExpDatePrevLen <= length && length < 3) {
            int month = Integer.parseInt(mExpDateView.getText().toString());
            if (length == 1 && month >= 2) {
                String autoFixStr = "0" + month + "/";
                mExpDateView.setText(autoFixStr);
                mExpDateView.setSelection(3);
            } else if (length == 2 && month <= 12) {
                String autoFixStr = mExpDateView.getText().toString() + "/";
                mExpDateView.setText(autoFixStr);
                mExpDateView.setSelection(3);
            } else if (length == 2 && month > 12) {
                mExpDateView.setText("1");
                mExpDateView.setSelection(1);
            }
        } else if (length == 5) {
            mSecurityView.requestFocus(); // auto move to next edittext
        }
    }

    /**
     * run validation tests on the card number
     */
    @OnClick(R.id.submit_btn)
    void submit() {
        // hide keyboard while app try to validate card
        hideKeyboard(getActivity());

        // Basic validation test before continue to try and validate card:
        // validate min length for the card
        if (mCarsNumberView.getText().toString().trim().length() < mCard.getType().getmMinLen()) {
            Toast.makeText(getActivity(), getString(R.string.card_number_missing), Toast.LENGTH_LONG).show();
            return;
        }
        // validate date exists
        if (mExpDateView.getText().toString().trim().length() < getResources().getInteger(R.integer.max_date_length)) {
            Toast.makeText(getActivity(), getString(R.string.card_exp_date_missing), Toast.LENGTH_LONG).show();
            return;
        }
        // validate security length
        if (mSecurityView.getText().toString().trim().length() < mCard.getType().getSecurityLen()) {
            Toast.makeText(getActivity(), getString(R.string.card_security_missing), Toast.LENGTH_LONG).show();
            return;
        }

        // don't let the user press the submit again while app try to validate card
        mSubmitBtn.setEnabled(false);
        // validate card. In "theory" can take time, so run in a thread and results to UI
        mExecutor.execute(() -> {
            try {
                mValidCard = mCard.validate();
            } catch (Exception e) {
            } finally {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getActivity(), mValidCard ? getString(R.string.card_valid) : getString(R.string.card_invalid), Toast.LENGTH_LONG).show();
                    // enable back the submit button
                    mSubmitBtn.setEnabled(true);
                });
            }
        });
    }

    /**
     * hide the keyboard
     * @param activity current carivity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
