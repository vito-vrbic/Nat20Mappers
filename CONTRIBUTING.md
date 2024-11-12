## Struktura GitHub repozitorija

Naš GitHub repozitorij slijedi preporučenu strukturu grana kako bismo osigurali jasnu organizaciju koda i dokumentacije. Ovdje je pregled naše strukture grana:

- **MASTER**: Glavna grana koja sadrži stabilnu verziju projekta. Sva važna izdanja su označena ovdje. Bilo kakav pull request treba biti odobren od strane voditelja tima.
  
- **DEV**: Razvojna grana za kod. Sve promjene u kodu rade se u ovoj grani i spajaju se natrag u glavnu granu nakon što su testirane i odobrene.

- **DEV DOC**: Razvojna grana za dokumentaciju. Ovdje se nalaze sve promjene vezane uz dokumentaciju koje se također spajaju natrag u glavnu granu.

### Proces razvoja

1. **Rad na značajkama**: Svaka nova značajka ili popravak buga treba se razvijati u zasebnim granama iz `DEV` ili `DEV DOC` ovisno o vrsti promjene.

2. **Testiranje**: Sve promjene trebaju biti testirane lokalno prije nego što budu spojene.

3. **Pull Request**: Nakon što su promjene dovršene i testirane, otvorite Pull Request prema `DEV` ili `DEV DOC` ovisno o vrsti promjena.

4. **Spajanje u MASTER**: Promjene se spajaju u glavnu granu nakon završetka ciklusa razvoja.
