package Interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {
    private final Logger logger = Logger.getLogger(LoggedInterceptor.class
            .getName());
    private FileHandler fh = null;

    public LoggedInterceptor() {
        //just to make our log file nicer :)
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("D:\\master\\Java\\Laborator8\\MyLogFile_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }
    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {
        logger.info("Entering method: "
                + invocationContext.getMethod().getName() + " in class "
                + invocationContext.getMethod().getDeclaringClass().getName());
        return invocationContext.proceed();
    }
}