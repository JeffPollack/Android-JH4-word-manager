package com.example.jeff.jh4_jdpollack;

/**
 * Created by Jeff on 10/18/2015.
 */

public interface FileOperations {
    // Commented out callbacks will be useful for JH4

    public void newFile(String category);
    public void open(String category);
    public void delete(String category);

    public void replaceWord(int index, String value);
}
