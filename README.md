# 2Devs-ProtocolFetcher


## Current support

  - Entities


## Description 

  ProtocolFetcher is used to access protocol data for example for packets, it's very helpful since it support most of version ;)
  
  
## How to use


```java
import com.ignitedev.devsprotocolfetcher.api.ProtocolFetcherAPI;
import com.ignitedev.devsprotocolfetcher.enums.MinecraftVersion;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
      ProtocolFetcherAPI.setDebugMode(true); // if you need debug mode, more logs.

      ProtocolFetcherAPI protocolFetcherAPI = new ProtocolFetcherAPI(MinecraftVersion.v_1_17); // specify version here 

      protocolFetcherAPI.fetchData(); // fetching all data to cache
      
      // examples:
        protocolFetcherAPI.getEntityByProtocolID(#int);
      // or
        protocolFetcherAPI.getEntityByDisplayName(#entity#getDisplayName());
      // or
        protocolFetcherAPI.getEntityByTypeName(#entity#getEntityType().name());
    }
}

```

### Adding dependency

Maven:

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependecies>
	<dependency>
	    <groupId>com.github.2DevsStudio</groupId>
	    <artifactId>2Devs-ProtocolFetcher</artifactId>
	    <version>6264c54e53</version> // specify version here
	</dependency>
</dependecies>
```

Gradle:

```text
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
	  implementation 'com.github.2DevsStudio:2Devs-ProtocolFetcher:6264c54e53' // specify version here
}
```

