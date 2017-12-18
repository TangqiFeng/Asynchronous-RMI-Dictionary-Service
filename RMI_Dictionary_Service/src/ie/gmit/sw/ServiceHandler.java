package ie.gmit.sw;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServiceHandler extends HttpServlet {
    private static long jobNumber = 0;

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

        //bolocking queue, used to store in-queue
        BlockingQueue<Query> in_queue = new LinkedBlockingDeque<Query>(7);
        //map<taskNumber, result>, used to store out-queue
        Map<String,String> out_queue = new HashMap<>();

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

        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/poll");
            dispatcher.forward(request,response);
            //Check out-queue for finished job with the given taskNumber

        }

        //Output some stuff at the top of the generated page
        out.print("<h1>Dictionary Service</h1>");
        out.print("<p />");
        out.print("<H3>waiting for response...</H3>");
        out.print("<p>jobID: "+taskNumber+"...</p>");
        out.print("</body>");
        out.print("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
