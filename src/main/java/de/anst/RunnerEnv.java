/**
 * RunnerEnv.java created 10.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

/**
 * RunnerEnv created 10.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
// @Component
@Log
public class RunnerEnv  implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		Map<String, String> env = AUtils.sortByKeys(System.getenv());
		env.forEach((key,value) -> log.info( key + ": " + value));
	}

}
