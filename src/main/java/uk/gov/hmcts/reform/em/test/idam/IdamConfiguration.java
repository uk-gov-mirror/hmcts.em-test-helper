package uk.gov.hmcts.reform.em.test.idam;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.idam.client.IdamClient;
import uk.gov.hmcts.reform.idam.client.IdamTestApi;

@Configuration
@ConditionalOnProperty(prefix = "idam", value = "api.url")
@ComponentScan(basePackages = "uk.gov.hmcts.reform.idam.client")
public class IdamConfiguration {

    @Bean
    IdamHelper idamHelper(IdamClient idamClient, IdamTestApi idamTestApi, DeleteUserApi deleteUserApi) {
        return new IdamHelper(idamClient, idamTestApi, deleteUserApi);
    }
}