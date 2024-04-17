
import java.util.Date;

public class Alimentacio extends Producte{


    Date dataCaducitat;

    public Alimentacio(float preu, String nom, String codiBarres, Date dataCaducitat) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }

    public void setPreu() {

        Date dataActual = new Date(System.currentTimeMillis());
        preu = (float) (preu-preu*(1/(dataCaducitat.getTime()-dataActual.getTime()+1)) + (preu * 0.1));

        System.out.println(preu);
    }

    @Override
    public String toString() {
        return "Alimentacio [nom=" + nom + ", preu=" + preu  + ", codiBarres=" + codiBarres + ", dataCaducitat="
                + dataCaducitat + "]";
    }

    @Override
    public float getPreu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPreu'");
    }

    


}
