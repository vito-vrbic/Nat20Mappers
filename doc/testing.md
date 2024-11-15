
_**Testing - Log in & Sign Up(Frontend aspekti) (No. 1) by Maksimilijan**_

VAŽNO: kod registracije kada se odabere opcija da se predstavlja organizacija
nije potrebno napisati ime organizacije, stvoren profil nema naziv organizacije
i omogućen je login bez da se zna kojoj organizaciji osoba pripada

--> Rješenje: dodati error handling za neunesen org. name

VAŽNO: pri prijavi u sustav treba dodati sustav javljanja greški i potvrdu za uspješno ulogiranje, trenutno se samo reloada page prilikom greške, a pri uspješnoj prijavi stranica stavlja korisnika na njegov dashboard

--> Rješenje: implementirati potvrdu za uspješni login i error handling

VAŽNO: treba se još implementirati, prema dokumentaciji,
neki tekst potvrde za korisnika da se uspješno registrirao/prijavio

--> Rješenje: implementirati navedeni nedostatak

VAŽNO: nedostaje dodatni način prijave u sustav (npr. prijava preko Googla)

--> Rješenje: implementirati Google sign in opciju

QOF(Quality of life): pri registraciji treba uvesti prioritetnu listu pogrešaka

--> Rješenje: provjera unosa po važnosti (All fields required>Email Format>Passwords Matching)

QOF: poboljšati obavještavanje korisnika oko neispravno ispunjenih dijelova 
(alert,automatska provjera pomoću type,opcijonalni tekst da treba popuniti sve)

--> Rješenje: koristiti 1 konzistentnu metodu obavještavanja

QOF:usklađivanje sa specifikacijama u dokumentaciji 
(piše da tijekom bacanja errora se izbrišu neispravni podaci, a aplikacija samo ostavi sve napisano)

--> Rješenje: Ažurirati dokumentaciju ili implementirati brisanje svih stavki radi jednostavnosti





_**Testing - Search (No. 1) by Nikola & Maksimilijan**_

VAŽNO: ako je radius search filter prazan, tada pretraga za lokalne igre ne radi uopće,
       problem obuhvaća i opciju kada stavimo samo online pretragu

-->Rješenje: Promijeniti logiku filtriranja ili postaviti neku default vrijednost za radijus(tipa 10000km)

VAŽNO: nedostaje vizualna diferencija između online i local igre

-->Rješenje: Dodati u neproširenoj kartici igre neku ikonu ili riječima
	     specificirati da se radi o online ili local igri. 

VAŽNO: nedostaje ikona za prikaz lokacije na mapi

-->Rješenje: Dodati neku ikonu/sliku za prikaz lokacije.

VAŽNO: kod displaya pravila u proširenoj kartici igre ima problem da piše 'Rules:Rules:'

-->Rješenje: U bazi treba maknuti prefiks 'Rules:' za svojstvo PRAVILNIK u n-torkama

QOF(Quality of life): način pretraživanja naslova se bazira na točnom nazivu igre 
		      što je za korisnika loše ako pogriješi ili želi tražiti veću grupu
		      igara sa sličnim nazivima (npr. poslovni korisnik napravi više gameova za isti event
		      pa ih nazove Event1, Event2, Event3, naš sistem tada zahtjeva da napišemo Event1 za Event1 i tako za svakog)

-->Rješenje: Promijeniti način filtiriranja naziva. Dodati neki sistem keywordova 
	     ili da se traži 

QOF: povećati broj filtera (npr. isHomeBrew, Complexity, Estimated Length...)

-->Rješenje: Implementirati nove filtere.

QOF: nije specificirano prilikom pretrage svih igara koje su od bussiness usera, a koje od privatnog

-->Rješenje: Dodati u proširenu karticu neki specifikator za problem.


_**Testing - Hosted website (No. 1) by Nikola & Maksimilijan**_

VAŽNO: homepage nije responzivan

-->Rješenje: dodati na homepage responzivnost za manje ekrane 
	     ili koristiti alternativnu verziju homepagea 

VAŽNO: kad se otvara URL link za stranicu, ona se učitava dugo

-->Rješenje: Potencijalna promjena mjesta deploymenta stranice.
