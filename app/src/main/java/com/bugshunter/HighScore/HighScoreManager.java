package com.bugshunter.HighScore;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class HighScoreManager {

    private Context context;
    private File path;
    private File file;

    private String[] names = new String[5];
    private int[] scores = new int[5];

    private int nrMaxOfValues;

    public HighScoreManager(Context context){
        this.context=context;
        path = context.getFilesDir();
        file = new File(path, "bugsHunterScore.txt");

//        names[0] = "Gheorghita";
//        names[1] = "Aurel";
//        names[2] = "Vania";
//
//        scores[0]= 132;
//        scores[1]= 76;
//        scores[2]= 42;
    }

    public String[] getData(){
        String[] returnVal = new String[names.length];

        for (int i=0; i<names.length; i++){
//            if(scores[i]>0){
                returnVal[i] = names[i] + " - " + scores[i];
//            }else{
//                return returnVal;
//            }

        }

        return returnVal;
    }

    public void readData(){
        String data = readFile();
        if(data!=null){

            String[] values;
            values = data.split("#.%.#");

            if(values.length>1){
                for(int i=0; i<values.length; i++){
                    names[i] = values[i].split(":,:")[0];
                    scores[i] = Integer.parseInt(values[i].split(":,:")[1]);
                }
            }


        }
    }

    public void parseNewScore(String name, int score){

        for(int i=0; i<scores.length; i++){
            if(scores[i]<=score){
                for(int j=scores.length-1; j>i; j--){
                    scores[j]=scores[j-1];
                    names[j] = names[j-1];
                }
                scores[i] = score;
                names[i] = name;

                writeToFile();

                return;
            }
        }
    }



    private void writeToFile() {

        String data="";

        for(int i=0; i<nrMaxOfValues; i++){
            data += names[i] + ":,:" + scores[i];
            if(i<nrMaxOfValues-1){
                data += "#.%.#";
            }
        }

        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data.getBytes());
            stream.close();
        } catch (IOException e) {
            System.out.println("Exception: " + "File write failed: " + e.toString());
        }
    }
    private String readFile(){
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (IOException e) {
            System.out.println("Exception: " + "File read failed: " + e.toString());
        }

        return new String(bytes);
    }

}
