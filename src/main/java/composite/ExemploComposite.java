package composite;

import java.math.BigDecimal;

/** Demonstra kits aninhados em um catálogo de loja virtual. */
public final class ExemploComposite {
    private ExemploComposite() {
    }

    public static void main(String[] args) {
        Produto teclado = new Produto("Teclado mecânico", new BigDecimal("249.90"));
        Produto mouse = new Produto("Mouse sem fio", new BigDecimal("119.90"));
        Produto headset = new Produto("Headset", new BigDecimal("189.90"));

        Kit perifericos = new Kit("Kit de periféricos")
                .adicionar(teclado)
                .adicionar(mouse);
        Kit setup = new Kit("Setup para jogos")
                .adicionar(perifericos)
                .adicionar(headset);

        System.out.println(setup.detalhar(""));
    }
}
