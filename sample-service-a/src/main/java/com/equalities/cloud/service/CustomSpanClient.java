package com.equalities.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.equalities.cloud.service.persistence.model.Person;

@FeignClient(name="customspan-client-for-service-b", url="http://localhost:8002")
public interface CustomSpanClient {

  @PostMapping(path = "/custom-span", produces = "application/json")
  public Person forward(Person person);
}