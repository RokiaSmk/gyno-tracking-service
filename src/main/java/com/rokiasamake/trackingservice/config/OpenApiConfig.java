package com.rokiasamake.trackingservice.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()

                .info(
                        new Info()

                                .title("🌸 API de suivi gynécologique")

                                .version("1.0.0")

                                .description("""

                                Plateforme intelligente de suivi gynécologique basée sur une architecture microservices.

                                Ce microservice assure le suivi du cycle menstruel, des symptômes,
                                de l'alimentation et des émotions des membres de la plateforme.

                                Fonctionnalités principales :

                                🌸 Gestion du profil menstruel
                                • Création du profil menstruel
                                • Consultation du profil
                                • Modification du profil

                                🩸 Gestion des cycles menstruels
                                • Démarrage d'un cycle menstruel
                                • Clôture d'un cycle menstruel
                                • Consultation de l'historique des cycles
                                • Suppression logique d'un cycle

                                🤒 Gestion des symptômes
                                • Enregistrement des symptômes d'un cycle
                                • Consultation des symptômes d'un cycle
                                • Modification d'un symptôme
                                • Suppression d'un symptôme

                                🍽️ Journal alimentaire
                                • Enregistrement des repas
                                • Consultation du journal alimentaire d'un cycle
                                • Modification d'un repas
                                • Suppression d'un repas

                                😊 Journal émotionnel
                                • Enregistrement des émotions quotidiennes
                                • Consultation du journal émotionnel d'un cycle
                                • Modification d'une émotion
                                • Suppression d'une émotion

                                📊 Les données collectées serviront aux prédictions
                                du prochain cycle, de l'ovulation, de la fenêtre fertile,
                                ainsi qu'aux analyses réalisées par le microservice Analytics :

                                • Analyse des symptômes
                                • Analyse de l'alimentation
                                • Analyse des émotions
                                • Analyse des habitudes au fil des cycles

                                """)
                                .contact(
                                        new Contact()
                                                .name("Rokia Ba Samaké")
                                                .email("rsamake96@gmail.com")
                                )
                )

                .servers(
                        List.of(
                                new Server()
                                        .url("/")
                                        .description("API Gateway")
                        )
                );
    }

}