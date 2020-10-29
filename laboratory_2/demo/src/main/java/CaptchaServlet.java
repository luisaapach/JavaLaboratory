import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = {"/Captcha"})
public class CaptchaServlet extends HttpServlet {

    public static final int getRandomInteger(final int max, final int min) {
        return ((int) (Math.random() * (max - min))) + min;
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage bufferedImage = new BufferedImage(120, 30,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setColor(Color.WHITE);
        Font currentFont = g2.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g2.setFont(newFont);

        int[] numers = new int[]{getRandomInteger(0,10), getRandomInteger(0,10)};
        String to_draw = String.format("%d + %d = ?",numers[0],numers[1]);
        g2.drawString(to_draw,20,20);
        req.getSession().setAttribute("CaptchaValue", numers[0]+numers[1]);
        resp.setContentType("image/jpg");
        try(OutputStream os = resp.getOutputStream()){
            ImageIO.write(bufferedImage,"jpg",os);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}

