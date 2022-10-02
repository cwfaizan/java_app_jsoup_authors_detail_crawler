/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faizanabbas.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mr Faizan Abbas
 */
public class Test {
    public static void main(String[] args) {
        FileInputStream in = null;
        FileOutputStream out = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        ExecutorService executor = Executors.newFixedThreadPool(8);//creating a pool of 8 threads
        try
        {
            in = new FileInputStream("D:/MS CS/RS DATA/dataset/author.json");
            reader = new BufferedReader(new InputStreamReader(in));
            out = new FileOutputStream("D:/MS CS/RS DATA/dataset/author-corpus.csv", true);
            writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject jsonObj = new JSONObject(line);
                Runnable worker = new AuthorProfile(jsonObj.getInt("aid") , jsonObj.getJSONArray("authors").getString(0), writer);  
                executor.execute(worker);
            }
        }
        catch(IOException | JSONException exp)
        {
            exp.printStackTrace();
        }
        finally{
            /*if(in!=null){
                try{
                    in.close();
                    out.close();
                    reader.close();
                    writer.close();
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }*/
        }
    }
}
