package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import sdaac.wym.app.Service.KPI.KPIManager;
/**
 * ME Attach download
 * @author SA1KV5
 *
 */
public class MeAttachDownload {
	
	private Logger log = Logger.getLogger(MeAttachDownload.class); 
	
	void download(String[] fileNames){
		String source = "E:/target/uploadFolder/";
		for(String fileName : fileNames) {
			File in = new File(source+fileName);
			File out = new File("E://meattach//"+fileName);
			if(!in.exists()) {
				log.error("file is not exist !");
			}else {
				if(!out.exists()) 
					out.mkdirs();
				File[] files = in.listFiles();
				FileInputStream fin = null;
				FileOutputStream fout = null;
				for(File file : files) {
					if(file.isFile()) {
						try {
							fin = new FileInputStream(file);
							fout = new FileOutputStream(new File("E://meattach//"+fileName+"/"+file.getName()));
							byte[] b = new byte[1024*5];
							int c;
								while((c=fin.read(b))!=-1) {
									fout.write(b,0,c);
								}
								fin.close();
								fout.flush();
								fout.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		MeAttachDownload manager = new MeAttachDownload();
		

		String[] fileNames = {"10110811500124",
				"10110828331423",
				"10110931301357",
				"10110931221384",
				"10110933161384",
				"10110925561278",
				"10111036401274",
				"10111039091274",
				"10111050031274",
				"10111108181323",
				"10111138400221",
				"10111223191384",
				"10111234001068",
				"10111208130567",
				"10111215300567",
				"10111229401324",
				"10111248140124",
				"10111525560227",
				"10111533001324",
				"10111534270124",
				"10111513430221",
				"10111659581403",
				"10111610171403",
				"10111611321403",
				"10111630090085",
				"10111631041403",
				"10111101120085",
				"10111111240221",
				"10111209050567",
				"10111533490227",
				"10111510221324",
				"10111516421324",
				"10111517401324",
				"10111648051267",
				"10111655281267",
				"10111637021403",
				"10111649030227",
				"10111658060227",
				"10111708200227",
				"10111718360567",
				"10111718510567",
				"10111739111061",
				"10111742450124",
				"10111741401311",
				"10111754120567",
				"10111714541061",
				"10111848370567",
				"10111810140567",
				"10111847480263",
				"10111902570567",
				"10111910210567",
				"10111913270567",
				"10111831000227",
				"10111858141372",
				"10111851030227",
				"10111914440567",
				"10111935561324",
				"10112242371403",
				"10112213141357",
				"10112249530267",
				"10112216490267",
				"10112238070267",
				"10112232100267",
				"10112300290567",
				"10112304290203",
				"10112346200567",
				"10112352310567",
				"10112305430567",
				"10112358491276",
				"10112356550124",
				"10112307371017",
				"10112312111017",
				"10112314101017",
				"10112415371268",
				"10112409281384",
				"10112415471403",
				"10112430480263",
				"10112411341423",
				"10112424030567",
				"10112232460085",
				"10112244270267",
				"10112212130267",
				"10112221011384",
				"10112225521017",
				"10112230031017",
				"10112356160203",
				"10112524340227",
				"10112542431403",
				"10112543520227",
				"10112535331311",
				"10112545201311",
				"10112545591268",
				"10112634201384",
				"10112405001278",
				"10112414021278",
				"10112557010567",
				"10112518300383",
				"10112507070227",
				"10112613110124",
				"10112632241268",
				"10112606320567",
				"10091701591423",
				"10091300050567",
				"10091728241061",
				"10091740081423",
				"10091756401061",
				"10091705301061",
				"10091914091403",
				"10091914291403",
				"10091949151440",
				"10091949211329",
				"10091922130227",
				"10092020021423",
				"10092027401423",
				"10092011011047",
				"11041326210227",
				"11041455360293",
				"11041419451061",
				"11041413421456",
				"11041507541403",
				"11041557271278",
				"11041607140267",
				"11041629350267",
				"11041702330227",
				"11041820321403",
				"11041827350267",
				"11041447000227",
				"11041401121061",
				"11041425511456",
				"11041511471473",
				"11041543371461",
				"11041535381423",
				"11041820050227",
				"11041836031403",
				"11042005130567",
				"11042055441061",
				"11042027241017",
				"11042054101061",
				"11042144521403",
				"11042139321473",
				"11042254501403",
				"11042245531403",
				"11042216061473",
				"11042237081473",
				"11042237171473",
				"11042201250293",
				"11042256411278",
				"11042548121384",
				"11041827121279",
				"11041842451403",
				"11041849221379",
				"11041949461384",
				"11041905040221",
				"11041925060567",
				"11041949251403",
				"11042258510085",
				"10092015380227",
				"10092108360383",
				"10092738411047",
				"10092716531047",
				"10092852011276",
				"10092852121276",
				"10092856380227",
				"10092829200227",
				"10092908101267",
				"10092942081061",
				"10093049100227",
				"10093049400227",
				"10100837441384",
				"10100802271384",
				"10100850321267",
				"10100856161267",
				"10100858441267",
				"10100806520124",
				"10101114010227",
				"10101115160227",
				"10091412041278",
				"10091420501278",
				"10091407581357",
				"10091515211061",
				"10091515511061",
				"10091516031061",
				"10091516341061",
				"10091527451061",
				"10091502160383",
				"10091652091017",
				"10091654591017",
				"10101106151267",
				"10101414221276",
				"10101417291276",
				"10101809401423",
				"10101840431268",
				"10101947521329",
				"10101956061403",
				"10102033231403",
				"10102133201423",
				"10102807050124",
				"10102823451047",
				"10102827351047",
				"10110200281329",
				"10110233091061",
				"10110253060567",
				"10110221020567",
				"10091936340227",
				"10091936410227",
				"10091949321440",
				"10092023231047",
				"10092428191047",
				"10092437201047",
				"10092531331329",
				"10100840081384",
				"10100856050124",
				"10101155430124",
				"10101259240203",
				"10101236371324",
				"10101239121324",
				"10101224331403",
				"10101302191017",
				"10101313301403",
				"10101313341403",
				"10101434180227",
				"10101402501357",
				"10101551571311",
				"10101558020124",
				"10101940291017",
				"10101942331357",
				"10102058511268",
				"10102006051403",
				"10102048421047",
				"10102044111061",
				"10102029071403",
				"10102146431278",
				"10102135491324",
				"10102136121324",
				"10102139031324",
				"10102115001324",
				"10102121591324",
				"10102123181274",
				"10102124291274",
				"10092711551047",
				"10092724381324",
				"10092725061324",
				"10100941391403",
				"10100941431403",
				"10101417081403",
				"10101525051403",
				"10101502580263",
				"10102258140227",
				"10102258480227",
				"10102215131329",
				"10102225581267",
				"10102237550383",
				"10102235091017",
				"10102532010227",
				"10102623371384",
				"10102632371384",
				"10102718051403",
				"10102741151278",
				"10102823151423",
				"10102834120227",
				"10102821221047",
				"10102835530124",
				"10102923311403",
				"10102924081403",
				"10110143240567",
				"10110104490567",
				"10110115260567",
				"10110125190567",
				"10101359401017",
				"10101350310124",
				"10101311120124",
				"10102145041324",
				"10102118120227",
				"10102202090227",
				"10102243020227",
				"10102518561061",
				"10102521531061",
				"10102625421384",
				"10102630321384",
				"10102619400221",
				"10102721161423",
				"10102930551423",
				"10110142520124",
				"10110240331061",
				"10110243451061",
				"10110359051268",
				"10110305150567",
				"10110313400567",
				"10110313591267",
				"10110316580567",
				"10110455321110",
				"10110456301110",
				"10110456401110",
				"10110430271324",
				"10110403301324",
				"10110403481324",
				"10110559320263",
				"10110554110085",
				"10110843581267",
				"10110838250085",
				"10110848001017",
				"10110849381017",
				"10110802430124",
				"10110956401323",
				"10111037591384",
				"10112940590227",
				"10113041550203",
				"10113055070567",
				"10113048521384",
				"10120109541278",
				"10120122420567",
				"10120123301278",
				"10120154251061",
				"10120149311329",
				"10120118381403",
				"10120151501403",
				"10120129241403",
				"10120135590567",
				"10112908090567",
				"10120137160567",
				"10113048330567",
				"10113008310567",
				"10113009170227",
				"10113007570267",
				"10113022250267",
				"10113025580267",
				"10113020240227",
				"10120114241278",
				"10120122321423",
				"10120127101329",
				"10120137091403",
				"10120137341403",
				"10120144301329",
				"10120149491274",
				"10120152391274",
				"10120203380567",
				"10120227581456",
				"10120236291384",
				"10120221020227",
				"10120224530227",
				"10120220370567",
				"10120319470263",
				"10120355311329",
				"10120306481329",
				"10120306541329",
				"10120324191329",
				"10120354441061",
				"10120301061017",
				"10120655320227",
				"10120658480383",
				"10120636440227",
				"10120608201278",
				"10120616191329",
				"10120636461329",
				"10120642420567",
				"10120718010203",
				"10120755351384",
				"10120714501017",
				"10120839140203",
				"10120823250227",
				"10120834470227",
				"10120952130203",
				"10120900181403",
				"10120939321324",
				"10120936080267",
				"10120954361379",
				"10120908541379",
				"10120911461379",
				"10120919011379",
				"10120923491379",
				"10120927061379",
				"10120900391457",
				"10120922161267",
				"10120937101379",
				"10121035141384",
				"10121037170124",
				"10121047261379",
				"10121041471379",
				"10121323561324",
				"10121329121017",
				"10121347141403",
				"10121325281403",
				"10121340110267",
				"10121431591276",
				"10121407100567",
				"10121431251017",
				"10121514071457",
				"10121549081047",
				"10121549311047",
				"10121510461047",
				"10121513361047",
				"10121500140124",
				"10121525111276",
				"10121547351276",
				"10121552421276",
				"10121641121324",
				"10121611241017",
				"10121754580227",
				"10121757301061",
				"10121711520227",
				"10121739361274",
				"10121756230227",
				"10121738111061",
				"10121728370227",
				"10122033301457",
				"10122049141267",
				"10122018311324",
				"10122034091403",
				"10121444300203",
				"10121410230227",
				"10121423290567",
				"10121433490567",
				"10121403020567",
				"10122042481267",
				"10122008061423",
				"10122012551274",
				"10122001121423",
				"10122157281047",
				"10122249211324",
				"10122309520124",
				"10122304201047",
				"10121527390263",
				"10121658201274",
				"10121547281276",
				"10121514241276",
				"10121602021274",
				"10121632441324",
				"10122421011324",
				"10122713500567",
				"10122743201017",
				"10122844361279",
				"10122118591457",
				"10122129211278",
				"10122114091323",
				"10122106071061",
				"10122345491384",
				"10122310211457",
				"10122310441279",
				"10122319141279",
				"10122300110567",
				"10122412041107",
				"10122412101107",
				"10122831291311",
				"10122120511403",
				"10122121381403",
				"10122222001047",
				"10122222301047",
				"10122319290567",
				"10122428451324",
				"10122454250263",
				"10122953431276",
				"10123019521268",
				"10123034281268",
				"10123042391268",
				"10123050041403",
				"10123031341276",
				"10123048491047",
				"11010444531403",
				"11010457341403",
				"11010413091403",
				"11010441130124",
				"10122851251276",
				"10122903420263",
				"10122953011456",
				"10122953131456",
				"11010428201403",
				"11010429060124",
				"11010529451423",
				"11010625351357",
				"10123045221268",
				"10123102571403",
				"11010656341457",
				"11010624111446",
				"11010628481446",
				"11010705591384",
				"11011024160124",
				"11011123061384",
				"11010632361324",
				"11010657591017",
				"11011228010263",
				"11011232510221",
				"11011256011329",
				"11011429521457",
				"11011401041107",
				"11011741510227",
				"11011731170267",
				"11011736360267",
				"11010710071384",
				"11010742421384",
				"11011044171384",
				"11011003320124",
				"11011020010124",
				"11011049061324",
				"11011114581324",
				"11011116161324",
				"11011100070124",
				"11011306140124",
				"11011332391017",
				"11011451590124",
				"11011459541403",
				"11011407251403",
				"11011457211384",
				"11011511081047",
				"11011727561461",
				"11011702011278",
				"11011704371278",
				"11011715201279",
				"11011725531456",
				"11011704290227",
				"11011753091017",
				"11011757101017",
				"11011706371017",
				"11011717151017",
				"11011826301279",
				"11011828141279",
				"11011853091456",
				"11011820141463",
				"11011819331267",
				"11011806141403",
				"11012004470267",
				"11012005070267",
				"11012155541457",
				"11013011361279",
				"11013013281279",
				"11013111510263",
				"11012059311107",
				"11012514380263",
				"11012522120263",
				"11012542170263",
				"11012606481403",
				"11012628520263",
				"11012655511423",
				"11012603140227",
				"11012705360124",
				"11012729330085",
				"11013114040263",
				"11020133591061",
				"11020946441047",
				"11020929260227",
				"11020918561463",
				"11020919071463",
				"11012441260567",
				"11012516101107",
				"11012524460263",
				"11012823091457",
				"11012802440567",
				"11012817041311",
				"11012809581276",
				"11013042411463",
				"11013017351279",
				"11013020121279",
				"11013021011279",
				"11013048540203",
				"11021042381278",
				"11021109320293",
				"11021109500293",
				"11012152370124",
				"11012448260124",
				"11012406331457",
				"11012502061107",
				"11012516051403",
				"11012632241457",
				"11012711280124",
				"11021250230567",
				"11021255190567",
				"11021404191279",
				"11021452101061",
				"11021425131279",
				"11013151001279",
				"11013151261279",
				"11020913360567",
				"11021050021279",
				"11021519431403",
				"11021533501456",
				"11021746380227",
				"11021830281473",
				"11021859571323",
				"11021835261017",
				"11021843591457",
				"11021818450293",
				"11021841310221",
				"11021845130221",
				"11022207181324",
				"11022209211324",
				"11022206370293",
				"11020902450227",
				"11022332451267",
				"11022335040293",
				"11022320091456",
				"11022344201456",
				"11022309331324",
				"11022431021403",
				"11022533090227",
				"11022519370567",
				"11022544250567",
				"11022553470567",
				"11022850100383",
				"11022856481403",
				"11022800451384",
				"11022808111403",
				"11022810501279",
				"11022820301403",
				"11022832140383",
				"11022844220383",
				"11022822381324",
				"11022827271324",
				"11022836241403",
				"11022855401457",
				"11022802540567",
				"11022839070221",
				"11021044210263",
				"11021107001463",
				"11021115410293",
				"11021144161268",
				"11021530260227",
				"11021536371456",
				"11021609421403",
				"11021610071230",
				"11022823021384",
				"11022800471461",
				"11030153211403",
				"11030226391403",
				"11030255301403",
				"11030256331403",
				"11030258181403",
				"11030218331403",
				"11022805451279",
				"11030125321379",
				"11030125541379",
				"11030147441379",
				"11030147481379",
				"11030151281061",
				"11030221551061",
				"11030306251403",
				"11030420141276",
				"11030417011456",
				"11030427040227",
				"11030723000567",
				"11030754121461",
				"11030700311274",
				"11030704161461",
				"11030845011456",
				"11030850071456",
				"11030827041329",
				"11021700010227",
				"11021840481461",
				"11022147421403",
				"11022145371457",
				"11022156401457",
				"11022255461047",
				"11022244391423",
				"11022252541047",
				"11022320440227",
				"11022325330227",
				"11022338130227",
				"11022419511311",
				"11022545330085",
				"11022514401403",
				"11022509120567",
				"11030947521276",
				"11030921391274",
				"11030925441329",
				"11030121501461",
				"11030158440203",
				"11031504111446",
				"11031552430124",
				"11031502500267",
				"11031510020267",
				"11031832140567",
				"11032138510567",
				"11030400340227",
				"11030421190227",
				"11030445251461",
				"11030457000263",
				"11030458421473",
				"11030444211324",
				"11031018581379",
				"11030719150567",
				"11030841141456",
				"11031046291017",
				"11031048391017",
				"11031158171267",
				"11031147010263",
				"11031450361384",
				"11031400081268",
				"11031402221268",
				"11031501240567",
				"11031651181047",
				"11031659331403",
				"11031600311047",
				"11031632301473",
				"11031625171473",
				"11031738471423",
				"11031713570267",
				"11031720040267",
				"11031721221061",
				"11031713151017",
				"11031716241017",
				"11031700281461",
				"11031718010263",
				"11031730221311",
				"11031701451047",
				"11031707191047",
				"11031826260267",
				"11031855250267",
				"11031852431461",
				"11032044280124",
				"11032129351107",
				"11032150341384",
				"11032151321423",
				"11031720350263",
				"11031819310267",
				"11032114131456",
				"11031812290267",
				"11032107071423",
				"11032121311017",
				"11032144181276",
				"11032144341276",
				"11032152141276",
				"11032245040567",
				"11032221370124",
				"11032250541267",
				"11032200001267",
				"11032254571456",
				"11032320161384",
				"11032316491276",
				"11041220461403",
				"11041257201403",
				"11041205060267",
				"11041358540124",
				"11041356201403",
				"11041307061403",
				"11041313281403",
				"11032351420267",
				"11032301161324",
				"11032410030085",
				"11032420311473",
				"11032511141461",
				"11032810430203",
				"11032848180567",
				"11032919121384",
				"11032919441279",
				"11032926490203",
				"11032927251279",
				"11032904170293",
				"11032217310221",
				"11032409220263",
				"11032457191446",
				"11032413390124",
				"11032802261274",
				"11032843501457",
				"11032923070567",
				"11032925261279",
				"11033038270267",
				"11033044260267",
				"11033049370267",
				"11033057260267",
				"11033034151473",
				"11033004351473",
				"11033147171456",
				"11032340140267",
				"11032344060267",
				"11032342030221",
				"11032346240221",
				"11032448461268",
				"11040249430263",
				"11040655311278",
				"11032821471423",
				"11032815340221",
				"11032920440567",
				"11033033160267",
				"11033022090567",
				"11033117121423",
				"11040129031446",
				"11033137011456",
				"11040102541423",
				"11040221350263",
				"11040634511278",
				"11040601561278",
				"11040628161278",
				"11040605170567",
				"11040620541403",
				"11040638421403",
				"11040630331403",
				"11040637171403",
				"11040627350263",
				"11040747100567",
				"11040714121403",
				"11040718001403",
				"11040801011268",
				"11040811401278",
				"11041149271473",
				"11041140511461",
				"11041131590293",
				"11041132330293",
				"11041230341457",
				"11040847460567",
				"11040838370085",
				"11040819010085",
				"11040826571110",
				"11040843411110",
				"11040822061278",
				"11040841291110",
				"11041151011278",
				"11041125321268",
				"11041133481268",
				"11041225051276",
				"11041235361276",
				"11041236431040",
				"11041211551276",
				"11041219471403",
				"11041305440124",
				"11041442241230",
				"11042541450203",
				"11042503271457",
				"11042524301384",
				"11042528581279",
				"11042535311279",
				"11042620530227",
				"11042622391278",
				"11042716000227",
				"11042723080567",
				"11042759040567",
				"11042759150567",
				"11042843331403",
				"11042844101403",
				"11051338021461",
				"11051601571403",
				"11051609411403",
				"11051636331456",
				"11051753201461",
				"11051703511110",
				"11051704381110",
				"11051732370203",
				"11051839301473",
				"11051808150124",
				"11051820431276",
				"11051838191457",
				"11051843331268",
				"11051938051403",
				"11051947281384",
				"11051903581107",
				"11051922290227",
				"11051954390227",
				"11051914150227",
				"11051913261276",
				"11052026201311",
				"11052001321384",
				"11052022440227",
				"11052033110227",
				"11042600451384",
				"11042602220124",
				"11042625420227",
				"11042708190567",
				"11042752071017",
				"11042856241061",
				"11042803411278",
				"11042922400203",
				"11042953471461",
				"11042917060227",
				"11042928350227",
				"11050333140267",
				"11050414100221",
				"11050416470221",
				"11050521451279",
				"11042902440227",
				"11042927410085",
				"11050301181403",
				"11050301401403",
				"11050329130124",
				"11050406130567",
				"11050432381403",
				"11050443490221",
				"11050451541279",
				"11050424281279",
				"11050550231403",
				"11050551280567",
				"11050555531274",
				"11050525390567",
				"11050527370567",
				"11050543081107",
				"11050518061017",
				"11050547451473",
				"11050502221107",
				"11050655261457",
				"11050606491457",
				"11050757511047",
				"11050915270227",
				"11050545350203",
				"11050559430203",
				"11050527011267",
				"11051607280227",
				"11051756251403",
				"11051702271403",
				"11051824580203",
				"11051824491276",
				"11050950421384",
				"11051026521274",
				"11051825081276",
				"11051052301107",
				"11051101000267",
				"11051127360267",
				"11051258081311",
				"11051207021311",
				"11051207151311",
				"11051236061457",
				"11051911420227",
				"11052002260227",
				"11052316041403",
				"11052349261457",
				"11052328131273",
				"11052328421273",
				"11052327161379",
				"11052327381379",
				"11052002191384",
				"11052459131384",
				"11052429381379",
				"11052413341279",
				"11052443170203",
				"11052529061110",
				"11052514021276",
				"11052537091276",
				"11052530440221",
				"11052523211403",
				"11052518141017",
				"11052558270383",
				"11052632461456",
				"11052645300124",
				"11052717061017",
				"11052730151273",
				"11052737561496",
				"11052749181496",
				"11052713341274",
				"11052732061061",
				"11052743381461",
				"11052710001461",
				"11052721021379",
				"11052640151276",
				"11052621081017",
				"11053024351273",
				"11053014241017",
				"11053036541017",
				"11053011321279",
				"11052648251379",
				"11052648351047",
				"11052613121230",
				"11053030141017",
				"11053039381279",
				"11053015431279",
				"11053122111268",
				"11060104110567",
				"11052717021274",
				"11060145271276",
				"11060149201276",
				"11060240171496",
				"11060240361496",
				"11060240411496",
				"11060254421496",
				"11060219161423",
				"11060223131423",
				"11060249500567",
				"11060722090567",
				"11053102500203",
				"11060108000567",
				"11060110280567",
				"11060141521276",
				"11060357531468",
				"11060333160227",
				"11060747460203",
				"11070754211504",
				"11070707020203",
				"11070708311504",
				"11070708421047",
				"11070726401276",
				"11070731261047",
				"11070738071273",
				"11070701090227",
				"11070720270227",
				"11070739200227",
				"11070700181276",
				"11070720031276",
				"11070704530124",
				"11070845281276",
				"11070806461276",
				"11070841401403",
				"11070848241403",
				"11070808461504",
				"11070806071457",
				"11070839371311",
				"11071042540227",
				"11071055540227",
				"11071011000227",
				"11071036230227",
				"11071056430227",
				"11071136080085",
				"11071114020124",
				"11071223511457",
				"11071325401267",
				"11071334300124",
				"11071303101267",
				"11071307001267",
				"11071148121311",
				"11071150181379",
				"11071221121107",
				"11071815590263",
				"11072817571423",
				"11072829581061",
				"11072800591061",
				"11080135371403",
				"11072929441110",
				"11080152141403",
				"11080108050124",
				"11080104201504",
				"11080153221061",
				"11080153331061",
				"11080348481267",
				"11080347431267",
				"11080304411017",
				"11080350100227",
				"11080308561047",
				"11080215040085",
				"11080222060085",
				"11080226320085",
				"11080352250567",
				"11080309551504",
				"11080306041457",
				"11080301211461",
				"11080447431446",
				"11080252371456",
				"11080503351457",
				"11080553101267",
				"11080539001061",
				"11080559301061",
				"11080835271047",
				"11080813511403",
				"11060846210124",
				"11060953201273",
				"11060916061061",
				"11061009030383",
				"11061338241473",
				"11060951180124",
				"11061355101017",
				"11061311201311",
				"11061335461423",
				"11061438031457",
				"11061403181403",
				"11061421291403",
				"11061433570227",
				"11061301461403",
				"11061319261276",
				"11061324540567",
				"11061330070567",
				"11061553080567",
				"11061330191457",
				"11061453151456",
				"11061411441456",
				"11061539370124",
				"11061546400567",
				"11061559061473",
				"11061524051268",
				"11061618351496",
				"11061632041504",
				"11061636081457",
				"11061638061457",
				"11061642381457",
				"11061759321273",
				"11063053260203",
				"11063051271457",
				"11063004101457",
				"11063009501496",
				"11063054491267",
				"11063009131496",
				"11070128510227",
				"11070127390124",
				"11070108170124",
				"11070133370293",
				"11070154400293",
				"11070130530293",
				"11070346370267",
				"11070440301017",
				"11070446091017",
				"11070537431276",
				"11070503041457",
				"11070513541457",
				"11070524011107",
				"11070532371107",
				"11070449341384",
				"11070622541520",
				"11070749341495",
				"11061601331267",
				"11061626131017",
				"11061628391107",
				"11061639561457",
				"11061645291457",
				"11062123400267",
				"11062127041110",
				"11062107271061",
				"11061731281273",
				"11061738371273",
				"11061745161403",
				"11062052061457",
				"11062053360186",
				"11062035171273",
				"11062009051457",
				"11062037310567",
				"11062049431457",
				"11062142391061",
				"11062254510227",
				"11062238371446",
				"11062314481498",
				"11062345031384",
				"11062355480227",
				"11062314591061",
				"11062331121107",
				"11062342060227",
				"11062432030267",
				"11062414010267",
				"11062419010267",
				"11062101241061",
				"11062119291403",
				"11062312201498",
				"11062359041498",
				"11062325541457",
				"11062329301107",
				"11062211210227",
				"11062241110227",
				"11062208150227",
				"11062222391495",
				"11062226511495",
				"11062244041495",
				"11062256181273",
				"11062228451495",
				"11062242581061",
				"11062453360227",
				"11062432121268",
				"11062457591268",
				"11062440071446",
				"11062714040227",
				"11062759240227",
				"11062723501107",
				"11062744001107",
				"11062737511273",
				"11062723541268",
				"11062744151110",
				"11062745021110",
				"11062745181110",
				"11062750460124",
				"11062804100267",
				"11062825541456",
				"11062929161504",
				"11062934061504",
				"11062910250567",
				"11062948271504",
				"11062742451273",
				"11062758440227",
				"11062837381268",
				"11062841561230",
				"11062841271273",
				"11062917330567",
				"11062942051498",
				"11062913371504",
				"11062913371504",
				"11071811501267",
				"11071947190124",
				"11071821011047",
				"11071902311495",
				"11071906041495",
				"11072005201457",
				"11071409241276",
				"11071411001276",
				"11071411191276",
				"11071405541047",
				"11071411181047",
				"11071441210267",
				"11071457440267",
				"11071519191504",
				"11071816031061",
				"11071848491268",
				"11071831521107",
				"11071855141107",
				"11071851001017",
				"11071853391017",
				"11072146151267",
				"11072043440267",
				"11072120410227",
				"11072210551457",
				"11072208220383",
				"11072553531496",
				"11072550001384",
				"11072049011457",
				"11072108161267",
				"11072108551504",
				"11072109041047",
				"11072218031423",
				"11072246361273",
				"11072259320383",
				"11072216490383",
				"11072201010267",
				"11072551441273",
				"11072521411496",
				"11072606561311",
				"11072634421528",
				"11072507531017",
				"11072627511457",
				"11072631571384",
				"11072633041273",
				"11072637481273",
				"11072714391273",
				"11080146471403",
				"11072632311047",
				"11072636510227",
				"11072658310227",
				"11072743310221",
				"11072856291273",
				"11072940340263",
				"11072816500567",
				"11072827500567",
				"11072950441268",
				"11072757280227",
				"11072701281268",
				"11080139131403",
				"11080154421403",
				"11080155201403",
				"11080155491403",
				"11080156151403",
				"11080156421403",
				"11080128171403",
				"11080147070567",
				"11080336361457",
				"11080840320263",
				"11080957420227",
				"11080915530227",
				"11081019271273",
				"11080439171061",
				"11080404251473",
				"11080523441473",
				"11080552520203",
				"11081125441017",
				"11081838491107",
				"11081832121457",
				"11080807111473",
				"11080900280227",
				"11080902491273",
				"11081040081504",
				"11081021141495",
				"11081022301495",
				"11081149281528",
				"11081150481528",
				"11081109350227",
				"11081115400227",
				"11081127570293",
				"11081117210227",
				"11081124400227",
				"11081132040227",
				"11081143311473",
				"11081224051457",
				"11081200331276",
				"11081213061276",
				"11081248401276",
				"11081200251276",
				"11081205051276",
				"11081208321273",
				"11081554011379",
				"11081500451379",
				"11081515591403",
				"11081503111379",
				"11081507181379",
				"11081558300263",
				"11081521010383",
				"11081635151504",
				"11081642141017",
				"11081647311017",
				"11081945351047",
				"11081617271384",
				"11081833041268",
				"11081616390124",
				"11081631561276",
				"11081601471520",
				"11081824091457",
				"11081802121107",
				"11081924331061",
				"11081953081526",
				"11081901361526",
				"11081950331446",
				"11081932330293",
				"11081904501061",
				"11082038061504",
				"11082035521017",
				"11082048281017",
				"11082021231268",
				"11082024111273",
				"11082013270124",
				"11082251131384",
				"11082256141384",
				"11082211170227",
				"11082257001110",
				"11082243551457",
				"11082203260227",
				"11082256531504",
				"11082238220383",
				"11082311360203",
				"11082342211384",
				"11082357261273",
				"11082310361403",
				"11082247541456",
				"11082528031520",
				"11082516321276",
				"11082521561276",
				"11082523031276",
				"11082528161276",
				"11082531441276",
				"11082526480567",
				"11083000501457",
				"11083001530203",
				"11090132210567",
				"11090133070567",
				"11090109421504",
				"11090112551061",
				"11090134541061",
				"11082633361463",
				"11083132481311",
				"11090529171047",
				"11090510331061",
				"11090628560267",
				"11090608061456",
				"11083158450203",
				"11082647170293",
				"11082629431017",
				"11082940181498",
				"11082908591403",
				"11082911491403",
				"11083041291384",
				"11083058391384",
				"11083007500203",
				"11083106460203",
				"11083120091068",
				"11083137581068",
				"11083156041311",
				"11083123051461",
				"11090115281457",
				"11090108591403",
				"11090135291403",
				"11090720421061",
				"11090734531061",
				"11090704091528",
				"11090717301273",
				"11090731511473",
				"11090841080383",
				"11090801151457",
				"11090216441311",
				"11090534411061",
				"11090526290085",
				"11090517540383",
				"11090506390383",
				"11090521411276",
				"11090616301273",
				"11090654211528",
				"11090623131457",
				"11090618231457",
				"11090645200267",
				"11090601560267",
				"11090707481311",
				"11090725111047",
				"11091320041461",
				"11091454190567",
				"11091415341061",
				"11091435131384",
				"11091451281107",
				"11090905431311",
				"11090902021273",
				"11091349301457",
				"11091357541520",
				"11091323451457",
				"11090832111273",
				"11091339581107",
				"11091443461276",
				"11091445481276",
				"11091403371384",
				"11091416511017",
				"11091410140227",
				"11091424581268",
				"11091553510124",
				"11091533241473",
				"11091533341473",
				"11091900361384",
				"11090909521461",
				"11091332061276",
				"11091940170263",
				"11092043540227",
				"11092057350227",
				"11092058260124",
				"11092018380567",
				"11092055231446",
				"11092041380293",
				"11092018111446",
				"11092308311384",
				"11092310171384",
				"11092311431384",
				"11092312141384",
				"11092337271463",
				"11092648581528",
				"11092739360085",
				"11092718401457",
				"11091448051496",
				"11091521450383",
				"11091506221423",
				"11091507311504",
				"11091543191457",
				"11091632570085",
				"11091908530567",
				"11091956490124",
				"11091944440567",
				"11092055220293",
				"11092055440293",
				"11092021521273",
				"11092006051110",
				"11091651480085",
				"11091934321384",
				"11091921411553",
				"11092052221276",
				"11092058091276",
				"11092001311495",
				"11092058310293",
				"11092055351276",
				"11092147481461",
				"11092158071061",
				"11092208041403",
				"11092203401273",
				"11092209411273",
				"11092201381457",
				"11092303041384",
				"11092326381384",
				"11092626381504",
				"11092646041504",
				"11092235401273",
				"11092223060567",
				"11092309061457",
				"11092314071061",
				"11092323241384",
				"11092328151384",
				"11092337231384",
				"11092340181384",
				"11092342101384",
				"11092602371311",
				"11092606381311",
				"11092655191528",
				"11092628551461"
};
		manager.download(fileNames);
	}
}