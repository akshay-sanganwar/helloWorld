/* $Header:  */
/*  */

/*
DESCRIPTION
 <short description of component this file declares/defines>

 PRIVATE CLASSES
 <list of private classes defined - with one-line descriptions>

 NOTES

 */

package rockwell.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.text.SimpleDateFormat;


/**
 *   (File traversal copied from Deltree.java in ANT)
 */

public class QuickSummary /* extends Task */{

  private File startDir;
  private File htmlReport = new File("results.html");
  private File props = new File("results.properties");
  private File[] header = new File[0];
  private File[] footer = new File[0];
  private StringBuffer html = new StringBuffer();
  private StringBuffer data = new StringBuffer();
  private StringBuffer summ_row = new StringBuffer();
  private String summaryHtml = null;
  private String start;
  private String workDir = ".";
  private String title = "Results Dashboard";
  private String onTop = null;
  private String failureSuffix = ".dif";
  private String successSuffix = ".suc";
  private String unknownSuffix = ".txt";
  private String propsSuffix = ".properties";
  private boolean headerDone = false;
  private boolean printToScreen = true;
  private boolean scanUserDir = false;
  private int daysToKeep = 5;
  private boolean failOnly = true;
  private boolean clip = true;
  private boolean compact = true;
  private int passed = 0;
  private int failed = 0;
  private int unknown = 0;
  private int dirLevels = 0;
  private String successP = "";
  private String passedTests = "";
  private String failedTests = "";
  private String failedTestsHtml = "";
  private String unknownTests = "";
  private String unknownTestsHtml = "";
  private String startTime = "";
  private String endTime = "";
  private String tcDisplayName = "";
  private String[] filters = {
	//"response", // weed out httptst dirs
        //"results/results",   
        //weed out summarizer replicated stuff
  };
  private Hashtable emailTable = new Hashtable();

  /** @param dir the root of the tree to be scanned. */
  public void setStartDir(File dir) {
    startDir = dir;
  }

  /** @param report the file name used for the html report */
  public void setReport(File report) {
    htmlReport = report;
  }

  private File[] appendFile(File f, File[] fs) {
    int len = fs.length;
    File[] newfs = new File[len+1];
    for (int i=0;i<len;i++) {
      newfs[i] = fs[i];
    }
    newfs[len]=f;
    return newfs;
  }

  /** @param header the file name used for the html header properties */
  public void setHeader(File header) {
    this.header = appendFile(header,this.header);
  }

  /** @param workDir the dir to use for creating success and failure files */
  public void setWorkDir(String s) {
    workDir = s;
  }

  /** @param s title to use for report */
  public void setTitle(String s) {
    title = s;
  }

  /** @param s marker used to keep current row on top */
  public void setOnTop(String s) {
    onTop = s;
  }

  /** @param failureSuffix the suffix to use for failure indication (default: ".dif") */
  public void setFailureSuffix(String s) {
    failureSuffix = s;
  }

  /** @param successSuffix the suffix to use for success indication (default: ".suc") */
  public void setSuccessSuffix(String s) {
    successSuffix = s;
  }

  /** @param unknownSuffix the suffix to use for unknown indication (default: ".txt") */
  public void setUnknownSuffix(String s) {
    unknownSuffix = s;
  }

  /** @param footer the file name used for the html footer properties */
  public void setFooter(File footer) {
    this.footer = appendFile(footer,this.footer);
  }

  /** @param props the file name used for the results properties */
  public void setProperties(File properties) {
    props = properties;
  }

  /** @param printToScreen set to false to suppress screen outputs (default: true) */
  public void setPrintToScreen(boolean printToScreen) {    //this.printToScreen = printToScreen;
	  this.printToScreen = false; }

   /** @param scanUserDir set to true to scan user dir (default: false) */
  public void setScanUserDir(boolean b) {    scanUserDir = b; }

  /** @param n number of days to keep results */
  public void setDaysToKeep(int n) {    daysToKeep = n; }

  /** @param clip set to false to show whole dir in report (default: true) */
  public void setClip(boolean clip) {    this.clip = clip;  }

  /** @param failOnly set to false to display all tests (default: true) */
  public void setFailOnly(boolean failOnly) {    this.failOnly = failOnly;  }

//  /** @param compact set to false to show "oracle/tipqa/" from dir (compacts only if clip is true) (default: true) */
//  public void setCompact(boolean compact) {
//    this.compact = compact;
//  }

  public void setEndTime(String endtime) {
    this.endTime = endtime;
  }

  public void setStartTime(String starttime) {
    this.startTime = starttime;
  }

  public void setTestCase(String testcase) {
    this.tcDisplayName = testcase;
  }

  /**
   @param numDays number of days to keep user results
   @param keep  strings if found in dir name, then leave alone (example: KEEP)
   @return array of strings which if found in dir should be left alone
  */
  public String[] toLeaveAlone(int numDays, String[] keep) {
        if (daysToKeep == -1) numDays = 0;
        int start = (keep == null ? 0 : keep.length);
	String[] result = new String[start+numDays];
	if (keep != null) {
	  for (int i = 0; i < keep.length; i++) result[i] = keep[i];
	}

	SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
	GregorianCalendar cal = new GregorianCalendar();
	for (int i = 0; i < numDays; i++) {
	  String date = formatter.format(cal.getTime());
	  result[start+i] = date;
	  cal.add(Calendar.DATE,-1);
	}

	return result;
  }

  private void writeToFile(File file, String msg) throws IOException {
    writeToFile(file,msg,true);
  }

  private void writeToFile(File file, String msg, boolean announce) throws IOException {
    if (announce) System.out.println(".... writing to file " + file);
    if (!file.exists()) {
      //System.out.println("Creating file " + file);
      File dir = new File(file.getParent());
      //System.out.println("Dir = " + dir);
      dir.mkdirs();
      file.createNewFile();
    }
    FileOutputStream fout = new FileOutputStream(file);
    fout.write(msg.getBytes());
    fout.close();
  }

  /**
   * Do the work.
   *
   * @exception Exception if the task is not configured correctly or
   * the tree cannot be scanned.
   */
  public void execute() throws Exception {
    if (startDir == null) {
      throw new Exception("startDir attribute must be set!");
    }
    if (scanUserDir) {
	   System.out.println("scanning User Dir");
           scanUserDir();
	} else {
	   System.out.println("scanning Results Dir");
           scanResultsDir();
	}
  }

  public void scanUserDir() throws Exception {
    if (startDir.exists()) {
      if (!startDir.isDirectory()) {
        System.out.println("Please provide a directory as a parameter: unable to scan file : " + startDir.getAbsolutePath());
        System.exit(-1);
      }

      System.out.println("Scanning user directory: " + startDir.getAbsolutePath());
      html.append(getTitle());
      html.append(getHeader());
      html.append("<table align=\"center\" border=\"1\" width=\"70%\"><tr>")
	  .append("<th>Host-Label-TimeRun</th>")
	  .append("<th>Results</th>")
	  .append("</tr>\n");

      start = startDir.toString();

      try {
        scanUserDir(startDir);
        html.append("</table></b><br>&nbsp;"+getFooter()+"\n</body>\n</html>\n");
        if (!html.toString().trim().equals("")) {
	      writeToFile(htmlReport,html.toString(),false);
        }
      } catch (IOException ioe) {
        String msg = "Unable to scan " + startDir.getAbsolutePath();
        //throw new Exception(msg);
      }
    } else {
      System.out.println("directory does not exist : " + startDir);
    }
  }

  public void scanResultsDir() throws Exception {
    if (startDir.exists()) {
      if (!startDir.isDirectory()) {
        System.out.println("Please provide a directory as a parameter: unable to scan file : " + startDir.getAbsolutePath());
        System.exit(-1);
      }

      System.out.println("Scanning: " + startDir.getAbsolutePath());
      //System.out.println("HTML Report is being sent to: " + htmlReport.getAbsolutePath());
      if (printToScreen) System.out.println("% Suc Failed Passed Total   Suite   Difs  Unks");
      html.append(getTitle());
      html.append(getHeader());
      html.append("<table border=\"1\" width=\"100%\"><tr>")
	  .append("<th>Failed</th>")
	  .append("<th>Text Files</th>")
	  .append("<th>Passed</th>")
	  .append("<th>Total</th>")
	  .append("<th>Success</th>")
	  .append("<th>Suite (durations)<br>[stack traces,errors]</th>")
	  .append("<th>Failed files, or<br>HTML Reports</th>")
	  .append("<th>Text Files files</th>")
	  .append("</tr>\n");

      start = startDir.toString();

      try {
        // System.out.println("scanning results dir : " + start);
        scanDir(startDir);
        System.gc();
        html.append(summ_row.toString());
        html.append(data.toString());
        html.append("</table></b><br>&nbsp;"+getFooter()+"\n</body>\n</html>\n");
        if (!html.toString().trim().equals("")) {
	  writeToFile(htmlReport,html.toString(),false);
//	  System.out.println("********************* Got the HTML: *********************\n"+html);
        }
	// write out notifications file
	String emails = "";
	String notifcns = "";
	for (Enumeration e = emailTable.keys(); e.hasMoreElements(); ) {
	  String email = (String)e.nextElement();
	  if (!email.equals("")) {
	    String notify = (String)emailTable.get(email);
	    emails = emails + (emails.equals("") ? "" : ",") + email;
	    notifcns = notifcns+"-"+email+":"+notify;
	  }
	}
	// write out notifications
	if (!emails.equals("")) {
	  System.out.println("Recording suite notifications ...");
	  StringBuffer n = new StringBuffer();
	  n.append("notify.emails:").append(emails).append("\n");
	  for (StringTokenizer st = new StringTokenizer(notifcns,"-"); st.hasMoreTokens();) {
	    n.append(st.nextToken()).append("\n");
	  }
	  writeToFile(new File(workDir+"/notify.properties"),n.toString(),false);
	} else {
	  //System.out.println("No notifications to record");
	}
      } catch (IOException ioe) {
        String msg = "Unable to scan " + startDir.getAbsolutePath();
        throw new Exception(msg);
      }
    } else {
      System.out.println("directory does not exist : " + startDir);
    }
  }

  private boolean skip(String file) {
    for (int i = 0; i < filters.length; i++) {
      if (file.replace('\\','/').indexOf(filters[i]) != -1)
	return true;
    }
    return false;
  }

  private int getNum(String key,Properties p) {
    try {
      if (p.get(key) == null) return 0;
      return (new Integer((String)p.get(key)).intValue());
    } catch (Exception e) {
      System.out.println("Bad value for key = " + key + " : " + e);
      return 0;
    }
  }

  private void processNotify(String emails, String suite, int pass, int threshold) {
    System.out.println("Recording notification for "+suite+", threshold "+threshold+", pass "+pass);
    StringTokenizer list = new StringTokenizer(emails," ,");
    while (list.hasMoreTokens()) {
      String email = list.nextToken();
      String suites = (String)emailTable.get(email);
      String notify = suite+" results "+pass+"% (min "+threshold+"%)";
      if (suites == null) {
        emailTable.put(email, notify);
      } else {
        emailTable.put(email, suites+","+notify);
      }
    }
  }

  private void processFailures(int fail, int total, String suite) {
    try {
      //System.out.println("Processing fail = "+fail+", total = "+total+", suite = "+suite);
      Properties p = new Properties();
      p.load(new FileInputStream(workDir+"/testsuite.props"));
      String email = (String)p.get("notify."+suite);
      if (email != null && total > 0) {
	      String t = (String)p.get("threshold."+suite);
	      int threshold = (t == null) ? 100 : new Integer(t.trim()).intValue();
	      int pass = (((total-fail)*100)/total);
	      if (pass < threshold) {
		processNotify(email,suite,pass,threshold);
	      }
	}
    } catch (Exception e) {
      System.out.println("Exception " + e + " encountered while processing failure notifications for " + suite);
    }
  }

  private void processProperties(String f,String parent) {
    boolean debug = true;
    try {
      if (f.endsWith("testsuite.properties"))
        return;
      if (debug) System.out.println("Processing properties " + f + " parent = " + parent);
      Properties p = new Properties();
      p.load(new FileInputStream(f));
      int fail = 0;
      int tot = 0;
      fail = getNum("test.failure.count",p);
      if (debug) System.out.println("fail = " + fail);
      String test = f.substring(0,f.indexOf(".properties"));
      if (debug) System.out.println("test = " + test);
      if (debug) System.out.println("Path sep char = " + File.separatorChar);
      test = test.substring(test.lastIndexOf(File.separatorChar)+1);
      if (debug) System.out.println("test = " + test);
      if (fail > 0) {
	writeToFile(new File(workDir+File.separatorChar+parent+File.separatorChar+test+failureSuffix),test+" test failed.  See HTML report for details\n",false);
      }
      failed += fail;
      tot = getNum("test.total.count",p);
      if (debug) System.out.println("tot = " + tot);
      // create a .suc file only if there were no failures
      if (fail == 0 && tot-fail > 0) {
	writeToFile(new File(workDir+File.separatorChar+parent+File.separatorChar+test+successSuffix),"", false);
      }
      passed += (tot-fail);
      //failedTests = "various";
      //failedTestsHtml = "<a href=\""+parent+"/html\">HTML Report</a>";
      if (debug) System.out.println("failedTestsHtml = " + failedTestsHtml + " failedTests = " + failedTests);
    } catch (Exception e) {
      if (debug) {
        System.out.println("Exception in processProperties : " + e);
        //e.printStackTrace();
      }
    }
  }

  /** @return 1 if miscellaneous file found (non suc/dif/txt etc.) else 0 */
  private int scanFile(File f,String parent) throws IOException {
    String file = f.toString();
    if (skip(file))  return 0;
    String filen = f.getName();
//    System.out.println("********* scan file ********** Got the File Name: "+ filen);
    if (file.endsWith(successSuffix)) {
      passed++;
      passedTests += (passedTests.length() == 0 ? "" : ", ")+filen.substring(0,filen.length()-4);
    } else if (file.endsWith(failureSuffix)) {
      failed++;
      failedTests += (failedTests.length() == 0 ? "" : ", ")+filen;
      failedTestsHtml += (failedTestsHtml.length() == 0 ? "" : ", ")+"<a href=\""+parent+"/"+filen+"\">"+filen+"</a>";
//      System.out.println("******************* failedTestsHtml ********"+ failedTestsHtml);
      //Populate start and end time in dif file for current test only
      if((startTime != null && !startTime.equals("")) && (endTime != null && !endTime.equals(""))){
        if(file.contains(tcDisplayName)) populateStartEndTime(file);
      }
    } else if (file.endsWith(unknownSuffix)) {
      if (dirLevels < 3) {
        // The above logic is a bit of a hack.  For Directory levels deeper than 2, we will ignore files such as *.txt
        // to avoid getting spurious "unknown" results
        unknown++;
        unknownTests += (unknownTests.length() == 0 ? "" : ", ")+filen;
        unknownTestsHtml += (unknownTestsHtml.length() == 0 ? "" : ", ")+"<a href=\""+parent+"/"+filen+"\">"+filen+"</a>";
      } else {
        //System.out.println("Ignoring file "+file+" because directory level is greater than 2");
      }
    } else if (file.endsWith(propsSuffix)) {
      processProperties(file,parent);
    } else {
      return 1;
    }
    return 0;
  }

  /** populate startime at beginning and endtime at the end of the file */
  private void populateStartEndTime(String file) {
    try {
      File difFile = new File(file);            
      File tempFile = new File(difFile.getAbsolutePath() + ".tmp"); 
      FileInputStream dfile = new FileInputStream(difFile);
      FileOutputStream tfile = new FileOutputStream(tempFile); 
      int line;
      
      tfile.write(("starttime="+startTime+'\n').getBytes());
 
      while ((line = dfile.read()) != -1) {       
        tfile.write((char)line);
      }
      
      tfile.write(('\n'+"endtime="+endTime).getBytes());
      
      tfile.close();
      dfile.close();
      
      if (!difFile.delete()) {
        //System.out.println("Could not delete file:"+difFile.getName());
        return;
      } 
      
      if (!tempFile.renameTo(difFile))
        System.out.println("Could not rename file:"+tempFile.getName());
      
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private String numPad(int num) {
    return numPad(num,4);
  }

  private String numPad(int num, int digits) {
    String ret = "";
    int[] dig = new int[digits];
    boolean pad = true;
    for (int i = 0; i < digits; i++) {
      int power = (int)Math.pow(10,digits-i-1);
      dig[i] = num/power;
      num = num - dig[i]*power;
      if (pad) pad = (dig[i] == 0) && i < digits-1;
      if (pad) {
	ret += " ";
      } else {
	ret += dig[i];
      }
    }
    return ret;
  }

  // return HTML title
  private String getTitle() {
    String result = "<html>\n<head>\n<title>"+title+"</title>\n</head>\n<body>\n<h2 align=\"center\">"+title+"</h2>\n";
    return result;
  }

  // return HTML header based on header properties file
  private String getHeader() {
    return getProperties(header);
  }

  // return HTML footer based on footer properties file
  private String getFooter() {
    return getProperties(footer);
  }

  private String getProperties(File[] files) {
    String result = "";
    for (int i = 0; i < files.length; i++ ) {
      result += getProperties(files[i]);
    }
    return result;
  }

  private String getProperties(File file) {
    String result = "";
    try {
      if (props != null) {
	Properties p = new Properties();
	p.load(new FileInputStream(file));
	result += "<table border=\"1\">";
	for (Enumeration e = p.keys(); e.hasMoreElements(); ) {
	  String key = (String) e.nextElement();
	  String value = p.getProperty(key);
	  result += "<tr><td><b>"+key+"</b><td>"+value;
	}
	result += "</table>";
      }
    } catch (Exception e) {
      result = "";
      //result = "<P>Error processing file : " + file+"<BR><P>\n";
      if (printToScreen) System.out.println(result);
      //e.printStackTrace();
    }
    return result;
  }

  private Properties getSuiteProps(Properties props,String dir) {
    try {
      	Properties p = new Properties();
        String file = startDir+"/"+dir+"/testsuite.properties";
      	p.load(new FileInputStream(file));
      	for (Enumeration e = props.keys(); e.hasMoreElements(); ) {
	  String key = (String)e.nextElement();
	  String value = (String)p.get(key);
	  if (value != null) {
	    props.put(key,value);
	  } else {
	    props.put(key,"");
	  }
	}
    } catch (Exception e) {
      if (printToScreen) System.out.println("Error getting properties : " + e);
    }
    return props;
  }

  private Properties initProperties(Properties p,String[] keys) {
    for (int i = 0; i < keys.length; i++ ) {
      p.put(keys[i],"");
    }
    return p;
  }

  private int getDiff(Properties p, String startKey, String endKey) {
    int result = 0;
    try {
      int start = new Integer((String)p.get(startKey)).intValue();
      int end =  new Integer((String)p.get(endKey)).intValue();
      result = end-start;
    } catch (Exception e) {
      //System.out.println("Error processing "+startKey+" or "+endKey);
    }
    return result;
  }

  private String getErrorDiffs(Properties p) {
    String result = "";
    try {
      int errDiff = getDiff(p,"start.errors","end.errors");
      int stackDiff = getDiff(p,"start.stack_traces","end.stack_traces");
      if (errDiff+stackDiff > 0) {
	result = "<br>"+stackDiff+","+errDiff;
      }
    } catch (Exception e) {
      System.out.println("Error processing error diffs " + e);
    }
    return result;
  }

  private String getDuration(Properties p) {
    String result = " ";
    try {
      String duration = (String)p.get("testsuite.duration");
      if (duration != null && duration.trim().length() > 0) {
	result = "("+duration.trim()+")";
      }
    } catch (Exception e) {
      //System.out.println("Error processing duration " + e);
    }
    return result;
  }

  
  private boolean okToRemoveUserDir(String dir,String[] safe) {
    for (int i = 0; i < safe.length; i++) {
      if (dir.indexOf(safe[i]) != -1) return false;
    }
    return true;
  }

  private boolean deleteDirectory(File path) {
    if( path.exists() && daysToKeep != -1 ) {
      File[] files = path.listFiles();
      for(int i=0; i<files.length; i++) {
         if(files[i].isDirectory()) {
           deleteDirectory(files[i]);
         }
         else {
           files[i].delete();
         }
      }
    }
    return( path.delete() );
  }

  private void scanUserDir(File dir) throws IOException {
    String[] keep = new String[] { "KEEP", "DONOTDELETE" };
    String[] safe = toLeaveAlone(daysToKeep, keep);
    // for (int i=0; i < safe.length; i++) System.out.println(safe[i]);
    String[] list = dir.list();
    String userHtml = "";
    String boldStart = "<font color='red'><b>";
    String boldStop = "</b></font>";
    Dir[] dirlist = new Dir[list.length];
    String topRow = null;
    
    for (int i = 0; i < list.length; i++) {
      String s = list[i];
      File f = new File(dir, s);

      if (f.isDirectory()) {
        if (daysToKeep != -1 && okToRemoveUserDir(f.getName(),safe)) {
	  System.out.println("... removing old directory " + f.getName() + ".  Please be patient");
	  deleteDirectory(f);
	}
	try {
	  Properties p = new Properties();
	  p.load(new FileInputStream(f+"/results.properties"));
	  int total = getNum("count.total",p);
	  int failed = getNum("count.dif",p);
	  int passed = total - failed;
	  String results = total+"T="+passed+"P+"+failed+"F";
          String start = (failed > 0 ? boldStart : "");
          String stop = (failed > 0 ? boldStop : "");
	  String url = f.getName()+"/results/dashboard.html";
	  String resultsRow = "<tr><td>"+start+f.getName()+stop+"</td>"+
	                  "<td><a href=\""+url+"\">"+start+results+stop+"</a></td>\n";
	  if (onTop != null && resultsRow.toLowerCase().indexOf(onTop) != -1) {
	    topRow = resultsRow+"<tr><td>- - - - - - - - - - </td><td>- - - - - - - - - - </td>\n";
	    // System.out.println("Top Row = "+topRow);
	  } else {
	    dirlist[i] = new Dir(f, resultsRow);
	  }
	} catch (Exception e) {
	  // System.out.println("No results found in " +f);
	}
      }
    }
    Arrays.sort(dirlist,new FileDateCompare());
    // use this sorted list for display
    for (int i=0;i<dirlist.length;i++) {
      if (dirlist[i] != null) {
        userHtml = dirlist[i] +userHtml;
      }
    }
    html.append(((topRow != null) ? topRow : "") + userHtml);
  }

  private void scanDir(File dir) throws IOException {
    int oldPassed = passed;
    int oldFailed = failed;
    int oldUnknown = unknown;
    int miscFiles = 0;
    String[] list = dir.list();
    // System.out.println("list of directories " + list);
    dirLevels++;
    if (skip(dir.toString())) {
      dirLevels--;
      // System.out.println("skipping directory " + dir);
      return;
    }
    for (int i = 0; i < list.length; i++) {
      String s = list[i];
      File f = new File(dir, s);

      if (f.isDirectory() && !f.getName().equals("screenshots")) {
        scanDir(f);
        System.gc();
      } else {
    	 
        miscFiles += scanFile(f,dir.getName());
      }
    }
    int deltaSucs = passed - oldPassed;
    int deltaDifs = failed - oldFailed;
    int deltaUnks = unknown - oldUnknown;
    int total = deltaSucs + deltaDifs + deltaUnks;
    boolean summary = false;
//     System.out.println("***** From Scandir deltaSucs : " + deltaSucs + " deltaDifs: "+deltaDifs+" total: "+total );
    if (total > 0 || miscFiles > 0) {
      String dirStr = dir.toString();

      if (dirStr.equals(startDir.toString())) {
        if (printToScreen) System.out.println("------------ summary ------------");
        html.append("<b>");
        summary = true;
      }
      String start = startDir.toString();
      if (dirStr.length() > start.length()+1) {
        if (clip) {
	  dirStr = dirStr.substring(start.length()+1);
//	  if (compact) {
//	    if (dirStr.startsWith(COMPACT1) && dirStr.length() > COMPACT1.length()) {
//	      dirStr = dirStr.substring(COMPACT1.length()+1);
//	      if (dirStr.startsWith(COMPACT2) && dirStr.length() > COMPACT2.length()) {
//		dirStr = dirStr.substring(COMPACT2.length()+1);
//	      }
//	    }
//	    if (skip(dirStr) || dirStr.equals(COMPACT1) || dirStr.equals(COMPACT2)) {
//	      dirLevels--;
//	      return;
//	    }
//	  }
	}
      }
      if (!failOnly || (deltaDifs > 0) || summary) { // || miscFiles > 0)
	int success = (int)Math.round(deltaSucs * 100.0 / total);
	if (!failOnly || (failOnly && failedTests.trim().length() > 0)) {
	  if (printToScreen) System.out.println(numPad(success)+ " "+numPad(deltaDifs)+"  "+numPad(deltaSucs)+"  "+numPad(total)+"  "+dirStr);
	  String hDirStr = dirStr;
	  Properties p = new Properties();
	  p = initProperties(p, new String[]{
	    "testsuite.duration",
	    "start.errors",
	    "end.errors",
	    "start.stack_traces",
	    "end.stack_traces"});
	  p = getSuiteProps(p,dirStr);
	  String hDurationStr = getDuration(p);
	  String start_bold = "";
	  String stop_bold = "";
//      System.out.println("*********dir level = " + dirLevels + " ***** hDirStr = " + hDirStr);
	  if (hDirStr.indexOf("/") != -1 || hDirStr.indexOf("\\") != -1) {
	    start_bold = "<b>";
	    hDirStr = "-- SUMMARY --";
            summary = true;
	    stop_bold = "</b>";
	    if (total > 0) summ_row.append("<tr><td align=\"right\">")
		.append(start_bold+numPad(deltaDifs)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(deltaUnks)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(deltaSucs)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(total)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(success)+" %"+stop_bold)
		.append("</td><td>")
		.append(start_bold+hDirStr+stop_bold+"<td>.</td><td>.</td></tr>\n");
    	    processFailures(deltaDifs+deltaUnks,total,"summary");
	  } else {
	    data.append("<tr><td align=\"right\">")
		.append(start_bold+numPad(deltaDifs)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(deltaUnks)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(deltaSucs)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(total)+stop_bold)
		.append("</td><td align=\"right\">")
		.append(start_bold+numPad(success)+" %"+stop_bold)
		.append("</td><td>")
		.append(start_bold+"<a href=\""+hDirStr+"\">"+hDirStr+"</a>"+stop_bold+" "+hDurationStr+getErrorDiffs(p));
    	    processFailures(deltaDifs+deltaUnks,total,hDirStr);
//           System.out.println("********* failedTestsHtml: *******"+ failedTestsHtml);
            if ((new File(dir, "html")).exists()) {
              failedTestsHtml += (failedTestsHtml.length() == 0 ? "" : ", ")+"<a href=\""+dir.getName()+"/html\">HTML Report</a>";
	    }
	    if (failedTestsHtml.trim().length() > 0) {
	      if (printToScreen) System.out.println("\t"+failedTests);
	      data.append("<td>").append(failedTestsHtml).append("</td>");
	    } else {
	      data.append("<td>.</td>");
	    }
	    if (unknownTests.trim().length() > 0) {
	      if (printToScreen) System.out.println("\t"+unknownTests);
	      data.append("<td>").append(unknownTestsHtml).append("</td>");
	    } else {
	      data.append("<td>.</td>");
	    }
	    data.append("</tr>\n");
	  }
	}
        failedTests = "";
	failedTestsHtml = "";
	unknownTests = "";
	unknownTestsHtml = "";
      }
      if (summary) {
	if (printToScreen) System.out.println("ok .  .  .  .  .  total="+numPad(total)+" "+dirStr);
	if (props != null) {
	  String results = "\ncount.suc="+passed+"\ncount.dif="+failed+"\ncount.unknown="+unknown+"\ncount.total="+(passed+failed+unknown)+"\n";
	  if (total > 0) {
            writeToFile(props,results,false);
            System.out.println("***** Results Summary *****\n"+(passed+failed+unknown)+" Total = "+passed+" Passed + "+failed+" Failed\n*************************");
          }
	}
      }
    }
    dirLevels--;
  }

  public static void main(String[] args) throws Exception {
    QuickSummary qs = new QuickSummary();
    String dir = ".";
    for (int i = 0; i < args.length; i++ ) {
      if (args[i].equalsIgnoreCase("-fullname")) qs.setClip(false);
//      else if (args[i].equalsIgnoreCase("-nocompact")) qs.setCompact(false);
      else if (args[i].equalsIgnoreCase("-scanUserDir")) qs.setScanUserDir(true);
      else if (args[i].equalsIgnoreCase("-showall")) qs.setFailOnly(false);
      else if (args[i].equalsIgnoreCase("-printToScreen")) qs.setPrintToScreen(true);
      else if (args[i].equalsIgnoreCase("-props")) {
	String props = args[++i];
	qs.setProperties(new File(props));
	//if (qs.printToScreen) System.out.println("Setting properties in \""+props+"\"");
      } else if (args[i].equalsIgnoreCase("-report")) {
	String report = args[++i];
	qs.setReport(new File(report));
	//if (qs.printToScreen) System.out.println("Creating report in \""+report+"\"");
	qs.setPrintToScreen(false);
      } else if (args[i].equalsIgnoreCase("-header")) {
	String header = args[++i];
	qs.setHeader(new File(header));
	//if (qs.printToScreen) System.out.println("Using \""+header+"\" for header");
      } else if (args[i].equalsIgnoreCase("-workDir")) {
	String workDir = args[++i];
	qs.setWorkDir(workDir);
      } else if (args[i].equalsIgnoreCase("-title")) {
	qs.setTitle(args[++i]);
      } else if (args[i].equalsIgnoreCase("-onTop")) {
	qs.setOnTop(args[++i]);
      } else if (args[i].equalsIgnoreCase("-footer")) {
	String footer = args[++i];
	qs.setFooter(new File(footer));
      } else if (args[i].equalsIgnoreCase("-daysToKeep")) {
	String n = args[++i];
	qs.setDaysToKeep(new Integer(n).intValue());
	//if (qs.printToScreen) System.out.println("Using \""+footer+"\" for footer");
      } else if (args[i].equalsIgnoreCase("-endtime")) {
        String endtime = null;  
        if(!args[i+1].equals("end-time") && args[i+1].substring(0,4).matches("-?\\d+(\\.\\d+)?"))
        {
          endtime = args[++i] + " " + args[++i] + " " + args[++i];
          //System.out.println("End Time:"+endtime);
          qs.setEndTime(endtime);
        } else ++i;
      } else if (args[i].equalsIgnoreCase("-starttime")) {
        String starttime = null;
        if(!args[i+1].equals("end-time") && args[i+1].substring(0,4).matches("-?\\d+(\\.\\d+)?"))
        {
          starttime = args[++i] + " " + args[++i] + " " + args[++i];
          //System.out.println("Start Time:"+starttime);
          qs.setStartTime(starttime);
        } else ++i;
      } else if (args[i].equalsIgnoreCase("-testcase")) {
        String testcase = args[++i];
        //System.out.println("testcase:"+testcase);
        qs.setTestCase(testcase);
      } else if (args[i].equalsIgnoreCase("-help")) {
	System.out.println(
	    "Usage: java [options] " + QuickSummary.class.getName() + " <dir>\n"
		+ "  -showall  to show all results (pass and fail)\n");
	System.exit(-1);
      }
      else dir = args[i];
    }
    if (!(dir.startsWith("/net/")||(dir.startsWith("\\\\")))) {
      qs.setDaysToKeep(-1);
    }
    //System.out.println("scanning directory " + dir);
    qs.setStartDir(new File(dir));
    qs.execute();
  }
}


class Dir {
  File f;
  String html;
  Dir(File f, String html) {
    this.f = f;
    this.html = html;
  }
  public String toString() {
	return html;
  }
}

class FileDateCompare implements Comparator {
  public int compare(Object o1, Object o2) {
    int result = 0;
    if ((o1 instanceof Dir)&&(o2 instanceof Dir)) {
      File f1 = ((Dir)o1).f;
      File f2 = ((Dir)o2).f;
      result = (f1.lastModified() < f2.lastModified() ? -1 : +1);
      // System.out.println(result+" : "+f1.getName()+" mod "+f1.lastModified()+" : "+f2.getName()+" mod "+f2.lastModified()); 
    }
    return result;
  }
}

