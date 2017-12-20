# Asynchronous-RMI-Dictionary-Service

> author: [Tangqi Feng](https://tangqifeng.github.io/)

4th year Distributed System project

> Module: Distributed Systems / 4th Year  
> Lecturer: Patrick Mannion

This project use the Servlet/JSP and Java RMI frameworks to develop a remote, asynchronous dictionary lookup service. A JSP page should provide users with the ability to specify a string which will be checked against the dictionary. 

![rmi](https://user-images.githubusercontent.com/22374434/34222519-bf626528-e5b3-11e7-959b-82faea1defb9.png)

A web client request is placed in a message queue to await processing. Each request should be allocated a job number. The job number should be added to an inQueue (a Map) along with the request string. The servlet handler should return the job number to the client which in turn should poll the server every 10 seconds for a response. When a response is received with a completed task, the result of the dictionary lookup should be displayed in the browser

An interface called DictionaryService exposes a remote method (``` lookup(String s) ```), where s is the string to lookup in the dictionary, and the String returned is either the dictionary definition of s or the text “String not found”. In the DictionaryServiceImpl, before looking up the query string in the dictionary the thread should be put to sleep for a time, i.e. Thread.sleep(1000), to slow the service down and simulate a real asynchronous service.

## How this repository organized

This repository includes:
* RMI project -- [RMIDictionary](https://github.com/TangqiFeng/Asynchronous-RMI-Dictionary-Service/tree/master/RMIDictionary) contains:
  * ServiceSetup.java, RMIServer, get server instances & open the rmi registry (binding to localhost:1099).
  * DictionaryService.java, RMI interface, defines abstration methods.
  * DictionaryServiceImpl.java, RMI remote objects, implements all basic methods in the dictionary-service.
    * lookup(String s), sued for searching work from rmi dictionary server.
    * loadDictionary(), load dictionary, shuold call this method once the server start.
    * addItem/deleteItem/modifyItem, some methods for updating the entries of dictionary.
* Servlet Project -- [RMI_Dictionary_Service](https://github.com/TangqiFeng/Asynchronous-RMI-Dictionary-Service/tree/master/RMI_Dictionary_Service) contains:
  * web.xml, deployment descriptor elements
  * index.jsp, the main page.
  * DictionaryService.java & DictionaryServiceImpl.java, the same content as the file in RMI project
  * ServiceHandler.java, a servlet class handles the job queues, and multiThreading the RMI client for querying words.
  * RMIClientHandler.java, a servlet class handles the operations -- add/delete/modify the words to the dictionary
  
## How to run?

please make sure 
* the Tomcat server is setup correctly before run this repository
* java environment is needed too

clone or download this repository
* 1.use your favorite IDEA(IntelliJ, Eclipse etc.) to run the two projects alternatively.
  
  2.open browser and type http://localhost:8080 to the address bar.
* 1.copy file (job-server.war) to the webapp folder of Tomcat server, and start local tomcat server
  
  2.go to the folder which have dictionary-service.jar, open the terminal, type the command:
  ```bash
  $ java -cp ./dictionary-service.jar ie.gmit.sw.ServiceSetup
  ```
  3.open browser and type http://localhost:8080 to the address bar.
