package com.CallAPI.call.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EntriesModel {
  @JsonProperty("API")
  private String API;
  @JsonProperty("Description")
  private String Description;
  @JsonProperty("Auth")
  private String Auth;
  @JsonProperty("HTTPS")
  private String HTTPS;
  @JsonProperty("Cors")
  private String Cors;
  @JsonProperty("Link")
  private String Link;
  @JsonProperty("Category")
  private String Category;
}
