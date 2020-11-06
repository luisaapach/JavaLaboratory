package Utils;

import java.util.ListResourceBundle;

public class Messages_en extends ListResourceBundle {
    static final Object[][] contents = {
            {"title","Give me a Pair for the Dictionary"},
            {"word","Word"},
            {"definition","Definition"},
            {"language","Language"},
            {"captcha","Insert the result for the above expression:"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
