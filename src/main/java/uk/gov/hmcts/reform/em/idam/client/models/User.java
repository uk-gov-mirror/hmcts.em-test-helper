package uk.gov.hmcts.reform.em.idam.client.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private String authToken;
    private UserDetails userDetails;
}
