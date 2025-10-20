package br.edu.unichristus.biblioteca;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Documentação da API BIBLIOTECA",
                version = "1.0",
                description = "Trata-se da documentação da API construída para a avaliação da NP2",
                contact = @Contact(
                        name = "Luís Eduardo Nogueira, Cristiano Florêncio e Cauã Carvalho",
                        email = "luiseduardogondimnogueira@gmail.com",
                        url = "https://unichristus.edu.br")
        )
)

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
