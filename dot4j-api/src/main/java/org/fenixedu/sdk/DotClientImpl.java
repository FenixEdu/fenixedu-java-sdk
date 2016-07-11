package org.fenixedu.sdk;

import java.util.HashMap;
import java.util.Map;

import org.fenixedu.sdk.api.ExpendituresResources;
import org.fenixedu.sdk.api.DotEndpoint;
import org.fenixedu.sdk.api.PublicResources;
import org.fenixedu.sdk.domain.MissionState;

import com.google.gson.JsonObject;

public class DotClientImpl extends DotClientBaseImpl implements DotClient {

    public DotClientImpl(ApplicationConfiguration config) {
        super(config);
    }

    @Override
    public PublicResources publicScope() {
        return this;
    }

    @Override
    public ExpendituresResources expendituresScope() {
        return this;
    }

    /**
     * Obtains a paginated list of missions that fit the search criteria.
     * Each element of the list has some, but not all, information about the mission.
     * Only missions that are available to the person in context are returned.
     *
     * <p>
     * <b>Scope:</b> Expenditures
     * </p>
     *
     * @return paginated list of missions who fit the search criteria
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject searchMissions(Authorization authorization, String processNumber, String missionResponsibleOID,
            String payingUnitOID, Boolean foreign, Long date, String interval, String requestingPersonOID, String participantOID,
            MissionState pendingState, String accountingUnitOID, String participantAuthorizationAuthorityOID,
            Boolean filterCanceledProcesses, Boolean filterTakenProcesses) {

        final Map<String, String> params = new HashMap<String, String>();
        if (processNumber != null) {
            params.put("processNumber", processNumber);
        }
        if (missionResponsibleOID != null) {
            params.put("ruOID", missionResponsibleOID);
        }
        if (payingUnitOID != null) {
            params.put("puOID", payingUnitOID);
        }
        if (foreign != null) {
            params.put("f", foreign.toString());
        }
        if (date != null) {
            params.put("d", date.toString());
        }
        if (interval != null) {
            params.put("i", interval);
        }
        if (requestingPersonOID != null) {
            params.put("rpOID", requestingPersonOID);
        }
        if (participantOID != null) {
            params.put("pOID", participantOID);
        }
        if (pendingState != null) {
            params.put("ps", pendingState.toString());
        }
        if (accountingUnitOID != null) {
            params.put("auOID", accountingUnitOID);
        }
        if (participantAuthorizationAuthorityOID != null) {
            params.put("paaOID", participantAuthorizationAuthorityOID);
        }
        if (filterCanceledProcesses != null) {
            params.put("fc", filterCanceledProcesses.toString());
        }
        if (filterTakenProcesses != null) {
            params.put("ft", filterTakenProcesses.toString());
        }
        return invoke(DotEndpoint.EXPENDITURES_SEARCH_MISSIONS, authorization, params);
    }

    /**
     * Obtains information about the mission with the chosen process number.
     * Only missions that are available to the person in context are returned.
     *
     * <p>
     * <b>Scope:</b> Expenditures
     * </p>
     *
     * @return information about the mission
     * @throws FenixEduClientException .
     */
    @Override
    public JsonObject getMission(Authorization authorization, String processNumber) {

        final Map<String, String> params = new HashMap<String, String>();
        if (processNumber != null) {
            params.put("processNumber", processNumber);
        }
        return invoke(DotEndpoint.EXPENDITURES_MISSION, authorization, params);
    }
}
