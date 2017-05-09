# Airline launching instruction
<h2>Editing files</h2>

1.	Add string below to Tomcat <strong>server.xml</strong>

<pre>&lt;Connector connectionTimeout="20000" port="8181" protocol="HTTP/1.1" redirectPort="8443"/&gt;</pre>

2.	Add strings below to Tomcat <strong>tomcat-users.xml</strong>
<pre>
&lt;role rolename="manager-script"/&gt;
&lt;user password="admin1" roles="manager-script" username="admin1"/&gt;
</pre>

3.	Add strings below to Maven <strong>settings.xml</strong> in <servers> section
<pre>
&lt;server&gt;
      &lt;id&gt;tomcat&lt;/id&gt;
      &lt;username>admin1&lt;/username&gt;
      &lt;password>admin1&lt;/password&gt;
&lt;/server&gt;
</pre>

<h2>Launch the program</h2>
<ol>
  <li>Launch Tomcat server</li>
  <li>In command line open a root directory of project (this is the directory where contains pom.xml)</li>
  <li>Build project with command <strong>mvn clean install</strong></li>
  <li>Deploy project with command <strong>mvn tomcat7:deploy</strong></li>
  <li>Open browser and enter URL <strong>your_tomcat_server_address/airline</strong></li>
</ol>
