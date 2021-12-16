package main.client;

import java.util.ArrayList;

public class MessageStorage {

    int order = 0;

    public static class Message {
        public String message;
        public String user;

        public Message(String _user, String _message) {
            message = _message;
            user = _user;
        }
    }

    public static class FileMessage extends Message {
        public String base64_file;
        public String mime;

        public FileMessage(String _user, String _base64_file, String _mime) {
            super(_user, "");
            base64_file = _base64_file;
            mime = _mime;
        }
    }

    ArrayList<Message> messages = new ArrayList<>();

    String template = "\n" +
            "<html>\n" +
            "<head>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            padding: 5px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "%s" +
            "\n" +
            "</body>\n" +
            "</html>";

    public String build_message_html() {
        String html = "";
        for (Message message : messages) {
            html += "<p>";
            html += "<b>" + message.user + ":</b> " + message.message + "</p>";
            if (message instanceof FileMessage) {
                html += "Shared a file: <a href=\"http://" + messages.indexOf(message) + "\">" + "File" + "</a>";
            }
        }
        //System.err.println(String.format(template, html));
        return String.format(template, html);
    }




}
