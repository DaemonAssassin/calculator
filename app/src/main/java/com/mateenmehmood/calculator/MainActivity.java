package com.mateenmehmood.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //creating a variable of EditText type bcz we want to use in many methods
    EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning appropriate view to EditText display var from xml
        display = findViewById(R.id.display);

        //setting to display view when someone clicks it the keyboard not pops up
        display.setShowSoftInputOnFocus(false);

        //applying onclick listener to display view
        //So when somebody click it something happens
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");
            }
        });
    }

    //method to update text in EditText view
    public void updateText (String strToAdd) {
        //getting text within the view
        String oldStr = display.getText().toString();

        //logic to add a number b/w any number (otherwise it will be added at the end)
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        //condition to check that if the text in the view is still there
        //so if someone click any button without click that view the should be added with text
        //Also changing the cursor position
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos + 1);

    }

    public void pressClearBTN (View view) {
        display.setText("");
    }

    public void pressParenthesisBTN (View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().charAt(i) == '(')
                openPar += 1;
            if (display.getText().toString().substring(i, i+1).equals(")"))
                closePar += 1;
        }

        if (openPar == closePar || display.getText().toString().substring(textLen - 1, textLen).equals("("))
            updateText("(");
        else if (closePar < openPar & display.getText().toString().charAt(textLen - 1) != '(')
            updateText(")");
        display.setSelection(cursorPos + 1);
    }

    public void pressBackspaceBTN (View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void pressSevenBTN (View view) {
        updateText("7");
    }

    public void pressEightBTN (View view) {
        updateText("8");
    }

    public void pressNineBTN (View view) {
        updateText("9");
    }

    public void pressFourBTN (View view) {
        updateText("4");
    }

    public void pressFiveBTN (View view) {
        updateText("5");
    }

    public void pressSixBTN (View view) {
        updateText("6");
    }

    public void pressOneBTN (View view) {
        updateText("1");
    }

    public void pressTwoBTN (View view) {
        updateText("2");
    }

    public void pressThreeBTN (View view) {
        updateText("3");
    }

    public void pressDivideBTN (View view) {
        updateText("÷");
    }

    public void pressMultiplyBTN (View view) {
        updateText("×");
    }

    public void pressSubtractBTN (View view) {
        updateText("-");
    }

    public void pressAddBTN (View view) {
        updateText("+");
    }

    public void pressEqualsBTN (View view) { }

    public void pressPointBTN (View view) {
        updateText(".");
    }

    public void pressZeroBTN (View view) {
        updateText("0");
    }

    public void pressExponentBTN (View view) {
        updateText("^");
    }


}