public class Alimentacio extends Producte{

    String dataCaducitat;

    public Alimentacio(float preu, String nom, String codiBarres, String dataCaducitat) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }
}
