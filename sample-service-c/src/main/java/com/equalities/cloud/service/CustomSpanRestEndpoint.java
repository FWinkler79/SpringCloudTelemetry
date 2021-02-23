package com.equalities.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.BaggageInScope;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.equalities.cloud.service.persistence.model.Person;

import lombok.extern.slf4j.Slf4j;

/**
 * A REST endpoint to show the use of custom child spans, trace context and support for baggage.
 */
@Slf4j
@RestController
public class CustomSpanRestEndpoint {
  
  @Autowired
  private Tracer tracer;
  
  private CustomSpanClient customSpanClient;
  
  CustomSpanRestEndpoint(CustomSpanClient customSpanClient) {
    this.customSpanClient = customSpanClient;
  }
  
  @PostMapping("/custom-span")
  public Person forward(@RequestBody Person person) {
    BaggageInScope baggage = tracer.getBaggage("my-baggage-key");
    String baggageValue = baggage.get();
    
    log.info("Baggage from span is: {}", baggageValue);
    
    person.setLastName(person.getLastName() + "C");
    return customSpanClient.forward(person);
  }
}
