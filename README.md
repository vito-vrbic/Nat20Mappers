# TTRPG Finder 🎲🧙‍♂️

> TTRPG Finder je web aplikacija koja omogućava lokalnoj zajednici da pronađe grupe za igranje tabletop role-playing igara (TTRPG-eva). Ovaj projekt ima za cilj olakšati lokalno povezivanje igrača, potaknuti zajedništvo među entuzijastima i, u konačnici, rasplamsati lokalnu TTRPG scenu.

## Opis projekta

### Motivacija

Unatoč rastućoj popularnosti i raznovrsnosti TTRPG-a, mnogi entuzijasti i početnici suočavaju se s izazovom pronalaska adekvatnih grupa za igranje, koje su jedan od najvažnijih aspekata TTRPG-eva i njihova osnova. Naša aplikacija omogućava korisnicima lako pretraživanje, organizaciju i sudjelovanje u online ili uživo igrama, čime se povećava dostupnost i privlačnost TTRPG-a za širu publiku te potiče interakciju i upoznavanje među entuzijastima i početnicima.

### Stečena znanja

Kroz ovaj projekt, članovi našeg tima (navedeni kasnije), trebali bi naučiti kako napraviti frontend stranicu preko React biblioteke, kako napraviti server u programskom jeziku Java (preko Spring Boot frameworka), kako osigurati da aplikacija funkcionira u svim aspektima i testirati zaštićenost korisničkih podataka u svim dijelovima aplikacije. Najvažnije, svi članovi tima trebali bi naučiti kako funkcionirati u timu srednje veličine.

### Važna napomena

Ovaj projekt rezultat je timskog rada u sklopu projektnog zadatka kolegija [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz) na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu.

# Funkcijski zahtjevi ⚙️

### Registracija i prijava

TTRPG Finder aplikacija trebala bi implementirati registraciju i prijavu dvije vrste korisnika: privatnih i poslovnih korisnika, te im omogućiti različita iskustva kroz interakciju s aplikacijom.

### Pretraga nadolazećih igara

Ovisno o tipu korisnika, TTRPG Finder aplikacija trebala bi implementirati sustav pretraživanja igara kroz različite filtere povezane s vrstom igre, datumom, lokacijom, te tekstualno pretraživanje pojmova u imenu ili opisu igre.

### Kreiranje i upravljanje igrama

TTRPG Finder aplikacija treba omogućiti korisnicima stvaranje i upravljanje stvorenim igrama, eventualno uređivanje informacija, odobravanje/odbijanje prijava drugih korisnika, stvaranje prilagođenih upita za prijavu na igre, ili brisanje igara.

### Različiti tipovi igara

TTRPG Finder aplikacija treba podržavati različite tipove igara kako bi se pretraga točnih zahtjeva mogla ubrzati. Tipovi igara koje TTRPG Finder aplikacija treba podržavati su:
- Online | Lokalizirane
- Javne | Privatne
- Igre s prijavom | Igre bez prijave
- Popunjene | Dostupne

### Stvaranje prilagođenih profila 

Poslovni korisnici (kao što su dućani igara, knjižare, ili igrače udruge) trebaju imati mogućnost stvaranja donekle-prilagođenih profila kako bi korisnici TTRPG Finder aplikacije imali pristup njihovim informacijama i uslugama.

# Nefunkcijski zahtjevi 🔒

### Višekorisnički sustav

TTRPG Finder aplikacija treba biti višekorisnički sustav i podržavati rad više korisnika u isto vrijeme.

### Sigurnost podataka

TTRPG Finder treba raditi višestruke provjere kada je u pitanju slanje podataka korisnicima—glavna ideja je zaštititi povjerljive korisničke podatke koliko god je to moguće, a da su svi funkcijski zahtjevi ispunjeni.

### Upotrebljivost

TTRPG Finder aplikacija treba biti intuitivna i laka za korištenje—želimo korisnicima olakšati pronalazak igara, ne otežati.

# Tehnologije 💻

- Frontend: React, implementacija OpenStreetMap API-a
- Backend: Spring Boot (Java)
- Baza podataka: PostgreSQL
- Dizajn: Figma, Canva

# Instalacija 📦⬇️

*TODO: Upute za instalaciju biti će dodane kasnije.*

# Članovi tima 👨‍💻

U nastavku slijede članovi tima i njihova *glavna* zaduženja na ovom projektu:

- **Alfirević Luka** - baza podataka, backend, testiranje baze podataka
- **Blašković Patrik** - backend
- **Hižman Maksimilijan** - frontend, testiranje frontenda
- **Kliček Patrick** - frontend, dizajn
- **Kramarić Nikola** - backend, testiranje backenda
- **Lekić Vedran** - backend, dokumentacija
- **Vrbić Vito** - voditelj, frontend, dizajn, dokumentacija

# Kontribucije 🤝

Pravila za doprinos definirana su u [CONTRIBUTING.md](./CONTRIBUTING.md) i trebaju se poštivati.

# Licenca 📝

Ovaj projekt licenciran je pod [Creative Commons licencijom](./LICENSE).
