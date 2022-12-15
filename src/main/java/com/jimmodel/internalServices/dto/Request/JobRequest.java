package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Collection<SlotRequest> slots;
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
    private Event.TYPE type;


    public Event toEntity() {
        return Event.jobBuilder(
                this.id,
                this.title,
                this.slots != null ? this.slots.stream().map(slotRequest -> slotRequest.toEntity()).collect(Collectors.toList()) : null,
                this.relatedModels != null ? this.relatedModels.stream().map(modelRequest -> modelRequest.toEntity()).collect(Collectors.toList()) : null,
                this.client != null ? this.client.toEntity() : null,
                this.personInCharge,
                this.mediaReleased,
                this.periodReleased,
                this.territoriesReleased,
                this.workingHour,
                this.overtimePerHour,
                this.feeAsAgreed,
                this.termOfPayment,
                this.cancellationFee,
                this.contractDetails,
                this.note
        );
    }
}
