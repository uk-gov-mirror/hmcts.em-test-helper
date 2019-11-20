package uk.gov.hmcts.reform.em.test;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.hmcts.reform.em.test.api.CcdDefImportApi;
import uk.gov.hmcts.reform.em.test.api.CcdDefUserRoleApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CcdDefinitionHelper {

    private final IdamHelper idamHelper;
    private final S2sHelper s2sHelper;
    private final CcdDefImportApi ccdDefImportApi;
    private final CcdDefUserRoleApi ccdDefUserRoleApi;

    public CcdDefinitionHelper(IdamHelper idamHelper, S2sHelper s2sHelper,
                               CcdDefImportApi ccdDefImportApi, CcdDefUserRoleApi ccdDefUserRoleApi) {
        this.idamHelper = idamHelper;
        this.s2sHelper = s2sHelper;
        this.ccdDefImportApi = ccdDefImportApi;
        this.ccdDefUserRoleApi = ccdDefUserRoleApi;
    }

    public void createCcdImportUser(String username, String userRole) {
        this.idamHelper.createUser(username, Stream.of(userRole, "ccd-import").collect(Collectors.toList()));
    }

    public void importDefinitionFile(String username, String userRole, InputStream caseDefFile) throws IOException {

        ccdDefUserRoleApi.createUserRole(new CcdDefUserRoleApi.CreateUserRoleBody(userRole, "PUBLIC"),
                idamHelper.authenticateUser(username), s2sHelper.getCcdGwS2sToken());

        MultipartFile multipartFile = new MockMultipartFile(
                "x",
                "x",
                "application/octet-stream",
                caseDefFile);

        ccdDefImportApi.importCaseDefinition(idamHelper.authenticateUser(username),
                s2sHelper.getCcdGwS2sToken(), multipartFile);

    }



}