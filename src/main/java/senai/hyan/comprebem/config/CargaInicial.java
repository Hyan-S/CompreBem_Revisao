package senai.hyan.comprebem.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import senai.hyan.comprebem.entity.Produto;
import senai.hyan.comprebem.repository.ProdutoRepository;

@Component
public class CargaInicial implements CommandLineRunner {

    private static final String[][] PRODUTOS = {
            {"Arroz Branco 5kg", "Arroz tipo 1", "27.90"},
            {"Feijão Carioca 1kg", "Feijão tipo 1", "8.49"},
            {"Feijão Preto 1kg", "Feijão tipo 1", "9.29"},
            {"Açúcar Refinado 1kg", "Açúcar refinado", "4.99"},
            {"Sal Refinado 1kg", "Sal iodado", "2.59"},
            {"Café Torrado 500g", "Café tradicional", "18.90"},
            {"Óleo de Soja 900ml", "Óleo refinado", "7.49"},
            {"Macarrão Espaguete 500g", "Massa de sêmola", "4.29"},
            {"Farinha de Trigo 1kg", "Farinha tipo 1", "5.49"},
            {"Fubá 1kg", "Fubá mimoso", "3.99"},
            {"Leite Integral 1L", "Leite UHT", "5.29"},
            {"Leite Desnatado 1L", "Leite UHT", "5.49"},
            {"Manteiga 200g", "Manteiga com sal", "12.90"},
            {"Margarina 500g", "Margarina com sal", "8.99"},
            {"Queijo Mussarela 500g", "Fatiado", "24.90"},
            {"Presunto 200g", "Fatiado", "7.90"},
            {"Iogurte Natural 170g", "Iogurte integral", "3.49"},
            {"Requeijão 200g", "Requeijão cremoso", "8.49"},
            {"Ovos Brancos 12un", "Ovos grandes", "13.90"},
            {"Pão de Forma 500g", "Pão tradicional", "8.99"},
            {"Biscoito Cream Cracker 400g", "Biscoito salgado", "5.99"},
            {"Biscoito Recheado 130g", "Sabor chocolate", "2.99"},
            {"Achocolatado 400g", "Achocolatado em pó", "9.49"},
            {"Molho de Tomate 340g", "Molho tradicional", "3.29"},
            {"Extrato de Tomate 340g", "Extrato concentrado", "4.49"},
            {"Milho Verde Lata 170g", "Milho em conserva", "3.79"},
            {"Ervilha Lata 170g", "Ervilha em conserva", "3.69"},
            {"Atum em Lata 170g", "Atum em óleo", "9.90"},
            {"Sardinha em Lata 125g", "Sardinha em óleo", "5.49"},
            {"Maionese 500g", "Maionese tradicional", "9.99"},
            {"Ketchup 400g", "Ketchup tradicional", "8.49"},
            {"Mostarda 200g", "Mostarda amarela", "5.29"},
            {"Vinagre 750ml", "Vinagre de álcool", "3.49"},
            {"Azeite de Oliva 500ml", "Extra virgem", "34.90"},
            {"Peito de Frango 1kg", "Congelado", "19.90"},
            {"Carne Moída 500g", "Patinho moído", "22.90"},
            {"Linguiça Toscana 1kg", "Linguiça suína", "21.90"},
            {"Salsicha 500g", "Salsicha hot dog", "8.90"},
            {"Batata 1kg", "Batata inglesa", "6.99"},
            {"Cebola 1kg", "Cebola nacional", "5.99"},
            {"Tomate 1kg", "Tomate italiano", "8.49"},
            {"Alho 200g", "Alho nacional", "6.49"},
            {"Banana Prata 1kg", "Banana fresca", "6.99"},
            {"Maçã Gala 1kg", "Maçã nacional", "10.90"},
            {"Laranja Pera 1kg", "Laranja para suco", "4.99"},
            {"Alface Crespa", "Unidade", "3.49"},
            {"Cenoura 1kg", "Cenoura fresca", "5.49"},
            {"Refrigerante Cola 2L", "Refrigerante", "9.99"},
            {"Suco de Uva 1L", "Suco integral", "12.90"},
            {"Água Mineral 1,5L", "Sem gás", "2.99"},
            {"Cerveja Lata 350ml", "Cerveja pilsen", "4.29"},
            {"Detergente 500ml", "Detergente neutro", "2.49"},
            {"Sabão em Pó 1kg", "Sabão em pó", "15.90"},
            {"Amaciante 2L", "Amaciante concentrado", "14.90"},
            {"Água Sanitária 1L", "Água sanitária", "4.49"},
            {"Esponja de Louça 3un", "Esponja dupla face", "4.99"},
            {"Papel Higiênico 12un", "Folha dupla", "21.90"},
            {"Sabonete 85g", "Sabonete em barra", "2.79"},
            {"Creme Dental 90g", "Creme dental", "4.99"},
            {"Shampoo 350ml", "Shampoo para todos os cabelos", "14.90"},
    };

    private final ProdutoRepository repository;

    public CargaInicial(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        for (int i = 0; i < PRODUTOS.length; i++) {
            Produto produto = new Produto();
            produto.setNome(PRODUTOS[i][0]);
            produto.setDescricao(PRODUTOS[i][1]);
            produto.setPreco(new BigDecimal(PRODUTOS[i][2]));
            produto.setQuantidade(10 + (i * 7) % 90);
            repository.save(produto);
        }
    }
}
