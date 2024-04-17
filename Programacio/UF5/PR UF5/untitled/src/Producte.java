public class Producte implements Comparable {

    float preu;
    String nom;
    String codiBarres;

    public Producte(float preu, String nom, String codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
    }

    @Override
    public String toString() {
        return "Producte [preu=" + preu + ", nom=" + nom + ", codiBarres=" + codiBarres + "]";
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    
}
