package com.mycompany.app;

import com.mycompany.component.JenkinsApi;
import com.mycompany.component.JobApi;
import com.mycompany.conf.JenkinsConfig;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JenkinsTest {

  @Resource private JenkinsConfig jenkinsConfig;

  @Resource private JenkinsApi jenkinsApi;
  @Resource private JobApi jobApi;

  @Test
  public void jobApiTest() {
    jobApi.getJob("simple-java-maven-app");
  }

  @Test
  public void getJenkinsJobTest() throws IOException {

    // jenkinsApi.getLabelNodeInfo("master");
    System.out.println("------------------------");
    jenkinsApi.getPluginInfo();
  }

  /** 获取视图基本信息 */
  @Test
  public void getView() {
    try {
      // 视图名
      String viewName = "simple-java-maven-app";
      // 获取视图基本信息
      JenkinsServer jenkinsServer = jenkinsConfig.connection();
      JenkinsHttpClient jenkinsHttpClient = jenkinsConfig.getClient();
      View view = jenkinsServer.getView(viewName);
      System.out.println(view.getName());
      System.out.println(view.getUrl());
      System.out.println(view.getDescription());
      // 获取视图xml信息
      String viewXml = jenkinsHttpClient.get("/view/" + viewName + "/api/xml");
      System.out.println(viewXml);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getClient() throws URISyntaxException, IOException {

    JenkinsHttpClient jenkinsHttpClient = null;
    String JENKINS_URL = "http://47.105.193.166:8080";
    String JENKINS_USERNAME = "admin";
    String JENKINS_PASSWORD = "zxcvbnm/8";

    URI uri = new URI(JENKINS_URL);

    String jobName = "simple-java-maven-app";
    JenkinsHttpClient client = new JenkinsHttpClient(uri, JENKINS_USERNAME, JENKINS_PASSWORD);
    JenkinsServer jenkinsServer = new JenkinsServer(client);
      System.out.println(client.getJenkinsVersion());
    // 获取所有job
    Map<String, Job> jobs = jenkinsServer.getJobs();
    jobs.forEach(
        (key, value) -> {
          System.out.println(key + " ---------- " +value);
        });
    // 触发jenkins构建
   // jenkinsServer.getJob(jobName).build();
    // 获取最后一次构建结果，结果为BuildResult枚举类型
   // jenkinsServer.getJob(jobName).getLastBuild().details().getResult();

  }

  @Test
  public void versionTest() throws IOException {
    System.out.println(jenkinsConfig.getClient().getJenkinsVersion());

// 获取所有job
      Map<String, Job> jobs = jenkinsConfig.connection().getJobs();
      JobWithDetails jobdetails=null;
      Job job;
      String jobName = "simple-java-maven-app";
      job=jobs.get(jobName);
      jobdetails=job.details();
      Build lastbuild=jobdetails.getLastBuild();
      BuildWithDetails buildDetails=lastbuild.details();
      if(buildDetails.isBuilding()){
          System.out.println("最后一次构建还没完成，还在构建中");
      }else{
          System.out.println("最后一次构建已完成");
      }
  }

  @Test
  public void jenkinsApiTest(){
      jenkinsApi.getLabelNodeInfo("simple-java-maven-app");
  }
}
