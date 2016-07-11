package org.fenixedu.sdk.api;

import org.fenixedu.sdk.Authorization;
import org.fenixedu.sdk.domain.MissionState;

import com.google.gson.JsonObject;

public interface ExpendituresResources {

    JsonObject searchMissions(Authorization authorization, String processNumber, String missionResponsibleOID,
            String payingUnitOID, Boolean foreign, Long date, String interval, String requestingPersonOID, String participantOID,
            MissionState pendingState, String accountingUnitOID, String participantAuthorizationAuthorityOID,
            Boolean filterCanceledProcesses, Boolean filterTakenProcesses);

    JsonObject getMission(Authorization authorization, String processNumber);

}
