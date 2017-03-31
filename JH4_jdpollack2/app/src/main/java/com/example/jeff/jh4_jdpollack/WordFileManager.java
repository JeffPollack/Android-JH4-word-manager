package com.example.jeff.jh4_jdpollack;

/**
 * Created by Jeff on 10/18/2015.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.preference.PreferenceManager;
import android.util.Log;


public class WordFileManager {

    private Context context;

    public WordFileManager(Context ctx)
    {
        context = ctx;
    }

    private static final String LAST_FILE_NAME_USED="LastFileNameUsed";

    // Fetch my last file
    public  String getMyLastFileName()
    {
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String category = defaultPrefs.getString(LAST_FILE_NAME_USED, null);
        return category;
    }

    // Save off a current fileName
    public void storeMyFileName(String value)
    {
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = defaultPrefs.edit();
        editor.putString(LAST_FILE_NAME_USED, value);
        editor.commit();
    }
    private File getStartingDir()
    {
        return context.getFilesDir();
    }

    public String[] getFileList()
    {
        File f = getStartingDir();
        String[] list = f.list();
        return list;
    }

    public boolean deleteFile(String fileName)
    {
        Log.d("Mine", "deleteFile fileName="+fileName);
        File startingDir = getStartingDir();
        File f = new File(startingDir, fileName);
        return f.delete();
    }

    // Bring in the ArrayList of words that go with my current category
    public ArrayList<String> getMyWords(String fileName)
    {
        ArrayList<String> myWords=new ArrayList<String>();
        Serializable serializable_object = getSerializable(fileName);
        if (serializable_object != null)
            myWords = (ArrayList<String>)serializable_object;
        return myWords;

    }
    public Serializable getSerializable(String fileName)
    {
        Serializable serializable_object=null;
        File startingDir = getStartingDir();

        try {
            Log.d("Mine", "getSerializable file="+fileName);
            File f = new File(startingDir, fileName);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream is = new ObjectInputStream(fis);
            serializable_object = (Serializable)is.readObject();
            is.close();
        }
        catch (ClassNotFoundException e)
        {
            Log.d("Mine", "getSerializable Error: "+ e);
        }
        catch (IOException e) {
            Log.d("Mine", "getSerializable Error: "+ e);
        }
        return serializable_object;

    }

    // Save an ArrayList
    public void saveMyWords(ArrayList<String> myWords, String fileName)
    {
        saveSerializable(myWords, fileName);
    }

    // Save any Serializable object
    public void saveSerializable(Serializable serializable_object, String fileName)
    {
        try {
            Log.d("Mine", "saveSerializable fileName="+fileName);
            File startingDir = getStartingDir();
            File f = new File(startingDir, fileName);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(serializable_object);
            os.close();

        } catch (IOException e) {
            Log.d("Mine", "saveSerializable Error: "+ e);
        }
    }

    // Get a Scanner object to read a raw resource input text file
    public Scanner getScanner(int resource)
    {
        Scanner scanner = null;

        Log.d("Mine", "getScanner resource="+resource);
        try
        {
            InputStream is = context.getResources().openRawResource(resource);
            scanner = new Scanner(is);
        }
        catch (NotFoundException e)
        {
            Log.d("Mine", "getScanner NotFoundException error: "+e);
        }

        return scanner;
    }

    // Get a Scanner object to read a file that exists for this application
    public Scanner getScanner(String fileName)
    {
        File startingDir = getStartingDir();
        Scanner scanner = null;
        try {
            Log.d("Mine", "getScanner file="+fileName);
            File f = new File(startingDir, fileName);
            FileInputStream fis = new FileInputStream(f);
            scanner = new Scanner(fis);
        }
        catch (FileNotFoundException e)
        {
            Log.d("Mine", "getScanner FileNotFoundException Error: "+ e);
        }
        return scanner;
    }

    // Create a PrintStream for writing to a file in this application
    public PrintStream getPrintStream (String fileName)
    {
        File startingDir = getStartingDir();
        PrintStream ps = null;
        try {
            Log.d("Mine", "getPrintStream file="+fileName);
            File f = new File(startingDir, fileName);
            FileOutputStream fos = new FileOutputStream(f);
            ps = new PrintStream(fos);
        }
        catch (FileNotFoundException e)
        {
            Log.d("Mine", "getPrintStream FileNotFoundException Error: "+ e);
        }
        return ps;
    }

}