package com.amazon.ec2.demo.springbootec2.security;


 //import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
//import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
//import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSSecrets {

  private static final String access_key= "<Your access Key>";
  private static final String secret_key= "<Your Secret Key>";

  @Bean
  public DataSource dataSource() {
    AwsSecretData awsSecretData = getSecret();
    return DataSourceBuilder.create().url("jdbc:"+awsSecretData.getEngine()+"://"+awsSecretData.getHost()+":"+awsSecretData.getPort()+
            "/rds1")
        .username(awsSecretData.getUsername())
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .password(awsSecretData.getPassword())
        .build();
  }

  private static Gson gson = new Gson();

  private  AwsSecretData getSecret() {

    String secretName = "<Your secret Key>";
    Region region = Region.getRegion(Regions.US_EAST_2);

    AWSSecretsManager awsSecretsManager = AWSSecretsManagerClient.builder().withRegion(Regions.US_EAST_2).
    withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(access_key,secret_key)))
    .build();
    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
    GetSecretValueResult getSecretValueResponse;

    try {
      getSecretValueResponse = awsSecretsManager.getSecretValue(getSecretValueRequest);
    } catch (Exception e) {
      throw e;
    }

    String secret = getSecretValueResponse.getSecretString();
    System.out.println("PRinting it");
    System.out.println(secret);
    if(secret != null) {

      AwsSecretData awsSecretData =  gson.fromJson(secret,AwsSecretData.class);
      return awsSecretData;
    }

    return null;
  }

}
