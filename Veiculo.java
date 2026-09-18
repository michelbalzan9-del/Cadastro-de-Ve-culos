import java.time.LocalDate;

public class Veiculo {

    public static final int ANO_MINIMO = 1900;

    private String marca;
    private String modelo;
    private int ano;
    private String placa;

    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, int ano, String placa) {
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setPlaca(placa);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.isBlank())
            throw new IllegalArgumentException("A marca não pode ser vazia.");
        this.marca = marca.trim();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.isBlank())
            throw new IllegalArgumentException("O modelo não pode ser vazio.");
        this.modelo = modelo.trim().toUpperCase();
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (!validaAno(ano))
            throw new IllegalArgumentException(
                    "Ano inválido. Informe um ano entre " + ANO_MINIMO + " e " + anoMaximo() + ".");
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        String normalizada = normalizaPlaca(placa);
        if (!validaPlaca(normalizada))
            throw new IllegalArgumentException(
                    "Placa inválida. Use o formato ABC1234 ou ABC1D23 (Mercosul).");
        this.placa = normalizada;
    }

    public static int anoMaximo() {
        return LocalDate.now().getYear() + 1;
    }

    public static boolean validaAno(int ano) {
        return ano >= ANO_MINIMO && ano <= anoMaximo();
    }

    public static String normalizaPlaca(String placa) {
        if (placa == null)
            return "";
        return placa.trim().toUpperCase().replace("-", "");
    }

    public static boolean validaPlaca(String placa) {
        String regex = "^([A-Z]{3}[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2})$";
        return normalizaPlaca(placa).matches(regex);
    }

    @Override
    public String toString() {
        return "Marca: " + marca + " | Modelo: " + modelo + " | Ano: " + ano + " | Placa: " + placa;
    }
}