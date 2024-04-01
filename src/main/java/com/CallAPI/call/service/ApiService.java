package com.CallAPI.call.service;

import com.CallAPI.call.model.EntriesModel;
import com.CallAPI.call.model.FilterModel;
import java.util.List;
import org.json.JSONArray;

public interface ApiService {

  List<EntriesModel> getEntries(FilterModel filterModel);

}
