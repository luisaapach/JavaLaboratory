package Tags;
import Utils.DictionaryPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;

public class DefinitionTag extends SimpleTagSupport {
    private String word="";
    private String language="";

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        ArrayList<DictionaryPair> list_of_words = (ArrayList<DictionaryPair>)request.getSession().getAttribute("Controller_Dictionary");
        boolean language_provided = language.length()!=0;
        String response="";
        for (DictionaryPair pair:list_of_words){
            if(pair.getWord().equalsIgnoreCase(word)){
                if(language_provided) {
                    if (pair.getLanguage().equalsIgnoreCase(language)) {
                        response += String.format("<h2> Definition for word `%s` and language `%s` is : %s</h2>", word, language, pair.getDefinition());
                    }
                }
                else
                {
                    response += String.format("<h2> Definition for word `%s` and language `%s` is : %s</h2>", word, pair.getLanguage(), pair.getDefinition());
                }

            }
        }
        out.print(response);


    }
}
