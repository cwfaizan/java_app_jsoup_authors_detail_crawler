/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faizanabbas.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Faizan
 */
public class Entrance {
    /*public static void main(String[] args) {
        try{
            
            ExecutorService executor = Executors.newFixedThreadPool(8);//creating a pool of 8 threads  
            
            String fileName = "E:/MS CS/RS DATA/dataset/rs-profile.json";
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/MS CS/RS DATA/dataset/rs-cite.csv"), true), "UTF-8")))
            {
                String line;
                while((line = br.readLine()) != null)
                {
                    JSONObject jsonObj = new JSONObject(line);
                    Runnable worker = new Citations(jsonObj.getInt("id") , jsonObj.getString("title"), writer);  
                    executor.execute(worker);
                }
                writer.flush();
                br.close();
                
                executor.shutdown();  
                while (!executor.isTerminated()) {   }  

                System.out.println("Finished all threads");
                System.out.println("done!");
            }
            catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }*/
}
