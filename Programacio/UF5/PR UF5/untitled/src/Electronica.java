public class Electronica extends Producte{

    int diesGarantia;

    public Electronica(float preu, String nom, String codiBarres, int diesGarantia) {
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    @Override
    public String toString() {
        return "Electronica [preu=" + preu + ", diesGarantia=" + diesGarantia + ", nom=" + nom + ", codiBarres="
                + codiBarres + "]";
    }

    
}
