package com.CallAPI.call.service.Impl;

import com.CallAPI.call.model.EntriesModel;
import com.CallAPI.call.model.FilterModel;
import com.CallAPI.call.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;



@Service
public class ApiServiceImpl implements ApiService {

  @Override
  public List<EntriesModel> getEntries(FilterModel filterModel) {
    String apiUrl = "https://api.publicapis.org/entries";
    String category = filterModel.getCategory();
    if (StringUtils.isNotEmpty(category)) {
      apiUrl += "?category=" + category;
    }
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create(apiUrl))
        .build();
    try {
      HttpResponse<String> httpResponse = client.send(request,
          HttpResponse.BodyHandlers.ofString());
      JSONObject responseObject = new JSONObject(httpResponse.body());
      JSONArray entries = responseObject.getJSONArray("entries");

      List<EntriesModel> entriesModels = new ArrayList<>();
      ObjectMapper objectMapper = new ObjectMapper();

      for (int i = 0; i < entries.length(); i++) {
        JsonNode jsonNode = objectMapper.readTree(entries.getJSONObject(i).toString());
        EntriesModel entriesModel = objectMapper.treeToValue(jsonNode, EntriesModel.class);
        entriesModels.add(entriesModel);
      }
      Integer limit = filterModel.getLimit();
      return Objects.nonNull(limit) ?
          entriesModels.stream().limit(limit).toList() :
          entriesModels;

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
