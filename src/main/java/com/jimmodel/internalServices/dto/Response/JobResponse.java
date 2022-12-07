package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Event;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse extends EventResponse {

    private UUID id;
    private String title;
    private Collection<SlotResponse> slots;
    private Collection<ModelResponse> relatedModels;
    private ClientResponse client;
    private String personInCharge;
    private String mediaReleased;
    private String periodReleased;
    private String territoriesReleased;
    private String workingHour;
    private String overtimePerHour;
    private String feeAsAgreed;
    private String termOfPayment;
    private String cancellationFee;
    private String contractDetails;
    private String note;
    private Event.TYPE type;

    public JobResponse(Event job){
        this.id = job.getId();
        this.title = job.getTitle();
        this.slots = job.getSlots() != null ? job.getSlots().stream().map(slot -> new SlotResponse(slot)).collect(Collectors.toList()) : new ArrayList<>();
        this.relatedModels = job.getRelatedModels() != null ? job.getRelatedModels().stream().map(relatedModel -> new ModelResponse(relatedModel)).collect(Collectors.toList()) : new ArrayList<>();
        this.client = job.getClient() != null ? new ClientResponse(job.getClient()): null;
        this.personInCharge = job.getPersonInCharge();
        this.periodReleased = job.getPersonInCharge();
        this.mediaReleased = job.getMediaReleased();
        this.periodReleased = job.getPeriodReleased();
        this.territoriesReleased = job.getTerritoriesReleased();
        this.workingHour = job.getWorkingHour();
        this.overtimePerHour = job.getOvertimePerHour();
        this.feeAsAgreed = job.getFeeAsAgreed();
        this.termOfPayment = job.getTermOfPayment();
        this.cancellationFee = job.getCancellationFee();
        this.contractDetails = job.getContractDetails();
        this.note = job.getNote();
        this.type = job.getType();
    }
}
