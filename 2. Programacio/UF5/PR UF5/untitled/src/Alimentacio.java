
import java.util.Date;

public class Alimentacio extends Producte{


    Date dataCaducitat;

    public Alimentacio(float preu, String nom, String codiBarres, Date dataCaducitat) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }

    public float setPreu(float preu) {

        Date dataActual = new Date(System.currentTimeMillis());
        preu = (float) (preu-preu*(1/(dataCaducitat.getTime()-dataActual.getTime()+1)) - (preu * 0.1));

        return preu;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Preu: " + getPreu()  + ", codiBarres=" + codiBarres + ", dataCaducitat="
                + dataCaducitat + "]";
    }

    @Override
    public float getPreu() {
        return setPreu(preu);
    }

    @Override
    protected String getNom() {
        return nom;
    }
}
