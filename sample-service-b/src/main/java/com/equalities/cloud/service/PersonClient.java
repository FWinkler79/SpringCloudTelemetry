package com.equalities.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.equalities.cloud.service.persistence.model.Person;

@FeignClient(name="service-c-client", url="http://localhost:8003")
public interface PersonClient {

  @PostMapping(path = "/people", produces = "application/json")
  public void persist(Person person);
}
