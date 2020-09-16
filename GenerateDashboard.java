package rockwell.tasks;

/*import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;





public class GenerateDashboard {
	
	public static StringBuffer htmlReport = new StringBuffer("<!DOCTYPE html><html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>"+
	".collapsible {  background-color: #777;  color: white;  cursor: pointer;  padding: 7px;  width: 100%;  border: none;  text-align: left;  outline: none;  font-size: 15px;}"+
    ".active, .collapsible:hover {  background-color: #555;}"+
    ".collapsible:after {  content: '\\002B';  color: white;  font-weight: bold;  float: right;  margin-left: 5px;}"+
    ".active:after {  content: \"\\2212\";}"+
    ".content {  padding: 0 7px;  max-height: 0;  overflow: hidden;  transition: max-height 0.2s ease-out;  background-color: #f1f1f1;}"+
    " .header {  padding: 8px 12px;  background: #555;  color: #f1f1f1;text-align:center}"+
    ".content {  padding: 7px;}"+
    ".sticky {  position: fixed;  top: 0;  width: 100%}"+
    ".sticky + .content {  padding-top: 102px;} "+
    "* {    box-sizing: border-box;}"+
    ".column {    float: left;    width: 50%;    padding: 10px;     }"+
    ".row:after {    content: \"\";    display: table;    clear: both;}"+
    "iframe{    border:none;    width:400px;    display:none;}"+
    "</style><title> Rockwell Automation Dashboard </title></head><body>");
			
	static BufferedWriter bw;
	

	public Collection getFiles(File dir, String ext)
	{
		return  FileUtils.listFiles(dir, 
				  new RegexFileFilter("^.*\\.("+ext+")"), 
				  DirectoryFileFilter.DIRECTORY
				);
	}
	
	public void closeReport()
	{
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void writeToReport()
{
	
	try {
		bw = new BufferedWriter(new FileWriter(new File("C:\\rockwell\\work\\results\\results\\report.html")));	
		bw.write(htmlReport.toString());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	public static void main(String args[])
	{
		GenerateDashboard gd = new GenerateDashboard();
		htmlReport.append("<div class=\"header\" id=\"myHeader\"><h2>RESULT DASHBOARD</h2></div><div class=\"row\"><div class=\"column\" style=\"background-color:#aaa;\">");
		File[] faFiles = new File("C:\\rockwell\\work\\results\\results").listFiles();
		  for(File file: faFiles){
		      if(file.isDirectory())
		      {
		    	  Collection successfiles=  gd.getFiles(file,"suc");
		    	  Collection failfiles=  gd.getFiles(file,"dif");
		    	  float passper=0;
		    	  double sucfile=successfiles.size();
		    	  double failfile=failfiles.size();
		    	  double tot= sucfile+ failfile;
		    	  if(tot!=0){
		    	   passper = (float)((sucfile*100)/tot);
		    	  }
		    	  
		    	  htmlReport.append("<button class=\"collapsible\"><b>   " + file.getName()+ "       "+passper +"%   "+    "  Total Tests: "+ (successfiles.size()+ failfiles.size()+"</b><span style=\"background-color:green;\">  Passed Test: "+successfiles.size()+"</span></button>" ));		    	  
		    	  htmlReport.append("<div class=\"content\"><p style=\"background-color:green;\">Success Tests:");
		    	  int counter =0;
		    	  for(Iterator<File> _iterator = successfiles.iterator();_iterator.hasNext();){
		    		  String path=_iterator.next().toString();
		    		  counter++;
		    		  String fileName = path.substring(path.lastIndexOf("\\")+1);
		    		  //System.out.println(path +" "+path.substring(path.lastIndexOf("\\")));
		    		  htmlReport.append("<br/><a href=\"javascript:yolla('file:///"+path.replace('\\', '/')+"');\" id= "+"\""+counter+"\">"+fileName+"</a>      " );
		    		  
		    	  }
		    	  htmlReport.append("</p></div>");
		    	  
		    	  htmlReport.append("<button class=\"collapsible\"><b>   " + file.getName()+ "       "+passper +"%   "+    "  Total Tests: "+ (successfiles.size()+ failfiles.size()+"</b><span style=\"background-color:Tomato;\"> Failed Test: "+failfiles.size()+"</span></button>" ));		    	  
		    	  htmlReport.append("<div class=\"content\">");
		    	  htmlReport.append("<p style=\"background-color:red;\">FAILED TESTS " + failfiles.toString() );
		    	  htmlReport.append("</p></div>");
		    	  
		    }
		      
		  } 
		  htmlReport.append("</div><div class=\"column\" id=\"contents\"><div id=\"bilgi\">text to be replaced</div></div></div>");
		  htmlReport.append("<script> var coll = document.getElementsByClassName(\"collapsible\");");
		  htmlReport.append("var i;for (i = 0; i < coll.length; i++) {  coll[i].addEventListener(\"click\", function() {    this.classList.toggle(\"active\");    var content = this.nextElementSibling;    if (content.style.maxHeight){");
		  htmlReport.append("content.style.maxHeight = null; } else {      content.style.maxHeight = content.scrollHeight + \"px\";    }   });}");
		  htmlReport.append("window.onscroll = function() {myFunction()};var header = document.getElementById(\"myHeader\");var sticky = header.offsetTop;");
		  htmlReport.append("function myFunction() {  if (window.pageYOffset > sticky) {    header.classList.add(\"sticky\");  } else {    header.classList.remove(\"sticky\");  }} ");
		  htmlReport.append("var nesne ;if(navigator.appName.search('Microsoft')>-1) { nesne = new ActiveXObject('MSXML2.XMLHTTP'); }else { nesne = new XMLHttpRequest(); }function yolla(filepath) {nesne.open('get', filepath, true); nesne.onreadystatechange= cevap;nesne.send(null);}function cevap() {if(nesne.readyState==4) {alert( nesne.responseText);}}");
		  htmlReport.append("</script></body></html>"); 
		  gd.writeToReport();
    	  gd.closeReport();
	}
}*/


/* ##############--Report is created by Niraj Pandey--###################  */





/*import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;





public class GenerateDashboard {
	
	public static StringBuffer htmlReport = new StringBuffer("<!DOCTYPE html><html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>"+
	".collapsible {  background-color: #777;  color: white;  cursor: pointer;  padding: 7px;  width: 100%;  border: none;  text-align: left;  outline: none;  font-size: 15px;}"+
    ".active, .collapsible:hover {  background-color: #555;}"+
    ".collapsible:after {  content: '\\002B';  color: white;  font-weight: bold;  float: right;  margin-left: 5px;}"+
    ".active:after {  content: \"\\2212\";}"+
    ".content {  padding: 0 7px;  max-height: 0;  overflow: hidden;  transition: max-height 0.2s ease-out;  background-color: #f1f1f1;}"+
    " .header {  padding: 8px 12px;  background: #555;  color: #f1f1f1;text-align:center}"+
    ".content {  padding: 7px;}"+
    ".sticky {  position: fixed;  top: 0;  width: 100%}"+
    ".sticky + .content {  padding-top: 102px;} "+
    "* {    box-sizing: border-box;}"+
    ".column {    float: left;    width: 50%;    padding: 10px;     }"+
    ".row:after {    content: \"\";    display: table;    clear: both;}"+
    "iframe{    border:none;    width:400px;    display:none;}"+
    "</style><title> Rockwell Automation Dashboard </title></head><body>");
			
	static BufferedWriter bw;
	

	public Collection getFiles(File dir, String ext)
	{
		return  FileUtils.listFiles(dir, 
				  new RegexFileFilter("^.*\\.("+ext+")"), 
				  DirectoryFileFilter.DIRECTORY
				);
	}
	
	public void closeReport()
	{
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void writeToReport()
{
	
	try {
		bw = new BufferedWriter(new FileWriter(new File("C:\\rockwell\\work\\results\\results\\report.html")));	
		bw.write(htmlReport.toString());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	public static void main(String args[])
	{
		GenerateDashboard gd = new GenerateDashboard();
		htmlReport.append("<div class=\"header\" id=\"myHeader\"><h2>RESULT DASHBOARD</h2></div><div class=\"row\"><div class=\"column\" style=\"background-color:#aaa;\">");
		File[] faFiles = new File("C:\\rockwell\\work\\results\\results").listFiles();
		  for(File file: faFiles){
		      if(file.isDirectory())
		      {
		    	  Collection successfiles=  gd.getFiles(file,"suc");
		    	  Collection failfiles=  gd.getFiles(file,"dif");
		    	  float passper=0;
		    	  double sucfile=successfiles.size();
		    	  double failfile=failfiles.size();
		    	  double tot= sucfile+ failfile;
		    	  if(tot!=0){
		    	   passper = (float)((sucfile*100)/tot);
		    	  }
		    	  
		    	  htmlReport.append("<button class=\"collapsible\"><b>   " + file.getName()+ "       "+passper +"%   "+    "  Total Tests: "+ (successfiles.size()+ failfiles.size()+"</b><span style=\"background-color:green;\">  Passed Test: "+successfiles.size()+"</span></button>" ));		    	  
		    	  htmlReport.append("<div class=\"content\"><p style=\"background-color:green;\">Success Tests:");
		    	  int counter =0;
		    	  for(Iterator<File> _iterator = successfiles.iterator();_iterator.hasNext();){
		    		  String path=_iterator.next().toString();
		    		  counter++;
		    		  String fileName = path.substring(path.lastIndexOf("\\")+1);
		    		  //System.out.println(path +" "+path.substring(path.lastIndexOf("\\")));
		    		  htmlReport.append("<br/><a href=\"javascript:yolla('file:///"+path.replace('\\', '/')+"');\" id= "+"\""+counter+"\">"+fileName+"</a>      " );
		    		  
		    	  }
		    	  htmlReport.append("</p></div>");
		    	  
		    	  htmlReport.append("<button class=\"collapsible\"><b>   " + file.getName()+ "       "+passper +"%   "+    "  Total Tests: "+ (successfiles.size()+ failfiles.size()+"</b><span style=\"background-color:Tomato;\"> Failed Test: "+failfiles.size()+"</span></button>" ));		    	  
		    	  htmlReport.append("<div class=\"content\">");
		    	  htmlReport.append("<p style=\"background-color:red;\">FAILED TESTS " + failfiles.toString() );
		    	  htmlReport.append("</p></div>");
		    	  
		    }
		      
		  } 
		  htmlReport.append("</div><div class=\"column\" id=\"contents\"><div id=\"bilgi\">text to be replaced</div></div></div>");
		  htmlReport.append("<script> var coll = document.getElementsByClassName(\"collapsible\");");
		  htmlReport.append("var i;for (i = 0; i < coll.length; i++) {  coll[i].addEventListener(\"click\", function() {    this.classList.toggle(\"active\");    var content = this.nextElementSibling;    if (content.style.maxHeight){");
		  htmlReport.append("content.style.maxHeight = null; } else {      content.style.maxHeight = content.scrollHeight + \"px\";    }   });}");
		  htmlReport.append("window.onscroll = function() {myFunction()};var header = document.getElementById(\"myHeader\");var sticky = header.offsetTop;");
		  htmlReport.append("function myFunction() {  if (window.pageYOffset > sticky) {    header.classList.add(\"sticky\");  } else {    header.classList.remove(\"sticky\");  }} ");
		  htmlReport.append("var nesne ;if(navigator.appName.search('Microsoft')>-1) { nesne = new ActiveXObject('MSXML2.XMLHTTP'); }else { nesne = new XMLHttpRequest(); }function yolla(filepath) {nesne.open('get', filepath, true); nesne.onreadystatechange= cevap;nesne.send(null);}function cevap() {if(nesne.readyState==4) {alert( nesne.responseText);}}");
		  htmlReport.append("</script></body></html>"); 
		  gd.writeToReport();
    	  gd.closeReport();
	}
}*/


/* ##############--Report is created by Niraj Pandey--###################  */

/*import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;





public class GenerateDashboard {
	
	public static StringBuffer htmlReport = new StringBuffer("<!DOCTYPE html><html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>"+
	".collapsible {  background-color: #777;  color: white;  cursor: pointer;  padding: 7px;  width: 100%;  border: none;  text-align: left;  outline: none;  font-size: 15px;}"+
    ".active, .collapsible:hover {  background-color: #555;}"+
    ".collapsible:after {  content: '\\002B';  color: white;  font-weight: bold;  float: right;  margin-left: 5px;}"+
    ".active:after {  content: \"\\2212\";}"+
    ".content {  padding: 0 7px;  max-height: 0;  overflow: hidden;  transition: max-height 0.2s ease-out;  background-color: #f1f1f1;}"+
    " .header {  padding: 8px 12px;  background: #555;  color: #f1f1f1;text-align:center}"+
    ".content {  padding: 7px;}"+
    ".sticky {  position: fixed;  top: 0;  width: 100%}"+
    ".sticky + .content {  padding-top: 102px;} "+
    "* {    box-sizing: border-box;}"+
    ".column {    float: left;    width: 50%;    padding: 10px;     }"+
    ".row:after {    content: \"\";    display: table;    clear: both;}"+
    "iframe{    border:none;    width:400px;    display:none;}"+
    "</style><title> Rockwell Automation Dashboard </title></head><body>");
			
	static BufferedWriter bw;
	

	public Collection getFiles(File dir, String ext)
	{
		return  FileUtils.listFiles(dir, 
				  new RegexFileFilter("^.*\\.("+ext+")"), 
				  DirectoryFileFilter.DIRECTORY
				);
	}
	
	public void closeReport()
	{
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void writeToReport()
{
	
	try {
		bw = new BufferedWriter(new FileWriter(new File("C:\\rockwell\\work\\results\\results\\report.html")));	
		bw.write(htmlReport.toString());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	public static void main(String args[])
	{
		GenerateDashboard gd = new GenerateDashboard();
		htmlReport.append("<div class=\"header\" id=\"myHeader\"><h2>RESULT DASHBOARD</h2></div><div class=\"row\"><div class=\"column\" style=\"background-color:#aaa;\">");
		File[] faFiles = new File("C:\\rockwell\\work\\results\\results").listFiles();
		  for(File file: faFiles){
		      if(file.isDirectory())
		      {
		    	  Collection successfiles=  gd.getFiles(file,"suc");
		    	  Collection failfiles=  gd.getFiles(file,"dif");
		    	  float passper=0;
		    	  double sucfile=successfiles.size();
		    	  double failfile=failfiles.size();
		    	  double tot= sucfile+ failfile;
		    	  if(tot!=0){
		    	   passper = (float)((sucfile*100)/tot);
		    	  }
		    	  
		    	  htmlReport.append("<button class=\"collapsible\"><b>   " + file.getName()+ "       "+passper +"%   "+    "  Total Tests: "+ (successfiles.size()+ failfiles.size()+"</b><span style=\"background-color:green;\">  Passed Test: "+successfiles.size()+"</span></button>" ));		    	  
		    	  htmlReport.append("<div class=\"content\"><p style=\"background-color:green;\">Success Tests:");
		    	  int counter =0;
		    	  for(Iterator<File> _iterator = successfiles.iterator();_iterator.hasNext();){
		    		  String path=_iterator.next().toString();
		    		  counter++;
		    		  String fileName = path.substring(path.lastIndexOf("\\")+1);
		    		  //System.out.println(path +" "+path.substring(path.lastIndexOf("\\")));
		    		  htmlReport.append("<br/><a href=\"javascript:yolla('file:///"+path.replace('\\', '/')+"');\" id= "+"\""+counter+"\">"+fileName+"</a>      " );
		    		  
		    	  }
		    	  htmlReport.append("</p></div>");
		    	  
		    	  htmlReport.append("<button class=\"collapsible\"><b>   " + file.getName()+ "       "+passper +"%   "+    "  Total Tests: "+ (successfiles.size()+ failfiles.size()+"</b><span style=\"background-color:Tomato;\"> Failed Test: "+failfiles.size()+"</span></button>" ));		    	  
		    	  htmlReport.append("<div class=\"content\">");
		    	  htmlReport.append("<p style=\"background-color:red;\">FAILED TESTS " + failfiles.toString() );
		    	  htmlReport.append("</p></div>");
		    	  
		    }
		      
		  } 
		  htmlReport.append("</div><div class=\"column\" id=\"contents\"><div id=\"bilgi\">text to be replaced</div></div></div>");
		  htmlReport.append("<script> var coll = document.getElementsByClassName(\"collapsible\");");
		  htmlReport.append("var i;for (i = 0; i < coll.length; i++) {  coll[i].addEventListener(\"click\", function() {    this.classList.toggle(\"active\");    var content = this.nextElementSibling;    if (content.style.maxHeight){");
		  htmlReport.append("content.style.maxHeight = null; } else {      content.style.maxHeight = content.scrollHeight + \"px\";    }   });}");
		  htmlReport.append("window.onscroll = function() {myFunction()};var header = document.getElementById(\"myHeader\");var sticky = header.offsetTop;");
		  htmlReport.append("function myFunction() {  if (window.pageYOffset > sticky) {    header.classList.add(\"sticky\");  } else {    header.classList.remove(\"sticky\");  }} ");
		  htmlReport.append("var nesne ;if(navigator.appName.search('Microsoft')>-1) { nesne = new ActiveXObject('MSXML2.XMLHTTP'); }else { nesne = new XMLHttpRequest(); }function yolla(filepath) {nesne.open('get', filepath, true); nesne.onreadystatechange= cevap;nesne.send(null);}function cevap() {if(nesne.readyState==4) {alert( nesne.responseText);}}");
		  htmlReport.append("</script></body></html>"); 
		  gd.writeToReport();
    	  gd.closeReport();
	}
}*/


/* ##############--Report is created by Niraj Pandey--###################  */



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class GenerateDashboard

{

	public static StringBuffer htmlReport = new StringBuffer("<!DOCTYPE html><html><head><title> Result Dashboard</title><body bgcolor=\"#E7E7E7\"><meta name=\"viewport\" content=\"width=device-width, initial-scale= 1\">" +
			"<style>.collapsible {  background-color:#4B515D ; box-shadow: 5px 10px 18px #263238; font-family: Arial, Helvetica, sans-serif; color: white;  cursor: pointer;  padding:8px;  width: 97%; border-style: groove;  height: 20px;  border-radius: 1px; text-align: left;  outline: none;  font-size: 15px;" +
			"}"+
			".active, .collapsible:hover {  background-color:#263238;}"+
			".content {padding: 0 10px; width: 96%; max-height: 0; font-family: Arial, Helvetica, sans-serif; font-size: 13px; overflow: auto; border-radius: 1px;  border-style: inset; resize: both; border-width: 1px;	transition: max-height 1s ease-out;background-color: #A1A1A1;}"+ "mark {" +
					"    background-color: #4B515D;" +
					"    color: black;" +

					"body {" +
					"  margin: 0;" +

					"}"+

					"}"+  "header {" +
						"    background-color: #4B515D;" +
						"    position: inherit;" +
						"    left: 0;" +
						"    top:  0;" +
						"    padding: 5px;" +
						"    width: 100%;" +
						"    height: 40px;"+
						"    text-align: center;" +
						"    font-size: 12px;" +
						"    color: white;" +
					"}"+

					".footer {" +
					"   position: fixed;" +
					"   left: 0;" +
					"   bottom: 0;" +
					"   width: 100%;" +
					"   background-color: #000000;" +
					"   color: white;" +
					"    height: 35px;"+
					"   text-align: center;" +

					"}"

					+"a {" +
							"color: white;"+
							//"target:_blank;"+
							"text-decoration: none;"+

					"}" +

					"a:hover {" +
					"    color: #000000;" +
					"}"

					+ "}"+

					"nav {" +
					        "    float: left; /* for the Collaps */" +
					        "    width: 40%;" +
					       // "    height: 550px;"+
							"    padding: 8px;" +
							"    border-style: groove;"+
							"    text-align: left;" +
							"    border-radius: 8px;"+
							"	 box-shadow: 5px 10px 18px #888888;"+
							"	font-family: Arial, Helvetica, sans-serif;"+
							"	font-size: 13px;"+

	                  "}" +

					".nav4 {" +
							"    float: left;/* for the DIF file */" +
							"    position: relative;"+
							"    width: 40%;" +
							"    padding: 8px;" +
							//"    top: 50px;"+
							"    text-align: left;" +


						"}"+

					".nav6 {" +
							"    float: left;/* for the SUC file */" +
							"    position: relative;"+
							"    width: 40%;" +
							"    padding: 8px;" +

							"    text-align: left;" +


							"}"+

							".nav1{" +
							"    float: right;" +
							"	 box-shadow: 5px 10px 18px #263238;"+
							"    width: 24.0%;" +
							"    height: 150px; /* for the total test test case om right side */\r\n" +
							"    background: #4B515D;" +
							"    padding: 10px;" +
							"    border-radius: 1px;"+
							"    border-style: groove;"+
							"    text-align: left;" +
							"    color: white;"+
							"    margin-top: 10px;" +
							"    margin-right: 5px;" +
							"	font-family: Arial, Helvetica, sans-serif;"+
							"	font-size: 15px;"+

							"}"+

							".nav2{" +
								"    float: right;" +
								"    width: 50.5%;" +
								"    height: 426px; /* for the total test test case om right side and chart */\r\n" +
								"    background: #ffffff;" +
								"    padding: 5px;" +
								"    border-radius: 1px;"+
								"    border-style: groove;"+
								"	 box-shadow: 5px 10px 18px #263238;"+
								"    margin-top: 1%;" +
								"}"+

							".nav3{" +
								"    float: right;" +
								"    width: 35%;" +
								"    height: 20px; /* for the total test test case om right side */\r\n" +
								"    background: #ccc;" +
								"    padding: 5px;" +
								"    border-radius: 1px;"+
								"    border-style: groove;"+
								"}"+



							".nav5{"+
								"	 left: 0;" +
								"	 width: 40%;" +
								"	height: 25px; /* For the file inside suite */" +
								"	padding: 0px;"+
								"	background: #FF6D64;"+
								"  border-style: groove;"+

									"}"+

							".ul1 {" +
								"    list-style-type: none; /* for the total pass and fail  test case sutite wise */" +
								"    margin: 0;" +
								"    padding: 0;" +
								"    border-style: groove;"+
								"    background-color: #616161;" +
								"}"+


							".li1  {" +
								"    /* for the total pass and fail  test case sutite wise */" +
								"    color: white;" +
								"    text-align: center;" +
								"    padding: 0px;" +
								"    text-decoration: none;" +
								"    border-width: 0.5px;"+
								"	 border-style: hidden;"+
								"    height: 15px;"+
								"}"+

							"#overflowTest {" +

								"    padding: 1%;" +
								"    position: relative;"+
								"    width: auto;" +
								"    height: -webkit-fill-available;" +
								"    overflow: auto;" +
								"    color: #FF7835;" +
								//"    resize: vertical;"+


								"}"+

							".div1{"+

								"    margin-top: 0px;" +
								//"    margin-bottom: 65px;" +
								"	 /* For the file inside suite */" +


								"}"+


".button {\r\n" +
"    background-color: #CE0000;" +
"    float: right;" +
"    border: none;" +
"    color: white;" +
"    padding: 7px 20px;" +
"    text-align: center;" +
"    text-decoration: none;" +
"    display: inline-block;" +
"    font-size: 10px;" +
"	  width: 15%;" +

	"}"+

".button1 {" +
"	  background-color: #01579b;" +
"	  width: 15%;" +
"	  float: right;" +
"	  border: none;"+
"	  color: white;" +
"	  padding: 7px 20px;" +
"	  text-align: center;" +
"	  text-decoration: none;" +
"	  display: inline-block;" +
"	  font-size: 10px;" +

	"}"+

".button4 {" + 
"    background-color:  #9033ff ;" + 
"	 width: 15%;" + 
"	 float: right;" + 
"	 border: none;" + 
"	 color: white;n" + 
"	 padding: 7px 20px;" + 
"	 text-align: center;" + 
"	 text-decoration: none;" + 
"	 display: inline-block;" + 
"	 font-size: 10px;"+ 
"    height: 25px;" +
"	}"+	
".button3 {" + 
"    background-color:  #ff8d33;" + 
"	 width: 15%;" + 
"	 float: right;" + 
"	 border: none;" + 
"	 color: white;n" + 
"	 padding: 7px 20px;" + 
"	 text-align: center;" + 
"	 text-decoration: none;" + 
"	 display: inline-block;" + 
"	 font-size: 10px;"+ 
"    height: 25px;" +
"	}"+

".button2 {" + 
"    background-color: #007B1D;" + 
"	 width: 15%;" + 
"	 float: right;" + 
"	 border: none;" + 
"	 color: white;n" + 
"	 padding: 7px 20px;" + 
"	 text-align: center;" + 
"	 text-decoration: none;" + 
"	 display: inline-block;" + 
"	 font-size: 10px;"+ 
"    height: 25px;" +
"	}"+



							".tooltip {" +
							"    position: relative;" +
							"    display: inline-block;" +

								"}"+

							".tooltip .tooltiptext {" +
							"    visibility: hidden;" +
							"    width: 310px;" +
							//"	height: 2000%"+
							"    background-color: #FFFFFF;" +
							"    color: #fff;" +
							"    text-align: center;" +
							"    border-radius: 6px;" +
							"    padding: 5px 0;\r\n" +
							"    /* Position the tooltip */" +
							"    position: absolute;" +
							"    z-index: 5;" +
							"}" +


							".tooltip:hover .tooltiptext {" +
							"    visibility: visible;" +
							"}"+

	"</style></body></head><body><header><h2><p><mark><font color=\"red\">ROCKWELL</font><font color=\"#B3B3B3\">AUTOMATION</font></mark></h2></header>");
	//+ "<div class=\"footer\"><p>Report V2.1</p></div>");

		static BufferedWriter bw;

		// Start of AutomationTestReport()
		static void AutomationTestReport(File[] arr,int index,int level) throws IOException
			{
    		GenerateDashboard dv = new GenerateDashboard();
    		//String SuiteName = null;
    		// terminate condition
    		if(index == arr.length) {
    			return;
    		}

         		// tabs for internal levels



         		for (int i = 0; i <= level; i++)

        	 	System.out.print("\t");


          		htmlReport.append("<nav><div class=\"content\" >");
          		htmlReport.append("\t");



          		htmlReport.append("<script> var coll = document.getElementsByClassName(\"collapsible\");");
		  		htmlReport.append("var i;for (i = 0; i < coll.length; i++) {  coll[i].addEventListener(\"click\", function() {    this.classList.toggle(\"active\");    var content = this.nextElementSibling;    if (content.style.maxHeight){");
		  		htmlReport.append("content.style.maxHeight = null; } else {      content.style.maxHeight = content.scrollHeight + \"px\";    }   });}</script>");
		  		htmlReport.append("");
		  		htmlReport.append("</body></html>");
		  		dv.writeToReport(maindirpath);

	      		//dv.closeReport();





		  	// for Test files in side suite
		  	if(arr[index].isFile()) {
        	 	//For console output
        	 	//System.out.println(arr[index].getName());

             	//Append on HTML page for report
        	 	String ext = null;

        	 	int i= arr[index].getName().lastIndexOf('.');
        	 	
        	 	if(i>0) {
        		 	ext= arr[index].getName().substring(i+1);
        		 	//System.out.println("EXT-----------------"+ ext);
        	 		}

                	htmlReport.append("</nav>");
                	
                	htmlReport.append("<a id=\"my_link\" href=file:///" +arr[index].toString() + " \ttarget=\"_blank\" >");



                	// if(arr[index].getName().startsWith("ftaDataView") && ext.equalsIgnoreCase("log") )
                	//SuiteName=arr[index].getName();
                	//System.out.println("File name for dif extension*********************"+ SuiteName);



                if(ext.equalsIgnoreCase("log"))
                	htmlReport.append("<nav>"+arr[index].getName()+"<button  class=\"button1\">Log</button></nav>");
                	//htmlReport.append("<nav><nav class=\"tooltip\">"+ arr[index].getName()+ "<span class=\"tooltiptext\" ><iframe src="+arr[index].toString()+"></iframe></span></nav><button  class=\"button1\">Log</button></nav>");


                if(ext.equalsIgnoreCase("dif"))
            		//System.out.println("File name for dif extension*********************"+ arr[index].getName());
                	htmlReport.append("<nav><nav class=\"tooltip\"><font color=\"#CE0000\">"+ arr[index].getName()+"</font><span class=\"tooltiptext\" ><iframe src=file:///"+arr[index].toString()+"></iframe></span></nav><button class=\"button\">Fail Test</button></nav>");


                if(ext.equalsIgnoreCase("suc"))
                	htmlReport.append("<nav>"+ arr[index].getName()+ "<button class=\"button2\">Passed Test</button></nav>");
                	//htmlReport.append("<nav><nav class=\"tooltip\">"+ arr[index].getName()+ "<span class=\"tooltiptext\" ><iframe src=file:///"+arr[index].toString()+"></iframe></span></nav><button class=\"button2\">Passed Test</button></nav>");

                if(ext.equalsIgnoreCase("png"))file:///
                	htmlReport.append("<nav>"+ arr[index].getName()+ "</nav>");
                	//htmlReport.append("<nav><nav class=\"tooltip\">"+ arr[index].getName()+ "<span class=\"tooltiptext\" ><iframe src="+arr[index].toString()+"></iframe></span></nav></nav>");

                    htmlReport.append("</nav>");

                    htmlReport.append("<script> var coll = document.getElementsByClassName(\"collapsible\");");
                    htmlReport.append("var i;for (i = 0; i < coll.length; i++) {  coll[i].addEventListener(\"click\", function() {    this.classList.toggle(\"active\");    var content = this.nextElementSibling;    if (content.style.maxHeight){");
                    htmlReport.append("content.style.maxHeight = null; } else {      content.style.maxHeight = content.scrollHeight + \"px\";    }   });}</script>");
                    htmlReport.append("");
                    dv.writeToReport(maindirpath);
                    dv.closeReport();

          		}//End of if()

         		// for Suite name
         		else if(arr[index].isDirectory())
         		{
         			String suiteTime="";
         			Collection<?> propfile=  dv.getFiles(arr[index],"properties");
         			
         			for(Iterator<File> _iterator = (Iterator<File>) propfile.iterator();_iterator.hasNext();){
  		    		
         				String path=_iterator.next().toString();
         				if(path.contains("testsuite.properties"))
         				{
         					String text;
         					try {
         					      text = new String(Files.readAllBytes(Paths.get(path)));
         					     suiteTime = text.substring(text.indexOf("testsuite.duration")+32,text.length());
         					     System.out.println("Got the suite time " + suiteTime);
         					    } catch (IOException e) {
         					      e.printStackTrace();
         					    }
                            
         				}
         			
         			}
         			
         			Collection<?> successfiles=  dv.getFiles(arr[index],"suc");
         			Collection<?> failfiles=  dv.getFiles(arr[index],"dif");
         			
         			// System.out.println("[" + arr[index].getName() + "]");

         			htmlReport.append("</nav>");
         			htmlReport.append("<nav class=\"collapsible\">");

         			htmlReport.append("<a id=\"my_link\"  href=" + arr[index].toString() + " \ttarget=\"_blank\">");

         			htmlReport.append("<b>" + arr[index].getName() + "</b></a>");

         			htmlReport.append("</nav><div class=\"content\">");
                    if (!(arr[index].getName().equals("screenshots"))){
         			htmlReport.append("<div class=\"collapsible\"> Result Statistics  "+ "<button  class=\"button3\">"+(float)((successfiles.size()*100))/(successfiles.size()+ failfiles.size() ) +"%</button> <button  class=\"button4\"> "+ suiteTime +"</button></div><div class=\"content\">");
         			htmlReport.append("<b><ul class=\"ul1\"><li class=\"li1\"> Suite Log-<a id=\"my_link\"  href=file:///" + arr[index].toString()+"/"+arr[index].getName() +"_run.log"+ " \ttarget=\"_blank\"> "+arr[index].getName()+"</a></li></ul></b>");
         			htmlReport.append("<b><ul class=\"ul1\"><li class=\"li1\"><font color=\"#fafafa\"> Total Tests- "+(successfiles.size()+ failfiles.size() )+ "</font></li></ul></b>");
         			htmlReport.append("<b><ul class=\"ul1\"><li class=\"li1\"><font color=\"#00e676\"> Passed Test- "+successfiles.size()+ "</font></li></ul></b>");
         			htmlReport.append("<b><ul class=\"ul1\"><li class=\"li1\"><font color=\"#CE0000\"> Failed Test- "+failfiles.size()+"</font></li></ul></b>");

         			htmlReport.append("</div>");
                    }
         			htmlReport.append("<script> var coll = document.getElementsByClassName(\"collapsible\");");
         			htmlReport.append("var i;for (i = 0; i < coll.length; i++) {  coll[i].addEventListener(\"click\", function() {    this.classList.toggle(\"active\");    var content = this.nextElementSibling;    if (content.style.maxHeight){");
         			htmlReport.append("content.style.maxHeight = null; } else {      content.style.maxHeight = content.scrollHeight + \"px\";    }   });}</script>");

         			// recursion for sub-directories in side Suite
         			AutomationTestReport(arr[index].listFiles(), 0, level +1);

         			htmlReport.append("</div>");
         			dv.writeToReport(maindirpath);



         			} //End of if()

         			// recursion for Suite directory
         			dv.writeToReport(maindirpath);
         			AutomationTestReport(arr, ++index, level);

     			}//End of AutomationTestReport()

     		public void closeReport()
     			{
     				try {
     					bw.close();
     				} catch (IOException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				}
     			}// End of closeReport()

     		
     		//public  static String maindirpath="C:\\Users\\npandey\\Desktop\\results";
     		public void writeToReport(String maindirpath)
     		{
               
     			try {
     				String filepath=maindirpath+"/newDashboard.html";
     				//bw = new BufferedWriter(new FileWriter(new File("C:\\rockwell\\work\\results\\Automation_Report.html")));
     				//System.out.println("The result would be generated at "+filepath);
     				bw = new BufferedWriter(new FileWriter(new File(filepath)));
     				
     				bw.write(htmlReport.toString());



     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
     		}//End of writeToReport()



     		public Collection<?> getFiles(File dir, String ext)
     		{
     			return  FileUtils.listFiles(dir,
 				  new RegexFileFilter("^.*\\.("+ext+")"),
 				  DirectoryFileFilter.DIRECTORY);

     		}



            private  static String maindirpath;
            private static String startTime;
            private static String endTime;
            private  static String title;
            private static String timerProp;

     		// Main Method
     		public static void main(String[] args) throws IOException
     		{
     			GenerateDashboard dv = new GenerateDashboard();
     			//maindirpath="C:\\Users\\npandey\\Desktop\\results";
     			
     		
     			//**************************************************************************
     			
     			 for (int i = 0; i < args.length; i++ ) {
     			      if (args[i].equalsIgnoreCase("-resultDir")) maindirpath=args[++i];

     			      else if (args[i].equalsIgnoreCase("-starttime")) startTime=args[++i];
     			      else if (args[i].equalsIgnoreCase("-endTime")) endTime=args[++i];
     			      else if (args[i].equalsIgnoreCase("-title")) title=args[++i];
     			      else if (args[i].equalsIgnoreCase("-timerProp")) timerProp=args[++i];
     			    }
     			//***************************************************************************
     			
     			
     			Properties prop = new Properties();
     			FileReader reader = null;

     				
     			    String totalExtime=null;
     				String filename = "C:\\rockwell\\work\\timer.properties";
     				reader=new FileReader(filename);
     				prop.load(reader);
     				Enumeration<?> e = prop.propertyNames();
     				while (e.hasMoreElements()) {
     					String key = (String) e.nextElement();
     					String value = prop.getProperty(key);
     					
     					if(key.contains("comp")) {
     						totalExtime=value;
     					//System.out.println(" Value : " + totalExtime);
     					}
     				}
     			
     			//***************************************************************************
     			
     	     
     	        
     			// File object
     			File maindir = new File(dv.maindirpath);

     			if(maindir.exists() && maindir.isDirectory())
     			{

     				File arr[] = maindir.listFiles();
     				float  PassPercentage;
     				float failPercentage;


     				Collection<?> successfiles=  dv.getFiles(maindir,"suc");
     				Collection<?> failfiles=  dv.getFiles(maindir,"dif");
     				Collection<?> applogfiles=  dv.getFiles(maindir,"text");
     				//----------------------------------------------------------------------------------------------
     				String logfilepath=null;
     				String logfile=null;
     				for(Iterator<File> _iterator = (Iterator<File>) applogfiles.iterator();_iterator.hasNext();){
      		    		
         				logfilepath=_iterator.next().toString();
         				//To-Do include this for multiple logs (multipe Application)
         				if(logfilepath.contains("_log.text"))
         				{
         					
         					logfile=logfilepath;
         					//System.out.println(logfile);
         				}
//         			   if(logfilepath.contains("sjc_ftaDataFlowML_log.text")) {
//         					
//         					logfile=logfilepath;
//         					//System.out.println(logfile);
//         				}
     				}
     				
     				//-----------------------------------------------------------------------------------------------
     				
     				int total1 = successfiles.size()+ failfiles.size();
     				int pass1 = successfiles.size();
     				int fail1 = failfiles.size();
     				float total = successfiles.size()+ failfiles.size();
     				float pass = successfiles.size();
     				float fail = failfiles.size();

     				PassPercentage =(int)((pass/total)*100);
     				failPercentage = (int)((fail/total)*100);



     				htmlReport.append("<b><nav class=\"nav1\"><ul><li id=\"total\" value= "+total1+">"+"<font color=\"#fafafa\">Total tests -"+(successfiles.size()+ failfiles.size() )+
	    			           "</font></li></ul>" + "<ul><li id=\"pass\" value="+pass1+ "><font color=\"#00e676\">Passed Tests -" +successfiles.size()
	    			           +"</font></li></ul>"+ "<ul><li id=\"fail\" value= "+fail1+"><font color=\"#CE0000\">Failed Tests-"+failfiles.size() +"</font></li></ul></nav></b>");
     				htmlReport.append("<b><nav class=\"nav1\"><ul><li> <a href=file:///"+ logfilepath +" \ttarget=\"_blank\"> Application Log</a></li></ul>"+"<ul><li><font color=\"#00e676\"> Success Percentage- "+PassPercentage+  " % </font></li></ul>" + "<ul><li><font color=\"#CE0000\"> Faliure Percentage- "+failPercentage + " %</font> </li></ul>" + "<ul><li> Elapsed Time - " + totalExtime + "</li></ul></nav></b>");
     				
     				
     				//htmlReport.append("<nav class=\"nav1\"><ul><li> Total tests -"+(successfiles.size()+ failfiles.size() )+ "</li></ul>" + "<ul><li> Passed Tests -"+successfiles.size()+ "</li></ul>"+ "<ul><li> Failed Tests-"+failfiles.size() + "</li></ul></nav>");

     				htmlReport.append("<nav id=\"piechart\" class=\"nav2\"></nav>");

     				htmlReport.append("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");

     				htmlReport.append("<script type=\"text/javascript\">\r\n" +
	    	 		"// Load google charts\r\n" +
	    	 		"google.charts.load('current', {'packages':['corechart']});\r\n" +
	    	 		"google.charts.setOnLoadCallback(drawChart);\r\n" +
	    	 		"\r\n" +
	    	 		"// Draw the chart and set the chart values\r\n" +
	    	 		"function drawChart() {\r\n" +
	    	 		"var x = document.getElementById(\"total\").value;"+
	    	 		"var y = document.getElementById(\"pass\").value;"+
	    	 		"var z = document.getElementById(\"fail\").value;"+
	    	 		"  var data = google.visualization.arrayToDataTable([\r\n" +
	    	 		"  ['Task', 'Chart View'],\r\n" +
	    	 		"  ['Total Test', 0],\r\n" +
	    	 		"  ['Failed Tests', z],\r\n" +
	    	 		"  ['Scepd', 0],"+
	    	 		"  ['Passed Tests',y]\r\n" +

	    	 		"]);\r\n" +
	    	 		"\r\n" +
	    	 		"  // Optional; add a title and set the width and height of the chart\r\n" +
	    	 		"  var options = {'title':'Tests Run Result Chart', 'width':580, 'height':400};\r\n" +
	    	 		"\r\n" +
	    	 		"  // Display the chart inside the <div> element with id=\"piechart\"\r\n" +
	    	 		"  var chart = new google.visualization.PieChart(document.getElementById('piechart'));\r\n" +
	    	 		"  chart.draw(data, options);\r\n" +
	    	 		"}" +
	    	 		"</script>");


     				//Calling AutomationTestReport()

     				//dv.writeToReport();
     				htmlReport.append("<div id=\"overflowTest\" class=\"div1\">");
     				AutomationTestReport(arr,1,0);
     				htmlReport.append("</div>");
     				htmlReport.append("</script></body></html>");
     				dv.writeToReport(maindirpath);
     				dv.closeReport();

          } //End of if()

    } //End of main()

}//End of class


