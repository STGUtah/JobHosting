package com.stgconsulting.config;

import com.stgconsulting.api.MonsterClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MonsterConfiguration {
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("com.stgconsulting.wsdl");
    return marshaller;
  }

  @Bean
  public MonsterClient monsterClient(Jaxb2Marshaller marshaller) {
    MonsterClient client = new MonsterClient();
    client.setDefaultUri("https://gateway.monster.com:8443/bgwBroker");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
