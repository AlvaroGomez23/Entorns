public abstract class Producte implements Comparable<Producte> {

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
        return "Producte [nom=" + nom + ", preu=" + preu +  ", codiBarres=" + codiBarres + "]";
    }

    public abstract float getPreu();

    protected abstract String getNom();

    public String getCodiBarres() {
        return codiBarres;
    }

    @Override
    public int compareTo(Producte o) {
        return Integer.parseInt(codiBarres)-Integer.parseInt(o.getCodiBarres());
    }

    ;


    

    
}
