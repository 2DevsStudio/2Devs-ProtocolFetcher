package com.ignitedev.devsprotocolfetcher;

import com.ignitedev.devsprotocolfetcher.api.ProtocolFetcherAPI;

public final class ProtocolFetcher {

  public static void main(String[] args) {
    ProtocolFetcherAPI.setDebugMode(true);

    ProtocolFetcherAPI protocolFetcherAPI = new ProtocolFetcherAPI("1.17.1");

    protocolFetcherAPI.fetchData();

  }

}
