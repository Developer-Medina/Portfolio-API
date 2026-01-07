package dev.medina.portfolio_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    // atributo
    @Value("${api.keys}")
    private String apiKeys;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // =====================================================
        // CORS PRE-FLIGHT (OPTIONS)
        // O navegador faz esse request ANTES do GET real
        // Ele NÃO envia API Key, então não podemos bloquear aqui
        // =====================================================
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // lendo header da requisicao
        String requestApiKey = request.getHeader("PORTFOLIO-API-KEY");

        if (requestApiKey == null || requestApiKey.isBlank()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("API key is missing.");
            return; // se nao mandar a chave, nao usa a api
        }

        // transformamos as keys em dois componentes
        List<String> validKeys = Arrays.asList(apiKeys.split(","));

        // se a chave estiver fora do range da lista
        if (!validKeys.contains(requestApiKey)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Invalid API key.");
            return; // matamos caso a requisição nao seja valida
        }

        // se tudo ocorreu bem, a API responde como deveria
        filterChain.doFilter(request, response);
    }
}
