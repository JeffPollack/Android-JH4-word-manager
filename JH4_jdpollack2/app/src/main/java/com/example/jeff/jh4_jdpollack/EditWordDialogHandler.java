package com.example.jeff.jh4_jdpollack;

/**
 * Created by Jeff on 10/18/2015.
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

public class EditWordDialogHandler extends Dialog
        implements  OnKeyListener, OnClickListener
{
    EditText myEditText;
    FileOperations fileOperations;
    String startingValue;
    int index;

    public EditWordDialogHandler(Context context, int index, String startingValue,
                                 FileOperations fileOperations) {
        super(context);
        this.fileOperations = fileOperations;
        this.startingValue = startingValue;
        this.index = index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify and hit Enter");
        setContentView(R.layout.edit_word_layout);

        myEditText = (EditText)findViewById(R.id.my_edit_word);
        myEditText.setText(startingValue);

        myEditText.setOnKeyListener(this);

        Button b = (Button)findViewById(R.id.cancel_edit_word);
        b.setOnClickListener(this);

    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                String newValue = myEditText.getText().toString();

                // Callback to MainActivity
                fileOperations.replaceWord( index, newValue);
                dismiss();
                return true;
            }
        return false;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}