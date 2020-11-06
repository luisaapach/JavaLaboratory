package Utils;

import java.util.ListResourceBundle;

public class Messages_ro extends ListResourceBundle {
    static final Object[][] contents = {
            {"title","Introdu un cuplu de informatii in Dictionar"},
            {"word","Cuvant"},
            {"definition","Definitie"},
            {"language","Limba"},
            {"captcha","Introdu rezultatul urmatoarei expresii:"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
