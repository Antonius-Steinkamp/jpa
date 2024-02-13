/**
 * RunnerEnv.java created 10.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
		Properties properties = System.getProperties();
		Map<String, String> theMap = new HashMap<>();
		properties.keySet().forEach(key -> theMap.put(key.toString(), properties.get(key).toString()));
		
		Map<String, String> env = AUtils.sortByKeys(theMap);
		env.forEach((key,value) -> log.info( key + ": " + value));
	}

}