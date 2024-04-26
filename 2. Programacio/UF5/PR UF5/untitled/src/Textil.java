public class Textil extends Producte{

    String composicio;

    public Textil(float preu, String nom, String codiBarres, String composicio) {
        super(preu, nom, codiBarres);
        this.composicio = composicio;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Preu: " + preu + ", composicio=" + composicio + ", codiBarres=" + codiBarres
                +  "]";
    }

    @Override
    public float getPreu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPreu'");
    }

    
}
