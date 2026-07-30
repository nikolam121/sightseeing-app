package hr.tis.academy.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductsMetadata {
    private Long id;
    private LocalDateTime datumVrijeme;
    private String naslov;
    private List<Product> popisProizvoda;

    public ProductsMetadata(Long id, LocalDateTime datumVrijeme, String naslov, List<Product> popisProizvoda) {
        this.id = id;
        this.datumVrijeme = datumVrijeme;
        this.naslov = naslov;
        this.popisProizvoda = popisProizvoda;
    }

    public ProductsMetadata() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public List<Product> getPopisProizvoda() {
        return popisProizvoda;
    }

    public void setPopisProizvoda(List<Product> popisProizvoda) {
        this.popisProizvoda = popisProizvoda;
    }

    @Override
    public String toString() {
        return "ProductsMetadata{" +
                "id=" + id +
                ", datumVrijeme=" + datumVrijeme +
                ", naslov='" + naslov + '\'' +
                ", popisProizvoda=" + popisProizvoda +
                '}';
    }

    static void main() {
        List<Product> lista = new ArrayList<>();
        lista.add(new Product("Acer laptop", BigDecimal.valueOf(45.6), 5, "EUR"));
        lista.add(new Product("HP laptop", BigDecimal.valueOf(41.6), 2, "EUR"));
        ProductsMetadata productsMetadata = new ProductsMetadata(Long.valueOf(1), LocalDateTime.now(), "Naslov1", lista);

        System.out.println(productsMetadata);
    }
}
