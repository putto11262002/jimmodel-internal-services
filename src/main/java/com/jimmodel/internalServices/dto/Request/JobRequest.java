package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobRequest {

    private UUID id;
    private String title;
    private Collection<SlotResponse> slots;
    private ClientRequest client;
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
    private Collection<ModelRequest> relatedModels;
    private String note;
    private String type;


    public Event toEntity(){
        return Event.builder()
                .title(this.title)
                .slots(this.getSlots() != null ? this.slots.stream().map(slotResponse -> slotResponse.toEntity()).collect(Collectors.toList()) : new ArrayList<>())
                .client(this.getClient() != null ? this.client.toEntity(): null)
                .personInCharge(this.personInCharge)
                .mediaReleased(this.mediaReleased)
                .periodReleased(this.periodReleased)
                .territoriesReleased(this.territoriesReleased)
                .workingHour(this.workingHour)
                .overtimePerHour(this.overtimePerHour)
                .feeAsAgreed(this.feeAsAgreed)
                .termOfPayment(this.termOfPayment)
                .cancellationFee(this.cancellationFee)
                .contractDetails(this.contractDetails)
                .relatedModels(this.relatedModels != null ? this.relatedModels.stream().map(modelRequest -> modelRequest.toEntity()).collect(Collectors.toList()) : null)
                .note(this.note)
                .type(this.type)
                .build();
    }
}
