# Upute za rad u Frontend Development okruženju

Dobrodošli u razvojno okruženje za frontend aplikacije **TTRPG Finder**! Ovaj dokument opisuje korake za postavljanje i rad u ovom okruženju.

## Preduvjeti

Prije početka, osigurajte da na sustavu imate instalirane sljedeće alate:

- **Node.js** (verzija 14 ili novija): [Preuzmite Node.js](https://nodejs.org/)
- **npm** (Node Package Manager, dolazi s Node.js-om)

## Postavljanje projekta

### 1. Instalacija ovisnosti

Nakon što ste klonirali projekt, uđite u mapu projekta i instalirajte potrebne ovisnosti:

```bash
cd TTRPG-Frontend
npm install
```

### 2. Pokretanje razvojnog poslužitelja (servera)

Za pokretanje lokalnog servera za razvoj i pregled aplikacije u preglediku koristite sljedeću naredbu:
```bash
npm start
```

Aplikacija će se automatski otvoriti u pregledniku na adresi http://localhost:3000. Poslužitelj podržava hot-reloading, što znači da će se sve promjene u kodu automatski prikazati u pregledniku.

## Struktura Projekta

Kratki pregled glavnih direktorija i datoteka:

- **src/**: Sadrži sav izvorni kod React aplikacije.
  - **App.js**: Glavna komponenta gdje započinje struktura aplikacije.
  - **components/**: Preporučeni direktorij za dodatne komponente.
- **public/**: Sadrži statičke datoteke i glavnu `index.html` datoteku gdje se aplikacija prikazuje.
- **.gitignore**: Osigurava da `node_modules` i druge generirane datoteke nisu dodane u verzijski sustav.
- **node_modules/**: Sadrži sve npm pakete (isključeno iz verzijskog sustava).
