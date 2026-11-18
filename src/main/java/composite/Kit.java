package composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Composite da loja: um kit reúne produtos e outros kits. */
public final class Kit implements Oferta {
    private final String nome;
    private final List<Oferta> ofertas = new ArrayList<>();

    public Kit(String nome) {
        this.nome = Produto.validarNome(nome);
    }

    public Kit adicionar(Oferta oferta) {
        Objects.requireNonNull(oferta, "A oferta não pode ser nula");
        if (oferta == this) {
            throw new IllegalArgumentException("Um kit não pode conter a si mesmo");
        }
        if (oferta instanceof Kit kit && kit.contem(this)) {
            throw new IllegalArgumentException("A composição criaria uma referência circular");
        }
        ofertas.add(oferta);
        return this;
    }

    public boolean remover(Oferta oferta) {
        return ofertas.remove(oferta);
    }

    public List<Oferta> itens() {
        return List.copyOf(ofertas);
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public BigDecimal preco() {
        return ofertas.stream()
                .map(Oferta::preco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String detalhar(String indentacao) {
        StringBuilder resultado = new StringBuilder(indentacao)
                .append(nome).append(" — R$ ").append(preco().toPlainString());
        for (Oferta oferta : ofertas) {
            resultado.append(System.lineSeparator()).append(oferta.detalhar(indentacao + "  "));
        }
        return resultado.toString();
    }

    private boolean contem(Oferta procurada) {
        if (this == procurada) {
            return true;
        }
        return ofertas.stream().anyMatch(oferta -> oferta instanceof Kit kit && kit.contem(procurada));
    }
}
