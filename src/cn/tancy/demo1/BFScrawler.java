package cn.tancy.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BFScrawler {

    public static Queue<String> queue = new LinkedList<>();
    public static HashSet<String> marked = new HashSet<>();
    public static String regex = "http[s]*://(\\w+\\.)*(\\w+)";

    public static void BfsAlgorithm(String root) throws IOException{

        BufferedReader br = null;
        queue.add(root);
        while(!queue.isEmpty()){
            String crawlerUrl = queue.poll();
            System.out.println("\n==== Site crawler : " + crawlerUrl + " " + "=====");

            if(marked.size() > 100) return;

            boolean ok = false;
            URL url = null;

            while(!ok){
                try{
                    url = new URL(crawlerUrl);
                    br = new BufferedReader(new InputStreamReader(url.openStream()));
                    ok = true;
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                    crawlerUrl = queue.poll();
                    ok = false;
                } catch (IOException e) {
                    e.printStackTrace();
                    crawlerUrl = queue.poll();
                    ok = false;
                }
            }

            StringBuilder sb = new StringBuilder();
            String tmp = null;

            while((tmp = br.readLine()) != null){
                sb.append(tmp);
            }

            tmp = sb.toString();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(tmp);

            while(matcher.find()){
                String w = matcher.group();

                if(!marked.contains(w)){
                    marked.add(w);
                    System.out.println("Site added for crawling :" + w);
                    queue.add(w);
                }
            }


        }

        if(br!=null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showResult(){
        System.out.println("\n\nResult: ");
        System.out.println("web sites crawled :" + marked.size() + "\n");

        for(String s:marked){
            System.out.println("*" + s);
        }
    }

    public static void main(String[] args) {

        try {
            BfsAlgorithm("https://www.jianshu.com/p/70952b51f0c8");
            showResult();
            ExportUrl.exportweburl(marked);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
