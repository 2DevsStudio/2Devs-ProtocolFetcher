package com.ignitedev.devsprotocolfetcher.data;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class EntityFetchedData {

  private int protocolID;
  private String typeName;
  private String displayName;
  private double height;
  private double width;

  private Map<Integer, String> metaData = new HashMap<>();

}
