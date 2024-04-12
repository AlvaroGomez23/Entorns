import java.util.Calendar;
import java.util.Date;

public class Alimentacio extends Producte{

    Date dataCaducitat;

    public Alimentacio(float preu, String nom, String codiBarres, Date dataCaducitat) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }

    public void setPreu() {
        Calendar c = Calendar.getInstance();
        //preu = (float) (preu-preu*(1/(dataCaducitat-dataActual+1)) + (preu * 0.1));
    }
}
