package com.stgconsulting.api;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monster")
public class MonsterResource {

  @RequestMapping(
    value = "/summary",
    method = RequestMethod.GET,
    produces = "application/json"
  )
  public String getMonsterPostingSummary() {
    return "";
  }

  @RequestMapping(
    value = "/posting/{id}",
    method = RequestMethod.GET,
    produces = "application/json"
  )
  public String getMonsterPosting(@PathVariable("id") long id) {
    return "";
  }

  @RequestMapping(
    value = "/posting/{id}",
    method = RequestMethod.POST,
    produces = "application/json"
  )
  public String saveMonsterPosting(@PathVariable("id") long id) {
    return "";
  }

  @RequestMapping(
    value = "/posting/{id}",
    method = RequestMethod.DELETE,
    produces = "application/json"
  )
  public String deleteMonsterPostingSummary(@PathVariable("id") long id) {
    return "";
  }
}
