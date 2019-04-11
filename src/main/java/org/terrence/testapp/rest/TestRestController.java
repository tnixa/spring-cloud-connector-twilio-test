package org.terrence.testapp.rest;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.cloud.Cloud;
// import org.springframework.cloud.CloudException;
// import org.springframework.cloud.CloudFactory;
// import org.springframework.cloud.service.ServiceInfo;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
  // private Cloud getCloud() {
  // try {
  // CloudFactory cloudFactory = new CloudFactory();
  // return cloudFactory.getCloud();
  // } catch (CloudException ce) {
  // return null;
  // }
  // }

  // @GetMapping("/infos")
  // public String getInfos() {
  // Cloud cloud = getCloud();
  // List<ServiceInfo> infos = cloud.getServiceInfos();
  // String result = "Info:\n";
  // for (ServiceInfo info : infos) {
  // result += info.getClass().toString() + "\n";
  // }
  // return result;
  // }

  @Autowired
  private TwilioRestClient twilioRestClient;

  @GetMapping("/test")
  public String runTest() {
    try {
      // TwilioRestClient twilioRestClient = new TwilioRestClient("sid", "token");
      Account account = twilioRestClient.getAccount();
      System.out.println("Account is: " + account);
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      params.add(new BasicNameValuePair("To", "15072879123"));
      params.add(new BasicNameValuePair("From", "+15073152942"));
      params.add(new BasicNameValuePair("Body", "Twilio Test"));

      MessageFactory messageFactory = twilioRestClient.getAccount().getMessageFactory();
      Message message = messageFactory.create(params);
      System.out.println(message.getSid());

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e);
    }
    return "done";
  }
}