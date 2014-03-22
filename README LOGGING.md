CalZone Logging (@autor youri)
=======

# Hoe zit het logging systeem in elkaar?

## Conclusie uit sectie 1.3.2 van de [Spring documentatie](http://docs.spring.io/spring/docs/3.1.0.M1/spring-framework-reference/html/overview.html#d0e748)
Spring ondersteunt vele frameworks voor logging. Standaard gebruikt Spring JCL (Jakarta Commons Logging API), maar zelf zegt de documentatie van Spring dat dit misschien geen geweldig iets is om zelf te gebruiken doorheen u code. Zij zeggen dat het beter/makkelijker is om SLF4J (Simple Logging Facade for Java) te gebruiken. Dit is een abstractielaag zodat er verscheidene soorten logging frameworks kunnen gebruikt en verwisseld worden 'at deployment' zonder daarvoor heel de source code te moeten wijzigen (slechts kleine aanpassingen in pom.xml zullen er nodig zijn naast een configuratie van u backbone framework). 

Ik heb dus reeds de nodige dependencies ingeladen om dit te initialiseren en ben nu de [manual van SLF4J](http://www.slf4j.org/manual.html) aan het lezen en aan het spelen met logging op mijn eigen branch.

Voorlopig is de backbone voor logging [log4j versie 1.2](https://logging.apache.org/log4j/1.2/). De configuratie van deze backbone gebeurt in de file ~~'log4j.properties'~~ 'log4j.xml'. Deze is terug te vinden in de main/resources folder (bij messages.properties enzo). Deze [tutorial](http://www.tutorialspoint.com/log4j/index.htm) kan je raadplegen voor een simpele uitleg van de werking van log4j. Google'en naar vele voorbeeldjes om te zien hoe de XML-boom wordt opgesteld.

## GEBRUIK van SLF4J 
In een klasse waar je gegevens wil loggen roep je een instantie van een SLF4J Logger Object op. Vervolgens stuur je alle berichten die je wil loggen naar dit object. De afhandeling van je logs wordt door log4j.xml voorzien.

Er zijn 4 soorten logberichten. Hieronder een voorbeeldklasse:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
	final Logger logger = LoggerFactory.getLogger(getClass());
	public void f(){
		logger.info("Hello World");
		logger.info("This is a logging test");
		logger.debug("This is a debug message with a parameter at the end: {}", 5);
		logger.error("This is an error message with parameter at the end: {}", 6);
		logger.warn("This is a warning message with parameter at the end: {}", 6);
	}
}
```
Voor meer info verwijs ik naar de [JavaDoc](http://www.slf4j.org/api/org/slf4j/Logger.html) van het object. Hier heeft is de gehele [API](http://www.slf4j.org/api/overview-summary.html) van SLF4J.

## Configuratie log4j
Je configureert ~~'log4j.properties'~~ 'log4j.xml' naargelang hoe je stuff gelogd wil (in een specifieke file of specifieke UNIX console...) en vervolgens hoef je je weinig aan te trekken over waar welke log te staan komt. Doorlees de [tutorial](http://www.tutorialspoint.com/log4j/index.htm) indien je hiermee wil knoeien en gebruik Google voor XML-voorbeeldjes.

Voorlopig is dit de geïmplementeerde architectuur: 3 locaties waar logs terecht kunnen komen: 1 voor debug, 1 UNIX console (system.out) en 1 voor gewone log (alles buiten debug)

### Opzet debug logs
De debug logs worden in een aparte folder gestoken: 'debugLog'. Bovendien zal er per dag een nieuwe log in deze folder starten kwestie van orde op zaken te houden. (gebeurt automatisch) Dit is vrij noodzakelijk, want er is een overload aan debug messages die geproduceerd worden door de verscheidene frameworks die we gebruiken.

##LET OP:## het kan dus zoekwerk vereisen om eigen gemaakt debug logs terug te vinden in deze file!

### Opzet gewone log file
Deze log file zit in een eigen folder: log. Alles behalve debug berichten komen in deze file terecht.

### Opzet console log
Alle logs passeren op system.out
