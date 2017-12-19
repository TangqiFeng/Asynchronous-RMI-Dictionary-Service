package ie.gmit.sw;

import java.io.*;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServiceHandler extends HttpServlet {
    private static long jobNumber = 0;

    //bolocking queue, used to store in-queue
    BlockingQueue<Query> in_queue = new LinkedBlockingDeque<Query>(7);
    //map<taskNumber, result>, used to store out-queue
    Map<String,String> out_queue = new HashMap<>();

    public void init() throws ServletException{
        //The servlet context is the application itself.
        ServletContext ctx = getServletContext();
    }

    /* The doGet() method handles a HTTP GET request. Please note the following very carefully:
	 *   1) The doGet() method is executed in a separate thread. If you instantiate any objects
	 *      inside this method and don't pass them around (ie. encapsulate them), they will be
	 *      thread safe.
	 *   2) Any instance variables like environmentalVariable or class fields like jobNumber will
	 *      are shared by threads and must be handled carefully.
	 *   3) It is standard practice for doGet() to forward the method invocation to doPost() or
	 *      vice-versa.
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Step 1) Write out the MIME type
        response.setContentType("text/html");

        //Step 2) Get a handle on the PrintWriter to write out HTML
        PrintWriter out = response.getWriter();

        //Step 3) Get any submitted form data. These variables are local to this method and thread safe...
        String querytxt = request.getParameter("txtQuery");
        String taskNumber = request.getParameter("frmTaskNumber");

        //Step 4) Process the input and write out the response.
        out.print("<html><head><title>RMI Dictionary Service</title>");
        out.print("</head>");
        out.print("<body>");

        //We could use the following to track asynchronous tasks. Comment it out otherwise...
        if (taskNumber == null){
            taskNumber = new String("T" + jobNumber);
            //Add job to in-queue
            try {
                in_queue.put(new Query(taskNumber,querytxt));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jobNumber++;

            // call a rmi client
            try {
                RMIClient();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            //Check out-queue for finished job with the given taskNumber
            String result = out_queue.get(taskNumber);
            if(result != null){
                out.print("<h1>Dictionary Service</h1>");
                out.print("<p />");
                out.print("<p><b>Response:</b> "+result+"</p>");
                out.print("</body>");
                out.print("</html>");
                out_queue.remove(taskNumber);
                return;
            }

        }

        //Output some stuff at the top of the generated page
        out.print("<h1>Dictionary Service</h1>");
        out.print("<p />");
        out.print("<H3>waiting for response...</H3>");
        out.print("<p>jobID: "+taskNumber+"</p>");

        //We can also dynamically write out a form using hidden form fields. The form itself is not
        //visible in the browser, but the JavaScript below can see it.
        out.print("<form name=\"frmRequestDetails\" action=\"doProcess\">");
        out.print("<input name=\"txtQuery\" type=\"hidden\" value=\"" + querytxt + "\">");
        out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");

        //JavaScript to periodically poll the server for updates (this is ideal for an asynchronous operation)
        out.print("<script>");
        out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 3000);"); //Refresh every 10 seconds
        out.print("</script>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // a method act as a rmi client.
    public void RMIClient() throws Exception{
        //Ask the registry running on localhost and listening in port 1099 for the instance of
        //the FileService object that is bound to the RMI registry with the name fileService.
        DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");

        //get a job from in_queue
        Query query = in_queue.take();
        //Make a remote method invocation to ask for search result
        ds.loadDictionary();
        query.setResult(ds.lookup(query.getQuerytxt()));

        //put this job to out_queue
        out_queue.put(query.getTaskNumber(),query.getResult());
    }



}

//this is the job bean class
class Query{
    private String taskNumber;
    private String querytxt;
    private String result;

    public Query() {
    }

    public Query(String taskNumber, String querytxt) {
        this.taskNumber = taskNumber;
        this.querytxt = querytxt;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getQuerytxt() {
        return querytxt;
    }

    public void setQuerytxt(String querytxt) {
        this.querytxt = querytxt;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
