package com.CallAPI.call.controller;

import com.CallAPI.call.model.EntriesModel;
import com.CallAPI.call.model.FilterModel;
import com.CallAPI.call.service.ApiService;
import java.util.List;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  @Autowired
  private ApiService apiService;

  @GetMapping("/entries")
  @PreAuthorize("hasAuthority('USER')")
  public ResponseEntity<List<EntriesModel>> getEntries(FilterModel filterModel) {
    return ResponseEntity.ok(apiService.getEntries(filterModel));
  }

}
