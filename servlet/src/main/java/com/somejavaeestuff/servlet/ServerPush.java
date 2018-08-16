package com.somejavaeestuff.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class ServerPush extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ServerPush.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PushBuilder pb = req.newPushBuilder();
        if (pb != null) {
            pb.path("images/pic.jpeg")
                    .addHeader("Content-Type", "image/jpeg")
                    .push();
            LOGGER.info("added image");
        }
        try (PrintWriter writer = resp.getWriter()) {
            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<center>");
            html.append("<img src=\"images/pic.jpeg\"><br>");
            html.append("<h2>the image</h2>");
            html.append("</center>");
            html.append("</html>");
            writer.write(html.toString());
        }
        LOGGER.info("sent response");
    }
}
