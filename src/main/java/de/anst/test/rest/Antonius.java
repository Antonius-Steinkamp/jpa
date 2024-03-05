/**
 * Antonius.java created 10.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.test.rest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import de.anst.AUtils;

/**
 * Antonius created 10.02.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
//@RestController
// @RequestMapping("/api/antonius")
public class Antonius {

	@GetMapping(value = "/vcf", produces = "text/vCard")
	public ResponseEntity<ByteArrayResource> downloadVcf() {
		// Hier kannst du deine Logik zum Laden der Datei implementieren
		byte[] fileContent = AUtils.readStaticFile("antonius.vcf").getBytes();

		// Erstelle eine ByteArrayResource aus dem Dateiinhalt
		ByteArrayResource resource = new ByteArrayResource(fileContent);

		// Erstelle die HttpHeaders, um den MIME-Typ und den Dateinamen festzulegen
		HttpHeaders headers = new HttpHeaders();
	//	headers.setContentType(new MediaType("text", "vcard"));
		headers.setContentDispositionFormData("attachment", "antonius.vcf");

		// Baue die ResponseEntity mit der ByteArrayResource, HttpHeaders und dem
		// HTTP-Status auf
		return ResponseEntity.ok().headers(headers).contentLength(fileContent.length).body(resource);
	}

	@GetMapping("/png")
	public ResponseEntity<ByteArrayResource> downloadPicture() {
		// Hier kannst du deine Logik zum Laden der Datei implementieren
		byte[] fileContent = AUtils.readStaticFile("/static/antonius.vcf").getBytes();

		// Erstelle eine ByteArrayResource aus dem Dateiinhalt
		ByteArrayResource resource = new ByteArrayResource(fileContent);

		// Erstelle die HttpHeaders, um den MIME-Typ und den Dateinamen festzulegen
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "vcard"));
		// headers.setContentDispositionFormData("attachment", "example-file.txt");

		// Baue die ResponseEntity mit der ByteArrayResource, HttpHeaders und dem
		// HTTP-Status auf
		return ResponseEntity.ok().headers(headers).contentLength(fileContent.length).body(resource);
	}


}

