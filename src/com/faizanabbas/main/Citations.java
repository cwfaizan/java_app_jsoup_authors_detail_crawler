/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faizanabbas.main;

import java.io.IOException;
import java.io.Writer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Citations implements Runnable {

    private static int count = 0;
    private int pid;
    private String title;
    private Writer writer;
    private StringBuilder sb = new StringBuilder();
    
    public Citations(int pid, String title, Writer writer){
        this.pid = pid;
        this.title = title;
        this.writer = writer;
    }
    
    @Override
    public void run() {
        try
        {
            sb.append(pid);
            sb.append(',');

            String url = "https://scinapse.io/search?query=ali+daud";
    //                    String key = "&btnG=";
            Document doc = Jsoup.connect((url+title)).get();
            Elements links = doc.select("a[href]");
            Integer citation = 0;
            for (Element link : links)
            {
                if(link.text().contains("Citations"))
                    if(Character.isDigit(link.text().charAt(0)))
                    {
                        citation = Integer.valueOf((link.text().split(" ")[0]).trim());
                        sb.append(citation);
                        sb.append('\n');
                        break;
                    }
            }
            writer.write(sb.toString());
            count++;
            if (count == 500)
            {
                writer.flush();
                count = 0;
            }
            System.out.println(pid+" -> "+citation);
        }
        catch(IOException | NumberFormatException exc)
        {
            exc.printStackTrace();
        }
    }
    
}
