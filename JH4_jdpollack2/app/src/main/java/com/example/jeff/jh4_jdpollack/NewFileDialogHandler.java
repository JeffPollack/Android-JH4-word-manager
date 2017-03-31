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

public class NewFileDialogHandler extends Dialog
        implements  OnKeyListener, OnClickListener
{

    EditText myEditText;
    FileOperations fileOperations;
    public NewFileDialogHandler(Context context, FileOperations fileOperations) {
        super(context);
        this.fileOperations = fileOperations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("New File");
        setContentView(R.layout.new_file);

        myEditText = (EditText)findViewById(R.id.myNewFileEditText);
        myEditText.setOnKeyListener(this);

        Button b = (Button)findViewById(R.id.cancel_new_file);
        b.setOnClickListener(this);

    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                String fileName = myEditText.getText().toString();
                if (!fileName.endsWith(".drw"))
                    fileName += ".drw";  // All drawing files will end with .drw
                fileOperations.newFile(fileName);
                myEditText.setText("");
                dismiss();
                return true;
            }
        return false;
    }

    @Override
    public void onClick(View arg0) {
        dismiss();
    }
}
