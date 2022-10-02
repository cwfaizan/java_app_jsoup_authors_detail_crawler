/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faizanabbas.main;

import java.io.BufferedWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Mr Faizan Abbas
 */
public class AuthorProfile  implements Runnable {

    private static int count = 0;
    private int aid;
    private String author;
    private BufferedWriter writer;
    private StringBuilder sb = new StringBuilder();
    
    public AuthorProfile(int aid, String author, BufferedWriter writer){
        this.aid = aid;
        this.author = author;
        this.writer = writer;
    }
    
    @Override
    public void run() {
        try
        {
            sb.append(aid);
            sb.append(',');
            String url = "https://scinapse.io/search?query=";
            Document doc = Jsoup.connect((url+author)).get();
            Elements links = doc.select("a");
            for (Element link : links) {
                if(link.text().toLowerCase().contains(author.toLowerCase()))
                {
                    //System.out.println(link.attr("abs:href"));
                    getData(link.attr("abs:href"));
                    break;
                }
            }
        }
        catch(IOException | NumberFormatException exc)
        {
            exc.printStackTrace();
        }
    }
    
    private void getData(String url){
        try
        {
            Document doc = Jsoup.connect(url).get();
            Element author = doc.select("span.authorShowHeader_username_3avaD").first();
            Element org = doc.select("div.authorShowHeader_affiliation_2sgbV").first();
            Elements links = doc.select("span.authorShowHeader_metricValue_1GGY2");
            
            if(links.size()==3){
                sb.append(author.text()).append(',');
                sb.append(org.text()).append(',');
                sb.append(links.get(0).text()).append(',');
                sb.append(links.get(1).text()).append(',');
                sb.append(links.get(2).text()).append('\n');
                writer.write(sb.toString());
                writer.flush();
                System.out.println(aid+" -> "+author.text());
            }
            count++;
            if (count == 5000)
            {
                writer.flush();
                count = 0;
            }
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }
    
}