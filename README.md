# Asynchronous-RMI-Dictionary-Service

> author: [Tangqi Feng](https://tangqifeng.github.io/)

4th year Distributed System project

> Module: Distributed Systems / 4th Year  
> Lecturer: Patrick Mannion

This project use the Servlet/JSP and Java RMI frameworks to develop a remote, asynchronous dictionary lookup service. A JSP page should provide users with the ability to specify a string which will be checked against the dictionary. 

![rmi](https://user-images.githubusercontent.com/22374434/34222519-bf626528-e5b3-11e7-959b-82faea1defb9.png)

A web client request is placed in a message queue to await processing. Each request should be allocated a job number. The job number should be added to an inQueue (a Map) along with the request string. The servlet handler should return the job number to the client which in turn should poll the server every 10 seconds for a response. When a response is received with a completed task, the result of the dictionary lookup should be displayed in the browser

An interface called DictionaryService exposes a remote method (``` lookup(String s) ```), where s is the string to lookup in the dictionary, and the String returned is either the dictionary definition of s or the text “String not found”. In the DictionaryServiceImpl, before looking up the query string in the dictionary the thread should be put to sleep for a time, i.e. Thread.sleep(1000), to slow the service down and simulate a real asynchronous service.

Addtional functionality which allows the client JSP to add/remove/modify entries in the dictionary.

Multithreading functionality, so that multiple RMI clients can make **queries** to the RMI Dictionary Service concurrently. implemented using Java Thread Pools.

## How this repository organized

This repository includes:
* **RMI** project -- [RMIDictionary](https://github.com/TangqiFeng/Asynchronous-RMI-Dictionary-Service/tree/master/RMIDictionary) contains:
  * ServiceSetup.java, RMIServer, get server instances & open the rmi registry (binding to localhost:1099).
  * DictionaryService.java, RMI interface, defines abstration methods.
  * DictionaryServiceImpl.java, RMI remote objects, implements all basic methods in the dictionary-service.
    * lookup(String s), sued for searching work from rmi dictionary server.
    * loadDictionary(), load dictionary, shuold call this method once the server start.
    * addItem/deleteItem/modifyItem, some methods for updating the entries of dictionary.
* **Servlet** Project -- [RMI_Dictionary_Service](https://github.com/TangqiFeng/Asynchronous-RMI-Dictionary-Service/tree/master/RMI_Dictionary_Service) contains:
  * web.xml, deployment descriptor elements
  * index.jsp, the main page.
  * DictionaryService.java & DictionaryServiceImpl.java, the same content as the file in RMI project
  * ServiceHandler.java, a servlet class handles the job queues, and multiThreading the RMI client for querying words.
  * RMIClientHandler.java, a servlet class handles the operations -- add/delete/modify the words to the dictionary
In this project, using LinkedBlockingQueue to handle In_queue jobs and using HashMap to handle the Out_queue jobs.
Once client send request to servlet, check if the request have jobId. if not, generate the job and put it into In_queue, return the jobID. If yes, take this job from In_queue, sending a rmi **thread** (from Threading pool) request to query the word from RMI server. put this job into the Out-queue when get a response from RMI server.

* dictionary-service.jar --> a jar file packaged from the rmi project.
* job-server.war --> a war file packaged from servlet project, which can deployee into tomcat server directly.

By the time, the jsp client will automatically send requests, every 10 mins,, to servlet to check if the job is in Out-queue. if yes, return the result page, if not, continue waiting. 

And, this project also provide **add/delete/modify** word funtions to the dictionary server. RMIClientHandler contains different calls to the RMI server.
  
## How to run?

please make sure 
* the **Tomcat** server is setup correctly before run this repository
* **java** environment is needed too

clone or download this repository
* 1.use your favorite IDEA(IntelliJ, Eclipse etc.) to run the two projects alternatively. (do not forget to add Tomcat server, and connect to the project -- servlet projevt.)
  
  2.open browser and type http://localhost:8080 to the address bar.
* 1.copy file (job-server.war) to the webapp folder of Tomcat server, and start local tomcat server
    * for Linux: go to the bin directory of the Tomcat server, and type
      ```bash
      $ ./startup.sh
      ```
      to start the server.
  
  2.go to the folder which have dictionary-service.jar, open the terminal, type the command:
  ```bash
  $ java -cp ./dictionary-service.jar ie.gmit.sw.ServiceSetup
  ```
  3.open browser and type http://localhost:8080 to the address bar.
  
  
  **NOTE: the example words in the dictionary are list here: https://github.com/TangqiFeng/Asynchronous-RMI-Dictionary-Service/wiki**
