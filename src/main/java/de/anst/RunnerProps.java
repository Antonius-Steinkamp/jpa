/**
 * RunnerEnv.java created 10.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.CommandLineRunner;

import lombok.extern.java.Log;

/**
 * RunnerEnv created 10.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
// @Component
@Log
public class RunnerProps  implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		sortedMapOfProperties().forEach((key,value) -> log.info( key + ": " + value));
	}
	
	public static Map<String, String> sortedMapOfProperties() {
		Properties properties = System.getProperties();
		Map<String, String> theMap = new HashMap<>();
		properties.keySet().forEach(key -> theMap.put(key.toString(), properties.get(key).toString()));
		
		return AUtils.sortByKeys(theMap);
	}

}
