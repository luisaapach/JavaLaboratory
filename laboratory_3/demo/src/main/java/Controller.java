import Objects.Dictionary;
import Utils.DictionaryPair;
import Utils.Languages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    Languages supportedLanguages = new Languages();

    public static class ResultCaptchaInvalid extends Exception {
        public ResultCaptchaInvalid(String errorMessage) {
            super(errorMessage);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Imi setez param in request de Supported Languages

        req.setAttribute("supported_languages",supportedLanguages.getLanguages());
        req.getRequestDispatcher("input.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Post din input.jsp cu AddDictionaryParameters
        resp.setContentType("text/html");
        DictionaryPair dictionaryPair=new DictionaryPair();
        dictionaryPair.setWord(req.getParameter("word"));
        dictionaryPair.setDefinition(req.getParameter("definition"));
        dictionaryPair.setLanguage(req.getParameter("language"));

        String captchaResult = req.getParameter("result_captcha");

        Dictionary dictionary;

        // Preiau / creez sesiunea
        HttpSession session = req.getSession();

        ArrayList<DictionaryPair> addedItems = (ArrayList<DictionaryPair>) session.getAttribute("Controller_Dictionary");
        if(addedItems!=null)
            dictionary = new Dictionary(addedItems);
        else
            dictionary = new Dictionary();

        // Resetez cookie-ul
        Cookie selected_language = new Cookie("selected_language", dictionaryPair.getLanguage());
        selected_language.setMaxAge(3600);
        resp.addCookie(selected_language);

        try {
            String expectedResult = Integer.toString((int)session.getAttribute("CaptchaValue"));
            if(!expectedResult.equals(captchaResult)){
                throw new ResultCaptchaInvalid("Invalid result for the Captcha expression!");
            }
            dictionary.addDictionaryPair(dictionaryPair);
            session.setAttribute("Controller_Dictionary", dictionary.getDictionary());
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }

    }
}
