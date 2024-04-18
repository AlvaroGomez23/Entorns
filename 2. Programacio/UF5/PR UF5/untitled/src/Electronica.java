public class Electronica extends Producte{

    int diesGarantia;

    public Electronica(float preu, String nom, String codiBarres, int diesGarantia) {
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    public int setPreuDiesGarantia(int preuDiesGarantia) {

        preuDiesGarantia = (int) (preu + preu*(diesGarantia/365)*0.1);
        return preuDiesGarantia;
    }

    @Override
    public float getPreu() {
        return setPreuDiesGarantia(diesGarantia);

    }

    @Override
    public String toString() {
        return "Electronica [nom=" + nom + ", preu=" + getPreu() + ", diesGarantia=" + diesGarantia +  "codiBarres="
                + codiBarres + "]";
    }

    
}
