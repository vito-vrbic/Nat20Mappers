![Testing](https://placehold.co/15x15/1589F0/1589F0.png)
_**Testing - Log in & Sign Up(Frontend aspekti) (No. 1) by Maksimilijan**_

<br/>

$${\color{orangered}VAŽNO}$$: kod registracije kada se odabere opcija da se predstavlja organizacija
nije potrebno napisati ime organizacije, stvoren profil nema naziv organizacije
i omogućen je login bez da se zna kojoj organizaciji osoba pripada

--> $${\color{lightgreen}Rješenje}$$: dodati error handling za neunesen org. name 

<br/>

$${\color{orangered}VAŽNO}$$: pri prijavi u sustav treba dodati sustav javljanja greški i potvrdu za uspješno ulogiranje, trenutno se samo reloada page prilikom greške, a pri uspješnoj prijavi stranica stavlja korisnika na njegov dashboard

--> $${\color{lightgreen}Rješenje}$$: implementirati potvrdu za uspješni login i error handling

<br/>

$${\color{orangered}VAŽNO}$$: treba se još implementirati, prema dokumentaciji,
neki tekst potvrde za korisnika da se uspješno registrirao/prijavio

--> $${\color{lightgreen}Rješenje}$$: implementirati navedeni nedostatak

<br/>

$${\color{orangered}VAŽNO}$$: nedostaje dodatni način prijave u sustav (npr. prijava preko Googla)

--> $${\color{lightgreen}Rješenje}$$: implementirati Google sign in opciju

<br/>

$${\color{yellow} QOF}$$ $${\color{yellow}(Quality}$$ $${\color{yellow} of}$$ $${\color{yellow} life)}$$: pri registraciji treba uvesti prioritetnu listu pogrešaka

--> $${\color{lightgreen}Rješenje}$$: provjera unosa po važnosti (All fields required>Email Format>Passwords Matching)

<br/>

$${\color{yellow} QOF}$$: poboljšati obavještavanje korisnika oko neispravno ispunjenih dijelova 
(alert,automatska provjera pomoću type,opcijonalni tekst da treba popuniti sve)

--> $${\color{lightgreen}Rješenje}$$: koristiti 1 konzistentnu metodu obavještavanja

<br/>

$${\color{yellow} QOF}$$:usklađivanje sa specifikacijama u dokumentaciji 
(piše da tijekom bacanja errora se izbrišu neispravni podaci, a aplikacija samo ostavi sve napisano)

--> $${\color{lightgreen}Rješenje}$$: Ažurirati dokumentaciju ili implementirati brisanje svih stavki radi jednostavnosti

<br/>

![Testing](https://placehold.co/15x15/1589F0/1589F0.png)
_**Testing - Search (No. 1) by Nikola & Maksimilijan**_

<br/>

$${\color{orangered}VAŽNO}$$: ako je radius search filter prazan, tada pretraga za lokalne igre ne radi uopće,
       problem obuhvaća i opciju kada stavimo samo online pretragu

--> $${\color{lightgreen}Rješenje}$$: Promijeniti logiku filtriranja ili postaviti neku default vrijednost za radijus(tipa 10000km)

<br/>

$${\color{orangered}VAŽNO}$$: nedostaje vizualna diferencija između online i local igre

--> $${\color{lightgreen}Rješenje}$$: Dodati u neproširenoj kartici igre neku ikonu ili riječima
	     specificirati da se radi o online ili local igri. 

<br/>

$${\color{orangered}VAŽNO}$$: nedostaje ikona za prikaz lokacije na mapi

--> $${\color{lightgreen}Rješenje}$$: Dodati neku ikonu/sliku za prikaz lokacije.

<br/>

$${\color{orangered}VAŽNO}$$: kod displaya pravila u proširenoj kartici igre ima problem da piše 'Rules:Rules:'

--> $${\color{lightgreen}Rješenje}$$: U bazi treba maknuti prefiks 'Rules:' za svojstvo PRAVILNIK u n-torkama

<br/>

$${\color{yellow} QOF}$$: način pretraživanja naslova se bazira na točnom nazivu igre 
		      što je za korisnika loše ako pogriješi ili želi tražiti veću grupu
		      igara sa sličnim nazivima (npr. poslovni korisnik napravi više gameova za isti event
		      pa ih nazove Event1, Event2, Event3, naš sistem tada zahtjeva da napišemo Event1 za Event1 i tako za svakog)

--> $${\color{lightgreen}Rješenje}$$: Promijeniti način filtiriranja naziva. Dodati neki sistem keywordova 
	     ili da se traži 

<br/>

$${\color{yellow} QOF}$$: povećati broj filtera (npr. isHomeBrew, Complexity, Estimated Length...)

--> $${\color{lightgreen}Rješenje}$$: Implementirati nove filtere.

<br/>

$${\color{yellow} QOF}$$: nije specificirano prilikom pretrage svih igara koje su od bussiness usera, a koje od privatnog

--> $${\color{lightgreen}Rješenje}$$: Dodati u proširenu karticu neki specifikator za problem.

<br/>

![Testing](https://placehold.co/15x15/1589F0/1589F0.png)
_**Testing - Hosted website (No. 1) by Nikola & Maksimilijan**_

<br/>

$${\color{orangered}VAŽNO}$$: homepage nije responzivan

--> $${\color{lightgreen}Rješenje}$$: dodati na homepage responzivnost za manje ekrane 
	     ili koristiti alternativnu verziju homepagea 

<br/>

$${\color{orangered}VAŽNO}$$: kad se otvara URL link za stranicu, ona se učitava dugo

--> $${\color{lightgreen}Rješenje}$$: Potencijalna promjena mjesta deploymenta stranice.

<br/>
