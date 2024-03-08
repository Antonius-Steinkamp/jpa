/**
 * FisService.java created 05.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.fis;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.anst.AUtils;
import de.anst.vpc.pearl.Pearl;
import de.anst.vpc.pearltype.PearlType;
import de.anst.vpc.segment.meldepunkt.Meldepunkt;
import lombok.extern.java.Log;


/**
 * FisService created 05.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */

@RestController
@RequestMapping("/api/fis")
@Log
public class FisService {
	private final PearlType.Persister pearlTypepersister;
	private final Meldepunkt.Persister mpPersister;
	private final FisMessage.Persister fisPersister;
	private final Pearl.Persister pearlPersister;
	
	public FisService(PearlType.Persister persister, Meldepunkt.Persister mpPersister, FisMessage.Persister fisPersister, Pearl.Persister pearlPersister) {
		super();
		this.pearlTypepersister = persister;
		this.mpPersister = mpPersister;
		this.fisPersister = fisPersister;
		this.pearlPersister = pearlPersister;
	}
	
	@PostMapping(value = "/message")
	public void meldung(@RequestBody FisMessageData message) {
		BAD_REQUEST.when(message == null, "No message");
		
		BAD_REQUEST.when(message.getType() == null, "type is null");

		List<PearlType> pearlTypes = pearlTypepersister.findByName(message.getType());

		BAD_REQUEST.when(!AUtils.hasValue(pearlTypes), "type " + message.getType() + " not found");


		PearlType pearlType = pearlTypes.get(0);

		BAD_REQUEST.when(message.getMp() == null, "mp is null");
		
		List<Meldepunkt> mps = mpPersister.findByName(message.getMp());

		BAD_REQUEST.when(!AUtils.hasValue(mps), "mp " + message.getMp() + " not found");
		
		Meldepunkt mp  = mps.get(0);

		String mpName = message.getMp();

		BAD_REQUEST.when(mpName == null, "name is null");

		
		LocalDateTime mpDate = message.getMpDate();

		BAD_REQUEST.when(mpDate == null, "mpDate is null");

	
		List<FisMessage> allreadyKnown = fisPersister.findByNameAndMpName(message.getName(), message.getMp());

		BAD_REQUEST.when(AUtils.hasValue(allreadyKnown), "message allready there");
		
		FisMessage msg = new FisMessage(message);
		fisPersister.add(msg);
		
		Pearl pearl = new Pearl();
		pearl.setName(message.getName());
		pearl.setType(pearlType);
		pearl.setSegment(mp.getSegment());
		pearl.setPos(mp.getSegment().getMaxPos()+1);
		
		mp.getSegment().setMaxPos(mp.getSegment().getMaxPos()+1);
		mpPersister.update(mp);
		
		pearlPersister.add(pearl);
		
		mp.setPearl(pearl);
		mpPersister.update(mp);
	}
	
	@GetMapping(value = "/types")
	public List<String> getTypes() {
		log.info("Send all types");
		
		return pearlTypepersister.findNames();
	}

	private static class BAD_REQUEST {
		public static void when(boolean b, String badNews) {
			if (b) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, badNews);
			}
		}
	}
}
