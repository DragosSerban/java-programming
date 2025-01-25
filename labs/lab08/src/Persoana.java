class ExceptieStartCnpInvalid extends Exception {
    ExceptieStartCnpInvalid() {
        System.out.println("CNP Start Invalid");
    }
}

class ExceptieFormulaCnpInvalid extends Exception {
    ExceptieFormulaCnpInvalid() {
        System.out.println("CNP Formula Invalid");
    }
}

public class Persoana {
    public static int nrPersoane;
    private ContBancar cont;
    private String nume, cnp;
    public Persoana(String nume, String cnp, ContBancar cont) throws ExceptieStartCnpInvalid, ExceptieFormulaCnpInvalid {
        try {
            this.seteazaDate(nume, cnp, cont);
            nrPersoane++;
        } catch (ExceptieStartCnpInvalid e) {
        } catch (ExceptieFormulaCnpInvalid e) {
        }
    }
    public void seteazaDate(String nume, String cnp, ContBancar cont)
            throws ExceptieStartCnpInvalid, ExceptieFormulaCnpInvalid {
        if(cnp.charAt(0) == '0' || cnp.charAt(0) == '9')
            throw new ExceptieStartCnpInvalid();
        String aux = "279146358279";
        int total = 0;
        for(int i = 0; i < aux.length(); i++)
            total += Integer.parseInt(String.valueOf(aux.charAt(i))) * Integer.parseInt(String.valueOf(cnp.charAt(i)));
        int rest = total % 11;
        int c;
        if(rest < 10)
            c = rest;
        else
            c = 1;
        if(Integer.parseInt(String.valueOf(cnp.charAt(12))) != c)
            throw new ExceptieFormulaCnpInvalid();
        this.nume = nume;
        this.cnp = cnp;
        this.cont = cont;
    }
    public ContBancar cont() {
        return this.cont;
    }
    public void afiseazaInformatii() {
        if(this.nume != null && this.cnp != null && this.cont != null)
            System.out.println("Date: " + this.nume + " - " + this.cnp + " (Cont - " + this.cont.numarCont() + " - " + this.cont.depozit() + ")");
    }
}
