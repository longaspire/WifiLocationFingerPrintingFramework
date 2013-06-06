<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>WELCOME TO WLFAGUI</title>
</head>
<body>
<center><h1>WIFI Location FingerPrint Administration GUI</h1></center>
<hr>
<i><b>WIFI Location FingerPrint Administration GUI</b> allows you to carry on the project of WLF</i>
<br />
<br />
<hr>
<h2>Initialization</h2>
<ul>
	<li><a href="#">Load Training Data [Once is Enough, DB Operation] </a></li> <!-- LoadTrainingData  --> 
	<li><a href="#">Load Test Data [Once is Enough, DB Operation] </a></li> <!-- LoadTestData -->
	<li><a href="InitializeApplication">Load RadioMap [MEM Operation as Server Start] </a></li>
</ul>
<h2>Operations</h2>
<ul>
	<li>to be described soon.</li>
	<li><a href="CleanDatabase">Click to Clean the Database [be cautious!!!]</a></li> <!-- CleanDatabase -->
</ul>
<h2>Query</h2>
<ul>
	<li><a href="ViewQuery">View all the test Queries</a></li>
	<li><a href="GetQueryReport">Calculate More than 2000 queries in a row With K = 6[be cautious!!!]</a> <!-- GetQueryReport --></li>
	<li><a href="SimulateQuery">Simulate A simple Query</a></li>
	<li>to be described soon.</li>
</ul>
<h2>Result of WKNN</h2>
<ul>
	<li><a href="Query_Result_1.pdf">For K = 1</a></li>
	<li><a href="Query_Result_4.pdf">For K = 4</a></li>
	<li><a href="Query_Result_5.pdf">For K = 5</a></li>
	<li><a href="Query_Result_6.pdf">For K = 6</a></li>
	<li><a href="Query_Result_7.pdf">For K = 7</a></li>
</ul>
<br />
<br />
<br />
<br />
<hr>
<center><span>Copyright@2013 DBLAB ZJU. All Rights Reserved.</span></center>
</body>
</html>