package ie.gmit.sw;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;

@WebServlet(name = "RMIClientHandler")
public class RMIClientHandler extends HttpServlet {

    public void init() throws ServletException{
        //The servlet context is the application itself.
        ServletContext ctx = getServletContext();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Step 1) Write out the MIME type
        response.setContentType("text/html");

        //Step 2) Get a handle on the PrintWriter to write out HTML
        PrintWriter out = response.getWriter();

        //Step 3) Get any submitted form data. These variables are local to this method and thread safe...
        String word = request.getParameter("txtWord");
        String definition = request.getParameter("txtDefinition");
        String operation = request.getParameter("rdiOperate");
        Dictionary d = new Dictionary(word,definition);

        //Step 4) Process the input and write out the response.
        out.print("<html><head><title>RMI Dictionary Service</title>");
        out.print("</head>");
        out.print("<body>");

        //handle the add/delete/modify operations
        if (word == null && definition==null){
            //do nothing here, just
        }else {
            String result=null;
            try {
                result = rmiDicOperate(d,operation);
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print("<h1>Dictionary Service</h1>");
            out.print("<p />");
            out.print("<p><b>Response:</b> "+result+"</p>");
            out.print("<p />");
            out.print("<a href=\"/\"><button>Back to main page</button></a>");
            out.print("</body>");
            out.print("</html>");
            return;
        }

        //write a form to the page
        out.print("<h1>Dictionary Service</h1>");
        out.print("<p />");
        out.print("<form action=\"doOperation\">");
        out.print("<label>Word: </label>");
        out.print("<input name=\"txtWord\" type=\"text\" placeholder=\"Enter word here\" >");
        out.print("<br /><br />");
        out.print("<label>Def : </label>");
        out.print("<input name=\"txtDefinition\" type=\"text\" placeholder=\"Enter definition here\">");
        out.print("<br /><br />");
        out.print("<label><input name=\"rdiOperate\" type=\"radio\" value=\"add\"/>add</label>");
        out.print("<br />");
        out.print("<label><input name=\"rdiOperate\" type=\"radio\" value=\"delete\"/>delete</label>");
        out.print("<br />");
        out.print("<label><input name=\"rdiOperate\" type=\"radio\" value=\"modify\"/>modify</label>");
        out.print("<p />");
        out.print("<input type=\"submit\" value=\"Submit\" />");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");
    }

    // a method act as a rmi client adding/deleting/modifying word to dictionary.
    public String rmiDicOperate(Dictionary d,String opt) throws Exception{
        //Ask the registry running on localhost and listening in port 1099 for the instance of
        //the FileService object that is bound to the RMI registry with the name fileService.
        DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");

        //Make a remote method invocation
        if(opt.equals(new String("add"))){
            return (ds.addItem(d.getWord(),d.getDefinition()));
        }else if(opt.equals(new String("delete"))){
            return (ds.deleteItem(d.getWord()));
        }else if(opt.equals(new String("modify"))){
            return (ds.modifyItem(d.getWord(),d.getDefinition()));
        }
        return null;
    }

}
