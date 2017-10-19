# Airline launching instruction
<h1><a href="https://tip14tomcat.azurewebsites.net/airline/">Try me here!</a></h1>

<h2>Editing files</h2>

1.	Add strings below to Tomcat <strong>tomcat-users.xml</strong>
<pre>
&lt;role rolename="manager-script"/&gt;
&lt;user password="admin1" roles="manager-script" username="admin1"/&gt;
</pre>

2.	Add strings below to Maven <strong>settings.xml</strong> in <servers> section
<pre>
&lt;server&gt;
      &lt;id&gt;tomcat&lt;/id&gt;
      &lt;username>admin1&lt;/username&gt;
      &lt;password>admin1&lt;/password&gt;
&lt;/server&gt;
</pre>

<h2>Launch the program</h2>
<ol>
  <li>Start Tomcat server</li>
  <li>In command line open a root directory of project (this is the directory where contains pom.xml)</li>
  <li>Build project with command <strong>mvn clean install</strong></li>
  <li>Deploy project with command <strong>mvn tomcat7:deploy</strong></li>
  <li>Open browser and enter URL <strong>your_tomcat_server_address/airline</strong></li>
</ol>
