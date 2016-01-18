<center><h1>WELL-BEING APPLICATION</h1></center>
<br/>

<h3>Introduction</h3>
The developed console application allows the final user to take care of its own life status.
It permits to set some goals and track his/her improvements to hit them.
Specifically, the goals which are possibile to set, in this application, are: 
<strong>sleeping hours per day</strong> and the <strong>person’s weight</strong>.

<br/>

<h3>How the application works</h3>
It is a console application. Once you run the program, you have to choose the user you want to login with.
After this first step, you are allowed to manage the health information about the selected user.
<br/>
In particular, with the selected person, you can:

<ul type=”disc”>
  <li><strong>Print life status</strong> information: it prints, in the console,
  the personal information about the user and information related to goals and life status;</li>
  <li><strong>Set sleeping hours</strong> and <strong>verify</strong> if the goal is reached;</li>
  <li><strong>Set weight</strong> and <strong>verify</strong> if the goal is reached;</li>
  <li><strong>Set sleeping hours goal</strong>: it registers a new goal value of sleeping hours;</li>
  <li><strong>Set weight</strong>: it register a new goal value for the weight;</li>
  <li><strong>Close</strong> the application</li>
</ul>

If the goal is reached by the specified user, the application returns back a pretty image 
(taken by <em>Instagram API</em> and chosen among predefinite random tags). 
On the other hand, if the goal is not reached, the application returns back a motivational phrase 
(taken by <em>quotesondesign API</em>). It is used to encourage the user to improve himself and doing better the next time. 
For simplicity, we just consider person who want to get weight.

</br>
<h3>Application architecture</h3>
The application architecture counts five different modules:
<ul type=”disc”>
<li><strong>Local Database Service</strong>: it is based on <strong>SOAP</strong> web service technology. 
It interacts directly with the database. Its methods manipulate information into the database 
(such as: goals, life status, person’s information, etc).
</li>
<li><strong>Adapter Service</strong>: it is based on <strong>REST</strong> web service technology. 
The services in this module, link an application to the outside world in term of data. 
In particular, for this application, it has been developed services which interact with Instagram and Quotesondesign APIs.
</li>
<li><strong>Storage Service</strong>: it is based on <strong>REST</strong> web service technology. 
The services developed in this module filter and merge information form the bottom two modules. 
For instance, I create a service which calls the API (in the Adapter Service) which allows me to get 
the information about a picture. In this module, I filter this information, returning just the URL of 
the interested image.
</li>
<li><strong>Business Logic Service</strong>: it is based on <strong>REST</strong> web service technology. 
This layer is responsible for all kinds of data manipulations, decision making, calculations, etc. 
It receives requests from the UI layer and it gets data from the data layer and processes it to send results back. 
This module is called directly by the UI when the user is going to ask for /GET requests 
(for example, getting information about the user status). In this module application, it has been created a service 
which does the comparison between the goal’s value set by the user and his/her life status value, related to that measurement.
</li>
<li><strong>Process Centric Service</strong>: It is based on <strong>REST</strong> web service technology. 
The services included in this layer serve all requests coming directly from users. 
These are the gateway to all other modules/services in an application context. 
This layer is doing nothing but redirecting a request to a proper underlying service or a set of services. 
In particular, it has been coded a service which calls other four services from the below modules. 
It acts as an orchestration.
</li>
</ul>
<img src="https://github.com/introsde-2015-final-project/UserInterface/blob/master/Schermata%202016-01-13%20alle%2020.36.38.png" width="700">

<br/>
<h3>Tools, services and technologies used</h3>
For the application development, the following tools have been used:
<ul type=”disc”>
<li><strong>Eclipse IDE</strong>: the application has been developed using the Eclipse IDE and, 
as programming language, it has been chosen <strong>Java</strong>.
</li>
<li><strong>SQLite - database</strong>: the database used to interact with the <em>Local Database Service</em> module, 
is the SQLite. 
It is a relational database management system. In contrast to many other database management systems, 
SQLite is not a client–server database engine. Rather, it is embedded into the end program.
</li>
<li><strong>GitHub</strong>: to host the code and services developed, it has been used GitHub.
 It is a Web-based Git repository hosting service. It has been created a repositories’ organization. 
 The source code is available through this link: <em>https://github.com/orgs/introsde-2015-final-project</em> .
</li>
<li><strong>Heroku</strong>: all the services have been deployed (and are hosted) on the Heroku server. 
It is a cloud Platform-as-a-Service (PaaS) supporting several programming languages.
</li>
<li><strong>Wiki page</strong>: APIs documentation is provided to the user through wiki pages. 
You can see the APIs documentation (for each module) here: <em>https://github.com/orgs/introsde-2015-final-project</em> .
</li>
</ul>

All the REST implemented services in each layer are based on the <strong>JSON format</strong> (both input and output). 
It has been decided to use the JSON format since it is both more compact and more readable. 
In transmission it can be “faster” simply because less data are transferred. 
JSON is also the best tool for sharing data. They are stored in arrays and records while XML stores data in trees. 
JSON is also starting to become more popular in web services applications than XML.

</br>
<h3>Conclusion and further improvements</h3>
What it is has been developed in this project, it is a simple application which uses web-services to work. 
It has been designed to run in a console application but, in the future, it can be improved using a better interface. 
For example, we can consider to build a bot (a software application that runs automated tasks over the Internet) 
which uses Telegram. Or, even better, build an interactive web-site which calls the services created.
<br/><br/>
Well-being applications can use also much more external APIs. 
We can think to integrate some of the services with some bracelets which track the life status 
and training activities of people. Some examples are: FitBit, Jawbone Upp, etc.
<br/><br/>
We can consider also the possibility to manage and take under control what the user eat while he/she tries to keep fit. 
For example, we can suggest some food which help him/her to hit some personal goals.
<br/><br/>
We can also think to send him/her real-time notifications (by e-mail, by sms, etc) when he/she hits a goal. 
For example, if the user run for two kilometers, we can notice him by sending a text message. 
In this way, he/she is aware of his/her result.
<br/><br/>
There are huge possibilities to improve a well-being application like this. 
We just need to use creativity and innovation. In this way, we can bring innovative products, 
which can be used by the population, into the market.


