/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lapachitei
 */
public class NewServlet extends HttpServlet {

    private Map<Integer, ArrayList<String>> wordsMap = null;
    private ServletContext context = null;
    @Override 
    public void init() throws ServletException {
       super.init();
       context = getServletContext();
       this.wordsMap = new TreeMap<Integer, ArrayList<String>>();
       BufferedReader reader;
       try{
           reader = new BufferedReader(new FileReader("C:\\Users\\lapachitei\\Documents\\NetBeansProjects\\WebApplication1\\web\\words.txt"));
           String line = reader.readLine();
           while(line!=null){
               line = line.toLowerCase();
               if(!wordsMap.containsKey(line.length())) {
                    wordsMap.put(line.length(), new ArrayList<String>());
               }
               wordsMap.get(line.length()).add(line);
               line = reader.readLine();
           }
           reader.close();
           
       }catch(IOException e){
           Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, e);
           e.printStackTrace();
       }
    }
    
    private static ArrayList<String> printWordsRec(String base, int length, String characters) {
        ArrayList<String> combinations = new ArrayList<String>();
        for (int i = 0; i < characters.length(); i++){
            char c = characters.charAt(i); 
            if (length == 1) {
                combinations.add(base + String.valueOf(c));
            }
            else{
                combinations.addAll(printWordsRec(base+c,length-1,characters));
            }
        }
        return combinations;
    }
    
    public static Comparator<String> sortCombinations = new Comparator<String>() {

	public int compare(String s1, String s2) {

	   int len1 = s1.length();
	   int len2 = s2.length();

	   return (len1>len2?1:(len1==len2?s1.compareTo(s2):-1));
        }
    };
    
    private void Log(HttpServletRequest request){
        context.log("Method - "+request.getMethod() + " Client IP - " + request.getRemoteAddr()+ " UserAgent - "+request.getHeader("User-Agent"));
        context.log("Language - " + request.getHeader("Accept-Language") + " Parameters - " + request.getParameterMap().toString());
    }
    
    private ArrayList<String> SearchForWords(String letters){
            System.out.println(NewServlet.printWordsRec("", letters.length(), letters));
            ArrayList<String> listOfCombinations = new ArrayList<String>();
            for(int i=1;i<=letters.length();i++){
                listOfCombinations.addAll(NewServlet.printWordsRec("", i, letters));
            }
            HashSet hs = new HashSet();
            hs.addAll(listOfCombinations);
            listOfCombinations.clear();
            listOfCombinations.addAll(hs);
//            Collections.sort(listOfCombinations, NewServlet.sortCombinations);
            this.context.log(listOfCombinations.toString());
            
            ArrayList<String> correctWords = new ArrayList<String>();
            for(String el:listOfCombinations){
                if(wordsMap.containsKey(el.length())) {
                    if(wordsMap.get(el.length()).contains(el))
                        correctWords.add(el);
                }
                else
                {
                    this.context.log("Too many letters");
                    break;
                } 
            }
            return correctWords;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.Log(request);
        response.setContentType("text/html;charset=UTF-8");
        String letters = request.getParameter("letters");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloServlet</title>");            
            out.println("</head>");
            out.println("<body>");

            out.println("<h1> Received Letters : " + letters + "</h1>");
            if(letters.length() == 0){
                out.println("<h2 style=\"color:red;\">Expected a valid string</h2>");
                return;
                
            }
            letters = letters.toLowerCase();
            ArrayList<String> correctWords = this.SearchForWords(letters);
            if(correctWords.size()>0){
                out.println("<h2 style=\"color:blue;\">Founded Words:</h2>");
                for (String el:correctWords){
                    out.println("<h2 style=\"color:blue;\">"+el+"</h2>");
                }
            }
            else{
                out.println("<h2 style=\"color:red;\">No words founded</h2>");
            }
//            out.println(correctWords);
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String letters = request.getParameter("letters");
        if(letters.length() == 0){
            context.log("Expected a valid string");
            return;

        }
        letters = letters.toLowerCase();
        System.out.println(context.getRealPath("/") + "postResponse.txt");
        FileWriter writer = new FileWriter(context.getRealPath("/") + "postResponse.txt", true);
        
        ArrayList<String> correctWords = this.SearchForWords(letters);
        writer.write(String.format("Number of valid words for [letters = %s] is %d\n",letters,correctWords.size()));
        for(String el:correctWords){
            writer.write(el);
            writer.write("\n");
        }
        writer.close();
        response.sendRedirect("/WebApplication1/postResponse.txt");
    }
    
}
