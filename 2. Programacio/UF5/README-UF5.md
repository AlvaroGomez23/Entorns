# Detalls pràctica UF 5
## Classe Main
El que fa la classe main és executar el menuPrincipal() que conté els demés metodes per poder executar el programa.

### Metode menuPrincipal()
Aquest mètode crida un menú on hi han diferents opcions per escollir. Cada una porta a una zona diferent del programa.

### Metode menuSecundari()
Aquest segon menú és per afegir els aliments. Cada aliment té un numero i per tornar al menú principal també té una opcio.

### Metode passarCaixa()
Una vegada afegits els aliments, al passar per caixa compta els articles i fa la suma total del que costarien

### Metode mostrarCarro()
Aquest mètode mostra els productes que hi han al carret, però no mostra els preus dels aliments

### Metode buscarProductes()
Busca un producte mitjançant un codi de barres del producte

### Metodes afegirAlimentacio(), afegirTextil(), afegirElectronica()
Afegeix els corresponents articles al carret

### Metode comprovarCodiBarres
Comprova si el preu del document UpdateTextilPrices correspon al que ha introduit l'usuari i cambia el preu

### Metode crearFitxers
Genera l'estructura de fitxers que es demana

### Metode recollirExcepcions
Recolleix les excepcions que s'han produit al llarg d'executar el programa

## Classe Producte
Classe abstracta que genera l'estructura principal de les altres classes
Té els mètodes getPreu, getNom, getCodiBarres i compareTo que faig servir en les eltres classes

## Classe Alimentació
Té les mateixes classes que el producte només que el preu es calculat amb un setPreu ja que va en funció dels dies de caducitat

## Classe Electronica
Té les mateixes classes que el producte i el preu es calculat mitjançant el setPreu que va en funció dels dies de garantia

## Classe Textil
Aquesta classe té els mateixos mètodes que el producte però implementa el comparable per ordenar naturalment els articles textil

PD: El codi ha sigut fet amb Visual Studio ja que el Windows Defender va bloquejar el git al IntelliJIdea
