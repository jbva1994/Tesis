package modelo;


public class TestPedagogico extends Persona {

    
    int idtest;
    int idpersona;
    String fecha;
    int barras;
    int paralelas;
    int cabos;
    int pecho;
    int abdomen;
    int cunclilla;
    double prom;
    double halon;
    double sentadilla;
    int ushikomi;
    int nagekomi60;
    int nagekomi30;
    double pique30;
    double pique50;
    double pique100;

    public TestPedagogico() {

    }

    public TestPedagogico(int idpersona, int barras, int paralelas, int cabos, int pecho, int abdomen, int cunclilla, double prom, double halon, double sentadilla, int ushikomi, int nagekomi60, int nagekomi30, double pique30, double pique50, double pique100) {
        this.idpersona = idpersona;
        this.barras = barras;
        this.paralelas = paralelas;
        this.cabos = cabos;
        this.pecho = pecho;
        this.abdomen = abdomen;
        this.cunclilla = cunclilla;
        this.prom = prom;
        this.halon = halon;
        this.sentadilla = sentadilla;
        this.ushikomi = ushikomi;
        this.nagekomi60 = nagekomi60;
        this.nagekomi30 = nagekomi30;
        this.pique30 = pique30;
        this.pique50 = pique50;
        this.pique100 = pique100;
    }
    
    public TestPedagogico(int idpersona, String cedula,String fecha, int barras, int paralelas, int cabos, int pecho, int abdomen, int cunclilla, double prom, double halon, double sentadilla, int ushikomi, int nagekomi60, int nagekomi30, double pique30, double pique50, double pique100) {
        this.idpersona = idpersona;
        this.cedula = cedula;
        this.fecha = fecha;
        this.barras = barras;
        this.paralelas = paralelas;
        this.cabos = cabos;
        this.pecho = pecho;
        this.abdomen = abdomen;
        this.cunclilla = cunclilla;
        this.prom = prom;
        this.halon = halon;
        this.sentadilla = sentadilla;
        this.ushikomi = ushikomi;
        this.nagekomi60 = nagekomi60;
        this.nagekomi30 = nagekomi30;
        this.pique30 = pique30;
        this.pique50 = pique50;
        this.pique100 = pique100;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    

    public int getIdtest() {
        return idtest;
    }

    public void setIdtest(int idtest) {
        this.idtest = idtest;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getBarras() {
        return barras;
    }

    public void setBarras(int barras) {
        this.barras = barras;
    }

    public int getParalelas() {
        return paralelas;
    }

    public void setParalelas(int paralelas) {
        this.paralelas = paralelas;
    }

    public int getCabos() {
        return cabos;
    }

    public void setCabos(int cabos) {
        this.cabos = cabos;
    }

    public int getPecho() {
        return pecho;
    }

    public void setPecho(int pecho) {
        this.pecho = pecho;
    }

    public int getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(int abdomen) {
        this.abdomen = abdomen;
    }

    public int getCunclilla() {
        return cunclilla;
    }

    public void setCunclilla(int cunclilla) {
        this.cunclilla = cunclilla;
    }

    public double getProm() {
        return prom;
    }

    public void setProm(double prom) {
        this.prom = prom;
    }

    public double getHalon() {
        return halon;
    }

    public void setHalon(double halon) {
        this.halon = halon;
    }

    public double getSentadilla() {
        return sentadilla;
    }

    public void setSentadilla(double sentadilla) {
        this.sentadilla = sentadilla;
    }

    public int getUshikomi() {
        return ushikomi;
    }

    public void setUshikomi(int ushikomi) {
        this.ushikomi = ushikomi;
    }

    public int getNagekomi60() {
        return nagekomi60;
    }

    public void setNagekomi60(int nagekomi60) {
        this.nagekomi60 = nagekomi60;
    }

    public int getNagekomi30() {
        return nagekomi30;
    }

    public void setNagekomi30(int nagekomi30) {
        this.nagekomi30 = nagekomi30;
    }

    public double getPique30() {
        return pique30;
    }

    public void setPique30(double pique30) {
        this.pique30 = pique30;
    }

    public double getPique50() {
        return pique50;
    }

    public void setPique50(double pique50) {
        this.pique50 = pique50;
    }

    public double getPique100() {
        return pique100;
    }

    public void setPique100(double pique100) {
        this.pique100 = pique100;
    }

}
