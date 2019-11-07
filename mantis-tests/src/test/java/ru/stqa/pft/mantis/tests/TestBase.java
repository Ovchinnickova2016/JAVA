package ru.stqa.pft.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.SoapHelper;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


public class TestBase {

  protected final static ApplicationManager app =
    new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));



  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.ftp().restore("config_inc.php.back", "config_inc.php");
    app.stop();
  }

 public static boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    boolean isFixed = true;
    SoapHelper soapHelper = new SoapHelper(app);
   MantisConnectPortType mc = soapHelper.getMantisConnect();
   BigInteger issueId2 =BigInteger.valueOf (issueId);
  ObjectRef status =  mc.mc_issue_get("administrator","root",issueId2).getStatus();
    if (status.getName().equals("resolved")){
      isFixed = false;

    }
    return isFixed;
  }


  protected static void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }

  }

  private static Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }


  public static boolean isIssueOpen2(String issueId2) throws IOException, ServiceException {
    boolean isFixed = true;
    String json =   getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/"+issueId2+".json")).returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues =  parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){
    }.getType());
    if (!issue.iterator().next().getState_Name().equals("Open")){
      isFixed = false;
    }
    return isFixed;
  }


  protected static void skipIfNotFixed2(String issueId) throws IOException, ServiceException {
    if (isIssueOpen2(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
