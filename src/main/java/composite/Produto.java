package composite;

import java.math.BigDecimal;
import java.util.Objects;

/** Folha da composição: um produto que não contém outras ofertas. */
public final class Produto implements Oferta {
    private final String nome;
    private final BigDecimal preco;

    public Produto(String nome, BigDecimal preco) {
        this.nome = validarNome(nome);
        this.preco = validarPreco(preco);
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public BigDecimal preco() {
        return preco;
    }

    @Override
    public String detalhar(String indentacao) {
        return indentacao + nome + " — R$ " + preco.toPlainString();
    }

    static String validarNome(String nome) {
        Objects.requireNonNull(nome, "O nome não pode ser nulo");
        if (nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode estar vazio");
        }
        return nome;
    }

    static BigDecimal validarPreco(BigDecimal preco) {
        Objects.requireNonNull(preco, "O preço não pode ser nulo");
        if (preco.signum() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }
        return preco;
    }
}
