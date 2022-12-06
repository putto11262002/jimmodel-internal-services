package com.jimmodel.internalServices.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "event")
@Builder
public class Slot extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator",strategy = "com.jimmodel.internalServices.config.UUIDGenerator")
    @EqualsAndHashCode.Include private UUID id;
    private Instant startTimestamp;
    private Instant endTimestamp;
    @ManyToOne(cascade = CascadeType.ALL)
    private Event task;

}