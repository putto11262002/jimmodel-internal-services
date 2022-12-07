package com.jimmodel.internalServices.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Event extends BaseEntity {
    public Event(UUID id, String title, Collection<Slot> slots, Collection<Model> relatedModels, Client client, String personInCharge, String mediaReleased, String periodReleased, String territoriesReleased, String workingHour, String overtimePerHour, String feeAsAgreed, String termOfPayment, String cancellationFee, String contractDetails, String note, TYPE type) {
        this.id = id;
        this.title = title;
        this.slots = slots;
        if(this.slots != null)this.slots.forEach(slot -> slot.setEvent(this));
        this.relatedModels = relatedModels;
        this.client = client;
        this.personInCharge = personInCharge;
        this.mediaReleased = mediaReleased;
        this.periodReleased = periodReleased;
        this.territoriesReleased = territoriesReleased;
        this.workingHour = workingHour;
        this.overtimePerHour = overtimePerHour;
        this.feeAsAgreed = feeAsAgreed;
        this.termOfPayment = termOfPayment;
        this.cancellationFee = cancellationFee;
        this.contractDetails = contractDetails;
        this.note = note;
        this.type = type;
    }

    public enum TYPE {
        OPTION,
        JOB,
        REMINDER
    }
    public interface JobInfo {}
    public interface OptionInfo {}
    public interface ReminderInfo {}

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @EqualsAndHashCode.Include private UUID id;
    @NotNull(groups = {JobInfo.class, OptionInfo.class, ReminderInfo.class}, message = "Title cannot be null.")
    protected String title;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Collection<Slot> slots;
    @ManyToMany
    @Valid
    protected Collection<Model> relatedModels;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Client client;
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
    private TYPE type;
    public Event(String title, Collection<Slot> slots, Collection<Model> relatedModels){
        this.title = title;
        this.slots = slots;
        this.relatedModels = relatedModels;
    }

    public Event(UUID id, String title, Collection<Slot> slots, Collection<Model> relatedModels){
        this.title = title;
        this.slots = slots;
        this.relatedModels = relatedModels;
    }

    public Event(UUID id, String title, Collection<Slot> slots){
        this.id = id;
        this.title = title;
        this.slots = slots;
    }

    public void toOption(){
        this.setClient(null);
        this.setCancellationFee(null);
        this.setContractDetails(null);
        this.setOvertimePerHour(null);
        this.setFeeAsAgreed(null);
    }

    public void toReminder(){
        this.setClient(null);
        this.setCancellationFee(null);
        this.setContractDetails(null);
        this.setOvertimePerHour(null);
        this.setFeeAsAgreed(null);
        this.setOvertimePerHour(null);
        this.setTerritoriesReleased(null);
        this.setMediaReleased(null);
        this.setPeriodReleased(null);
        this.setPersonInCharge(null);
        this.setTermOfPayment(null);
        this.setWorkingHour(null);
    }

    public void setRelatedModels(Collection<Model> relatedModels){
        if (this.relatedModels == null){
            this.relatedModels = new HashSet<>();
        }else {
            this.relatedModels.clear();
        }
        this.relatedModels.clear();
        if(relatedModels != null){
            this.relatedModels.addAll(relatedModels);
        }
    }

    public void setSlots(Collection<Slot> slots){
        if(this.slots == null){
            this.slots = new HashSet<>();
        }else {
            this.slots.clear();
        }
        if(slots != null){
            this.slots.addAll(slots);
            this.slots.forEach(event -> event.setEvent(this));
        }
    }

    public void addSlot(Slot slot){
        slot.setEvent(this);
        this.slots.add(slot);
    }

    public void removeSlot(Slot slot){
        this.slots.remove(slot);
        slot.setEvent(null);
    }

}
