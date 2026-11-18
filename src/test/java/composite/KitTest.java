package composite;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class KitTest {
    @Test
    void somaPrecosDeProdutosEDeKitsAninhados() {
        Kit perifericos = new Kit("Periféricos")
                .adicionar(new Produto("Teclado", new BigDecimal("249.90")))
                .adicionar(new Produto("Mouse", new BigDecimal("119.90")));
        Kit setup = new Kit("Setup")
                .adicionar(perifericos)
                .adicionar(new Produto("Headset", new BigDecimal("189.90")));

        assertEquals(new BigDecimal("559.70"), setup.preco());
    }

    @Test
    void detalhamentoMostraArvoreComIndentacao() {
        Kit kit = new Kit("Kit básico")
                .adicionar(new Produto("Mouse", new BigDecimal("50.00")));

        assertEquals("Kit básico — R$ 50.00" + System.lineSeparator()
                        + "  Mouse — R$ 50.00", kit.detalhar(""));
    }

    @Test
    void permiteAdicionarERemoverOfertas() {
        Produto mouse = new Produto("Mouse", new BigDecimal("50.00"));
        Kit kit = new Kit("Kit").adicionar(mouse);

        assertTrue(kit.remover(mouse));
        assertFalse(kit.remover(mouse));
        assertEquals(BigDecimal.ZERO, kit.preco());
    }

    @Test
    void naoPermiteReferenciasCirculares() {
        Kit externo = new Kit("Externo");
        Kit interno = new Kit("Interno");
        externo.adicionar(interno);

        assertThrows(IllegalArgumentException.class, () -> interno.adicionar(externo));
        assertThrows(IllegalArgumentException.class, () -> externo.adicionar(externo));
    }

    @Test
    void impedeAlterarListaInternaPelaListaRetornada() {
        Kit kit = new Kit("Kit").adicionar(new Produto("Mouse", new BigDecimal("50.00")));

        assertThrows(UnsupportedOperationException.class, () -> kit.itens().clear());
        assertEquals(new BigDecimal("50.00"), kit.preco());
    }

    @Test
    void validaNomeEPrecoDeProduto() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(" ", new BigDecimal("10.00")));
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Mouse", new BigDecimal("-1.00")));
        assertThrows(NullPointerException.class,
                () -> new Produto("Mouse", null));
    }
}
