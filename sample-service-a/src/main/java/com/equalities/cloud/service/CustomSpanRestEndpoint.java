package com.equalities.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.equalities.cloud.service.persistence.model.Person;

/**
 * A REST endpoint to show the use of custom child spans, trace context and support for baggage.
 */
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
//    BaggageField baggage = BaggageField.create("baggage-custom-baggage");
//    baggage.updateValue("custom-value");
//    
//    BaggagePropagation.newFactoryBuilder(B3Propagation.FACTORY)
//                      .add(SingleBaggageField
//                      .remote(baggage))
//                      .build();
    if (person.getLastName().endsWith("C")) {
      return person;
    }
    
    tracer.createBaggage("my-baggage-key", "my-baggage-value");
    
    person.setLastName(person.getLastName() + "-A");
    return customSpanClient.forward(person);
  }
}
