package com.jimmodel.internalServices.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Client")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @NotNull(groups = {Event.JobInfo.class, Event.ReminderInfo.class, Event.OptionInfo.class}, message = "Client id cannot be empty.")
    @EqualsAndHashCode.Include private UUID id;
    @NotBlank(message = "Client name cannot be empty.")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
