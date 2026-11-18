package composite;

import java.math.BigDecimal;

/** Componente comum a um produto individual e a um kit. */
public interface Oferta {
    String nome();

    BigDecimal preco();

    String detalhar(String indentacao);
}
