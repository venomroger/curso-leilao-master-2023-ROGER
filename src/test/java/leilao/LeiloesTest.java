package leilao;

import Login.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;

    @AfterEach
    public void afterEach(){
        this.paginaDeLeiloes.fechar();
    }
    @Test
    public void deveriaCadastrarLeilao() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        CadastroLeilaoPage paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
    }

}
