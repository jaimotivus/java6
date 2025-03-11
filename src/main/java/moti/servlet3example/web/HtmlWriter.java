
package moti.servlet3example.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HtmlWriter {
    private PrintWriter writer;
    private String contextPath;
    private String header;
    private String footer;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    ////////////////////////////////////////////////////////////////////////////
    // These are general helper methods to generate HTML page.
    ////////////////////////////////////////////////////////////////////////////
    public HtmlWriter println(String text) {
        writer.println(text);
        return this;
    }

    public HtmlWriter header() {
        if (header != null) {
            writer.println(header);
            return this;
        }
        
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body>");
        return this;
    }

    public HtmlWriter footer() {
        if (footer != null) {
            writer.println(footer);
            return this;
        }
        
        writer.println("<body>");
        writer.println("</html>");
        return this;
    }

    ////////////////////////////////////////////////////////////////////////////
    // These are builder methods that match and generate HTML elements from
    // a Java objects input.
    ////////////////////////////////////////////////////////////////////////////
    public HtmlWriter p(String text) {
        writer.println("<p>" + text + "</p>");
        return this;
    }

    public HtmlWriter h(int level, String text) {
        writer.println("<h" + level + ">" + text + "</h" + level + ">");
        return this;
    }

    public HtmlWriter ul(String... texts) {
        return ul(Arrays.asList(texts));
    }

    public HtmlWriter ul(List<String> texts) {
        writer.println("<ul>");
        for (String text : texts) {
            writer.println("<li>" + text + "</li>");
        }
        writer.println("</ul>");
        return this;
    }

    public HtmlWriter table(Map<?, ?> map) {
        List<List<? extends Object>> rows = new ArrayList<>();
        // rows.add(Utils.list("NAME", "VALUE"));
         for (Map.Entry<?, ?> entry : map.entrySet()) {
        //     rows.add(Utils.list(entry.getKey(), entry.getValue()));
         }
        return table(rows, null, true, true);
    }
    
    public HtmlWriter table(List<List<?>> rows) {
        return table(rows, null, true, true);
    }
            
    public HtmlWriter table(List<List<?>> rows, String id, boolean isFirstRowHeader, boolean showRowIndex) {
        if (id == null)
            id = "" + System.identityHashCode(rows);
        int indexCount = 0;
        int numOfCols = 1;
        Iterator<List<?>> itr = rows.iterator();
        writer.println("<table id='" + id + "' class='dataTable'>");
        
        if (isFirstRowHeader && itr.hasNext()) {
            writer.print("<tr class='tableHeader'>");
                            
            List<?> row = itr.next();
            numOfCols = row.size();
            
            if (showRowIndex) {
                writer.print("<th></th>");
                numOfCols ++;
            }
            for (Object col : row) {
                // writer.print("<th>" + Utils.toString(col) + "</th>");
            }
            writer.println("</tr>");
        }
        
        if (!itr.hasNext()) {
            writer.println("<tr><td colspan='" + numOfCols + "'>No records</td></tr>");
        } else {
            while (itr.hasNext()) {
                indexCount++;
                String evenOddCss = (indexCount % 2 == 0) ? "even" : "odd";
                writer.print("<tr class='" + evenOddCss + " row" + indexCount + "'>");

                // Index Column
                if (showRowIndex) {
                    writer.print("<td>" + indexCount + "</td>");
                }
                
                List<?> row = itr.next();
                for (Object col : row) {
                    // writer.print("<td>" + Utils.toString(col) + "</td>");
                }
                writer.println("</tr>");
            }
        }
        writer.println("</table>");
        writer.println("<!-- End of table id='" + id +"' -->");
        writer.println();
        return this;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // These are extra utitlity methods that help build html partial elements,
    // and thus not returing the HtmlWriter object it self.
    ////////////////////////////////////////////////////////////////////////////
    public String link(String label, String url) {
        return "<a href=\"" + contextPath + url + "\">" + label + "</a>";
    }
}
