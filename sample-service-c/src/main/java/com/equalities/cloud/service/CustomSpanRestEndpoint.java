package com.equalities.cloud.service;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.equalities.cloud.service.persistence.model.Person;

/**
 * A REST endpoint to show the use of custom child spans, trace context and support for baggage.
 */
@RestController
public class CustomSpanRestEndpoint {
  
  private CustomSpanClient customSpanClient;
  
  CustomSpanRestEndpoint(CustomSpanClient customSpanClient) {
    this.customSpanClient = customSpanClient;
  }
  
  @PostMapping("/custom-span")
  @NewSpan("forwarding-person-from-c-to-a")
  public Person forward(@RequestBody Person person) {
    person.setLastName(person.getLastName() + "C");
    return customSpanClient.forward(person);
  }
}
