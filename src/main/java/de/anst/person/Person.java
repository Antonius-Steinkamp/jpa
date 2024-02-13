package de.anst.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.extern.java.Log;

@Entity
@Table(name = "persons")
@FieldNameConstants
@NoArgsConstructor
public class Person extends AbstractEntity {

	@Getter @Setter
	@NotNull
    private String firstName;
	
	@Getter @Setter
    private String lastName;

	@Email
	@Getter @Setter
	@NotNull
    private String email;
    
	@Getter @Setter
	private String phone;

	@Getter @Setter
    private LocalDate dateOfBirth;
	
	@Getter @Setter
    private String occupation;

	@Getter @Setter
	private String role;

	@Getter @Setter
    private boolean important;

	
	public Person(int u1, int u2, String firstName, String lastName, String email, String phone, String dob, String occupation, String role, boolean important) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.occupation = occupation;
		this.role = role;
		this.important = important;
	}
	

	@Log
	public static class Persister extends JpaCrudService<Person, Long, PersonRepository> {

		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = -9163299082925807553L;

		public Persister(PersonRepository repository) {
			super(repository);
			if (repository.count() == 0) {
				initData(repository);
				log.info("Initialize " + Persister.class.getName());
			}
		}
		
		private void initData(PersonRepository repo) {
				Person[] persons = {
				 new Person (1, 1, "Eula","Lane","eula.lane@jigrormo.ye","(762) 526-5961","1954-05-09","Insurance Clerk","Worker",false)
				, new Person (1, 2,"Barry","Rodriquez","barry.rodriquez@zun.mm","(267) 955-5124","2013-05-09","Mortarman","Manager",false)
				, new Person (1, 3,"Eugenia","Selvi","eugenia.selvi@capfad.vn","(680) 368-2192","1973-04-24","Beer Coil Cleaner","External",false)
				, new Person (1, 4,"Alejandro","Miles","alejandro.miles@dec.bn","(281) 301-2039","2013-06-11","Scale Attendant","Worker",false)
				, new Person (1, 5,"Cora","Tesi","cora.tesi@bivo.yt","(600) 616-7955","1971-08-09","Clinical Audiologist","Supervisor",false)
				, new Person (1, 6,"Marguerite","Ishii","marguerite.ishii@judbilo.gn","(882) 813-1374","1937-05-06","Parking Meter Collector","Supervisor",false)
				, new Person (1, 7,"Mildred","Jacobs","mildred.jacobs@joraf.wf","(642) 665-1763","1966-12-09","Business Unit Manager","Manager",false)
				, new Person (1, 8,"Gene","Goodman","gene.goodman@kem.tl","(383) 458-2132","2009-10-19","Technical Communicator","External",false)
				, new Person (1, 9,"Lettie","Bennett","lettie.bennett@odeter.bb","(769) 335-6771","1958-12-24","Correctional Officer Sergeant","Worker",false)
				, new Person (1, 10,"Mabel","Leach","mabel.leach@lisohuje.vi","(803) 586-8035","1945-11-30","Food Chemist","Supervisor",false)
				, new Person (1, 11,"Jordan","Miccinesi","jordan.miccinesi@duod.gy","(531) 919-2280","1982-01-11","Signals Intelligence/Electronic Warfare Chief","Manager",false)
				, new Person (1, 12,"Marie","Parkes","marie.parkes@nowufpus.ph","(814) 667-8937","1942-11-12","Language Pathologist","External",false)
				, new Person (1, 13,"Rose","Gray","rose.gray@kagu.hr","(713) 311-8766","1957-11-11","Wildlife Officer","Worker",false)
				, new Person (1, 14,"Garrett","Stokes","garrett.stokes@fef.bg","(381) 421-2371","2008-08-22","Bindery Machine Operator","Manager",false)
				, new Person (1, 15,"Barbara","Matthieu","barbara.matthieu@derwogi.jm","(940) 463-7299","1929-08-18","Instructional Aide","External",false)
				, new Person (1, 16,"Jean","Rhodes","jean.rhodes@wehovuce.gu","(777) 435-9570","1949-01-25","Clinical Psychiatrist","Worker",false)
				, new Person (1, 17,"Jack","Romoli","jack.romoli@zamum.bw","(517) 393-9630","1974-11-21","Mortician Investigator","Supervisor",false)
				, new Person (1, 18,"Pearl","Holden","pearl.holden@dunebuh.cr","(711) 904-3669","1949-03-18","Rod Buster Helper","Manager",false)
				, new Person (1, 19,"Belle","Montero","belle.montero@repiwid.si","(935) 404-4792","1932-04-10","Classroom Aide","External",false)
				, new Person (1, 20,"Olive","Molina","olive.molina@razuppa.ga","(935) 267-8492","1933-10-21","Traditional Chinese Herbalist","Worker",false)
				, new Person (1, 21,"Minerva","Todd","minerva.todd@kulmenim.ad","(763) 948-4815","1950-03-24","Electronic Drafter","Supervisor",false)
				, new Person (1, 22,"Bobby","Pearson","bobby.pearson@ib.kg","(238) 240-2561","2014-08-16","Vault Teller","Worker",true)
				, new Person (1, 23,"Larry","Ciappi","larry.ciappi@ba.lk","(410) 257-1723","1995-09-01","Fire Sprinkler Installer","Supervisor",false)
				, new Person (1, 24,"Ronnie","Salucci","ronnie.salucci@tohhij.lv","(566) 726-3346","1974-03-05","Brewery Pumper","Manager",false)
				, new Person (1, 25,"Walter","Grossi","walter.grossi@tuvo.sa","(416) 906-7221","1987-04-23","Kitchen Chef","External",false)
				, new Person (1, 26,"Frances","Koopmans","frances.koopmans@foga.tw","(611) 712-1562","1966-06-14","Medical Esthetician","Worker",false)
				, new Person (1, 27,"Frances","Fujimoto","frances.fujimoto@uswuzzub.jp","(919) 887-8542","1935-11-18","Auto Tire Worker","Supervisor",false)
				, new Person (1, 28,"Olivia","Vidal","olivia.vidal@hivwerip.vc","(982) 684-7650","1933-02-25","Semi-Truck Driver","Manager",false)
				, new Person (1, 29,"Edna","Henry","edna.henry@gugusu.rw","(811) 931-8202","1947-01-09","Command And Control","External",false)
				, new Person (1, 30,"Lydia","Brun","lydia.brun@zedekak.md","(927) 400-3928","1929-02-21","Drywall Hanger","Worker",false)
				, new Person (1, 31,"Jay","Blake","jay.blake@ral.mk","(365) 345-1498","2009-03-11","Real Property Evaluator","Manager",false)
				, new Person (1, 32,"Isabel","Serafini","isabel.serafini@turuhu.bh","(656) 968-9869","1973-03-19","Human Performance Professor","Manager",false)
				, new Person (1, 33,"Rebecca","Carter","rebecca.carter@omjo.et","(739) 612-6585","1958-10-06","V/Stol Landing Signal Officer","External",false)
				, new Person (1, 34,"Maurice","Fabbrini","maurice.fabbrini@rig.bh","(485) 521-2687","1992-08-11","Air Control/Anti-Air Warfare Officer","Supervisor",false)
				, new Person (1, 35,"Ollie","Turnbull","ollie.turnbull@sicewap.org","(835) 620-3330","1944-04-07","General Superintendent","Manager",false)
				, new Person (1, 36,"Jerry","Hopkins","jerry.hopkins@fo.mh","(211) 851-5960","2014-05-02","Child Protective Services Social Worker","External",true)
				, new Person (1, 37,"Nora","Lyons","nora.lyons@gegijap.na","(811) 311-5257","1945-02-13","Lens Grinder and Polisher","Worker",false)
				, new Person (1, 38,"Anne","Weiß","anne.weiß@kuvesa.pe","(843) 836-3759","1940-09-01","Civil Engineering Professor","Worker",false)
				, new Person (1, 39,"Louise","Gauthier","louise.gauthier@lapahu.mt","(913) 235-1856","1929-12-12","Mobile Home Servicer","Supervisor",false)
				, new Person (1, 40,"Lloyd","Fani","lloyd.fani@zev.ru","(467) 487-7239","1991-11-16","Floor Refinisher","Supervisor",false)
				, new Person (1, 41,"Maud","Dunn","maud.dunn@nabeaga.ni","(724) 340-3634","1955-02-07","Senior Sales Associate","Manager",false)
				, new Person (1, 42,"Henry","Gigli","henry.gigli@kaot.ps","(413) 229-8428","1988-04-11","Tile Designer","Worker",false)
				, new Person (1, 43,"Virgie","Werner","virgie.werner@tawuctuj.cf","(886) 292-9749","1941-05-21","Econometrics Professor","Supervisor",false)
				, new Person (1, 44,"Gregory","Cozzi","gregory.cozzi@eh.ru","(418) 472-1239","1994-03-14","Basketball Player","Manager",false)
				, new Person (1, 45,"Lucinda","Gil","lucinda.gil@fajjusuz.kr","(961) 233-3461","1934-03-05","Indirect Fire Infantryman","External",false)
				, new Person (1, 46,"Gertrude","Verbeek","gertrude.verbeek@pave.cc","(605) 226-4037","1964-03-18","Licensed Esthetician","Worker",false)
				, new Person (1, 47,"Mattie","Graham","mattie.graham@ispaviw.gt","(719) 765-1705","1957-01-14","Antisubmarine Warfare Intelligence Officer","Supervisor",false)
				, new Person (1, 48,"Bryan","Shaw","bryan.shaw@ha.ee","(232) 228-5539","2018-09-10","Research Assistant","Manager",true)
				, new Person (1, 49,"Essie","Adams","essie.adams@iliat.cw","(768) 554-8377","1958-03-22","Cigar Roller","External",false)
				, new Person (1, 50,"Gary","Osborne","gary.osborne@do.ga","(311) 731-7079","2009-01-08","Customer Support Representative","Worker",false)
				, new Person (1, 51,"Richard","Silva","richard.silva@wi.lc","(207) 554-6244","2014-08-20","Programmer","Manager",true)
				, new Person (1, 52,"Dustin","Pestelli","dustin.pestelli@iwage.la","(558) 913-2855","1977-12-24","Global Engineering Manager","Manager",false)
				, new Person (1, 53,"Henrietta","Hilton","henrietta.hilton@joopoju.pn","(832) 759-6654","1943-10-18","Telegraph and Teletype Operator","External",false)
				, new Person (1, 54,"Francisco","Giordano","francisco.giordano@gojawu.tn","(482) 736-8079","1988-03-26","Hairpiece Stylist","Manager",false)
				, new Person (1, 55,"Cynthia","Sardi","cynthia.sardi@afigoh.mm","(677) 345-2680","1973-09-14","Tobacco Buyer","External",false)
				, new Person (1, 56,"Lula","Testi","lula.testi@benom.tj","(610) 374-7581","1971-07-12","Marine Steamfitter","Worker",false)
				, new Person (1, 57,"Bess","Lucas","bess.lucas@jevakbe.cd","(982) 583-8067","1928-12-20","Attending Anesthesiologist","Supervisor",false)
				, new Person (1, 58,"Linnie","Driessen","linnie.driessen@darhow.tr","(680) 266-3167","1967-09-20","Certified Indoor Environmentalist","External",false)
				, new Person (1, 59,"Eva","Tesi","eva.tesi@dupid.cf","(611) 955-4652","1971-08-02","Land Management Forester","Worker",false)
				, new Person (1, 60,"Augusta","Sakai","augusta.sakai@comouc.ee","(940) 714-8088","1936-05-09","Digital Proofing and Platemaker","Worker",false)
				, new Person (1, 61,"Mathilda","Schwarz","mathilda.schwarz@igunisi.ao","(868) 481-5125","1941-09-02","Public Health Veterinarian","Manager",false)
				, new Person (1, 62,"Joe","Riley","joe.riley@pe.vu","(225) 395-2772","2017-02-10","Statement Processor","External",true)
				, new Person (1, 63,"Leon","McGee","leon.mcgee@puk.se","(365) 837-6888","2010-11-27","Computer Applications Developer","Worker",false)
				, new Person (1, 64,"Florence","Viviani","florence.viviani@vegub.no","(606) 352-8734","1969-09-19","African History Professor","Supervisor",false)
				, new Person (1, 65,"Lee","Miceli","lee.miceli@rucwi.pf","(555) 800-7339","1981-12-23","Gastroenterology Professor","Manager",false)
				, new Person (1, 66,"Celia","Sodi","celia.sodi@agijit.iq","(657) 357-3671","1972-10-15","Laboratory Animal Caretaker","External",false)
				, new Person (1, 67,"Aaron","Misuri","aaron.misuri@loolu.lu","(523) 789-5485","1981-09-16","Water Pump Installer","Worker",false)
				, new Person (1, 68,"Fanny","Parkinson","fanny.parkinson@tupwovali.cw","(766) 966-7387","1949-06-28","Orthopedic Cast Specialist","Supervisor",false)
				, new Person (1, 69,"Phoebe","Vitale","phoebe.vitale@hidge.fo","(672) 613-2954","1969-10-29","Budget Coordinator","Manager",false)
				, new Person (1, 70,"Edith","Brennan","edith.brennan@liowci.ir","(803) 549-9387","1948-04-09","Railroad Engineer","Manager",false)
				, new Person (1, 71,"Jeremy","Marilli","jeremy.marilli@vesa.pf","(526) 435-1819","1983-10-13","Chief Projectionist","External",false)
				, new Person (1, 72,"Kathryn","Huet","kathryn.huet@wupikdoh.by","(937) 855-5936","1927-04-01","Telecasting Engineer","Worker",false)
				, new Person (1, 73,"Lelia","Matsuo","lelia.matsuo@dajsiphaj.az","(960) 335-6192","1935-04-12","Drama Therapist","Supervisor",false)
				, new Person (1, 74,"Virginia","Woods","virginia.woods@soofpe.ht","(735) 809-2611","1955-01-18","General Superintendent","Manager",false)
				, new Person (1, 75,"Sally","Aoki","sally.aoki@aruzusjas.tc","(857) 797-7918","1937-03-21","Technical Communicator","External",false)
				, new Person (1, 76,"Isabelle","de Ridder","isabelle.deridder@ufeco.in","(659) 331-1543","1963-01-05","Leisure Studies Professor","Worker",false)
				, new Person (1, 77,"Rosie","Murphy","rosie.murphy@uneehi.id","(759) 639-8597","1958-07-08","Air Conditioning Service Technician","Supervisor",false)
				, new Person (1, 78,"Lou","Meyer","lou.meyer@hahinaba.gm","(942) 352-4854","1929-04-02","Business Unit Manager","External",false)
				, new Person (1, 79,"Rodney","Love","rodney.love@zun.ph","(247) 867-8287","2012-07-27","Job Estimator","Worker",false)
				, new Person (1, 80,"Kenneth","Bianchini","kenneth.bianchini@jo.ws","(302) 793-9936","2001-07-19","Correctional Officer Sergeant","Worker",false)
				, new Person (1, 81,"Essie","Dietrich","essie.dietrich@goltuefo.mn","(861) 740-6628","1939-03-21","Parking Meter Collector","Supervisor",false)
				, new Person (1, 82,"Leila","Simon","leila.simon@lupuwuzo.gw","(953) 866-9992","1930-12-16","Signals Intelligence/Electronic Warfare Chief","External",false)
				, new Person (1, 83,"Eva","Pierre","eva.pierre@reduzris.ee","(915) 491-8384","1927-12-28","Clinical Psychiatrist","Worker",false)
				, new Person (1, 84,"Landon","Moretti","landon.moretti@pubsav.sk","(584) 909-6235","1981-04-01","Wildlife Officer","Supervisor",false)
				, new Person (1, 85,"Mittie","Sardi","mittie.sardi@lullip.nf","(673) 849-4256","1973-09-09","Food Chemist","Manager",false)
				, new Person (1, 86,"Corey","McDaniel","corey.mcdaniel@aba.tc","(268) 208-9643","2012-11-13","Advanced Foreign Counterintelligence Specialist (Afcs)","Supervisor",false)
				, new Person (1, 87,"Hester","Stein","hester.stein@kettujwo.eu","(873) 489-6641","1939-09-06","Forest Fire Officer","Manager",false)
				, new Person (1, 88,"Danny","Lowe","danny.lowe@ju.sd","(243) 974-5539","2013-12-19","Catalogue Illustrator","External",true)
				, new Person (1, 89,"Lillie","Winter","lillie.winter@vioburez.vi","(816) 699-1291","1945-11-13","Knife Grinder","Worker",false)
				, new Person (1, 90,"Brandon","Borchi","brandon.borchi@ig.al","(319) 401-1090","2000-09-04","Neuropsychiatrist","Supervisor",false)
				, new Person (1, 91,"Isaac","Bernardi","isaac.bernardi@omu.bj","(359) 691-6408","2002-02-15","Bottle Packer","Manager",false)
				, new Person (1, 92,"Clyde","Crawford","clyde.crawford@luw.dz","(273) 892-4646","2019-02-18","Special Education Kindergarten Teacher","External",true)
				, new Person (1, 93,"Paul","Sherman","paul.sherman@pi.cf","(304) 610-2881","2008-07-27","Commercial Art Instructor","Worker",false)
				, new Person (1, 94,"Craig","Russell","craig.russell@zu.nz","(237) 969-2900","2020-05-24","Ferryboat Captain","Supervisor",true)
				, new Person (1, 95,"John","Sutton","john.sutton@ag.ee","(207) 424-6468","2013-08-05","School Social Worker","Manager",false)
				, new Person (1, 96,"Francisco","Formigli","francisco.formigli@fopav.tn","(481) 661-8179","1990-07-20","Joint Terminal Attack Controller","External",false)
				, new Person (1, 97,"Gary","Baker","gary.baker@ji.cf","(212) 510-3444","2022-08-09","VP Sales","Worker",true)
				, new Person (1, 98,"Earl","Giovannoni","earl.giovannoni@lojet.ge","(433) 862-3076","1987-12-18","Auto Service Station Attendant","Manager",false)
				, new Person (1, 99,"Helen","Zanieri","helen.zanieri@ukve.tn","(619) 506-4452","1969-05-01","Healthcare Social Worker","External",false)
				, new Person (1, 100,"Agnes","Toccafondi","agnes.toccafondi@viipo.ae","(616) 688-6883","1971-05-21","Comedian","External",false)
				};
			for( Person person: persons) {
				repo.save(person);
			}
		}
		
	}
}
