package leilao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();

    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.fechar();

    }
    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();
        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos(){
        paginaDeLogin.preencheFormularioDeLogin("invalido", "123");
        paginaDeLogin.efetuaLogin();

        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());

    }
    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        paginaDeLogin.navegadaParaPaginaDeLances();
        Assertions.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));

    }
}
