package com.example.dell.caculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CaculatorFragment extends Fragment implements View.OnClickListener {
    TextView mTextViewDisplayNumber;
    TextView mTextViewResult;

    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char PERCENT = '%';

    private final char EQU = 0;
    private double mValue1 = Double.NaN;
    private double mValue2;

    private char ACTION;

    private final String PRE_NAME = "PRE_NAME";
    private final String SAVE_RESULT = "SAVE_RESULT";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_caculator, container, false);

        mTextViewDisplayNumber = rootView.findViewById(R.id.textview_display_number);
        mTextViewResult = rootView.findViewById(R.id.textview_display_result);
        rootView.findViewById(R.id.button0).setOnClickListener(this);
        rootView.findViewById(R.id.button1).setOnClickListener(this);
        rootView.findViewById(R.id.button2).setOnClickListener(this);
        rootView.findViewById(R.id.button3).setOnClickListener(this);
        rootView.findViewById(R.id.button4).setOnClickListener(this);
        rootView.findViewById(R.id.button5).setOnClickListener(this);
        rootView.findViewById(R.id.button6).setOnClickListener(this);
        rootView.findViewById(R.id.button7).setOnClickListener(this);
        rootView.findViewById(R.id.button8).setOnClickListener(this);
        rootView.findViewById(R.id.button9).setOnClickListener(this);
        rootView.findViewById(R.id.buttonSub).setOnClickListener(this);
        rootView.findViewById(R.id.buttonAdd).setOnClickListener(this);
        rootView.findViewById(R.id.buttonMultip).setOnClickListener(this);
        rootView.findViewById(R.id.buttonDiv).setOnClickListener(this);
        rootView.findViewById(R.id.buttonEquals).setOnClickListener(this);
        rootView.findViewById(R.id.buttonAC).setOnClickListener(this);
        rootView.findViewById(R.id.buttonDot).setOnClickListener(this);
        rootView.findViewById(R.id.buttonPercent).setOnClickListener(this);

        //display OptionsMenu
        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        readData();
    }


    private void clear() {  //khi nhấn menu clear
        mValue1 = Double.NaN;
        mValue2 = Double.NaN;
        mTextViewDisplayNumber.setText(null);
        mTextViewResult.setText(null);
    }


    private String removeTrailingZero(String formattingInput) {
        if (!formattingInput.contains(".")) {
            return formattingInput;
        }
        int dotPosition = formattingInput.indexOf(".");
        String newValue = formattingInput.substring(dotPosition, formattingInput.length());
        if (newValue.equals(".0")) {
            return formattingInput.substring(0, dotPosition);
        }
        return formattingInput;
    }

    private void compute() {  //xử lý tính toán

        if (!Double.isNaN(mValue1)) {
            mValue2 = Double.parseDouble(mTextViewDisplayNumber.getText().toString());
            switch (ACTION) {
                case ADDITION:
                    mValue1 = mValue1 + mValue2;
                    break;
                case SUBTRACTION:
                    mValue1 = mValue1 - mValue2;
                    break;
                case MULTIPLICATION:
                    mValue1 = mValue1 * mValue2;
                    break;
                case DIVISION:
                    mValue1 = mValue1 / mValue2;
                    break;
                case PERCENT:
                    mValue1 = (mValue1 + mValue2) / 100;
                    break;
                case EQU:
                    break;
            }


        } else {
            mValue1 = Double.parseDouble(mTextViewDisplayNumber.getText().toString());

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { //xử lý menu

        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //xử lý menu
        switch (item.getItemId()) {
            case R.id.menu_clear:
                clear();
                break;
            case R.id.menu_save:
                writeData();
                onStart();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void writeData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SAVE_RESULT, (long) mValue1);
        editor.apply();
        Toast.makeText(getActivity(), "Save Success fully", Toast.LENGTH_SHORT).show();
        //editor.commit();
    }

    private void readData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        double result = sharedPreferences.getLong(SAVE_RESULT, 1);
        mTextViewDisplayNumber.setText(String.valueOf(result));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "0");
                break;

            case R.id.button1:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "1");
                break;

            case R.id.button2:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "2");
                break;

            case R.id.button3:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "3");
                break;

            case R.id.button4:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "4");
                break;

            case R.id.button5:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "5");
                break;

            case R.id.button6:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "6");
                break;

            case R.id.button7:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "7");
                break;

            case R.id.button8:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "8");
                break;

            case R.id.button9:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + "9");
                break;

            case R.id.buttonDot:
                mTextViewDisplayNumber.setText(mTextViewDisplayNumber.getText().toString() + ".");
                break;

            case R.id.buttonSub:
                compute();
                ACTION = SUBTRACTION;
                mTextViewResult.setText(String.valueOf(mValue1) + "-");
                mTextViewDisplayNumber.setText("");
                break;

            case R.id.buttonAdd:
                compute();
                ACTION = ADDITION;
                mTextViewResult.setText(String.valueOf(mValue1) + "+");
                mTextViewDisplayNumber.setText("");
                break;

            case R.id.buttonMultip:
                compute();
                ACTION = MULTIPLICATION;
                mTextViewResult.setText(String.valueOf(mValue1) + "*");
                mTextViewDisplayNumber.setText("");
                break;

            case R.id.buttonDiv:
                compute();
                ACTION = DIVISION;
                mTextViewResult.setText(String.valueOf(mValue1) + "/");
                mTextViewDisplayNumber.setText("");
                break;

            case R.id.buttonEquals:
                compute();
                ACTION = EQU;
//                resultTextView.setText(removeTrailingZero(resultTextView.getText().toString()
//                        + String.valueOf(value2) + "= "));
                mTextViewDisplayNumber.setText(removeTrailingZero(String.valueOf(mValue1)));
                break;

            case R.id.buttonAC:
                if (mTextViewDisplayNumber.getText().length() > 0) {
                    CharSequence name = mTextViewDisplayNumber.getText().toString();
                    mTextViewDisplayNumber.setText(name.subSequence(0, name.length() - 1));
                } else {
                    clear();
                }
                break;

            case R.id.buttonPercent:
                compute();
                ACTION = PERCENT;
                mTextViewResult.setText(String.valueOf(mValue1) + "%");
                mTextViewDisplayNumber.setText("");
                break;
        }
    }
}
