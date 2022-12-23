package com.jimmodel.services.dto.Response;

import com.jimmodel.services.domain.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlotsResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean isLast;
    private Collection<SlotResponse> content;

    public SlotsResponse(Page<Slot> eventPage){
        this.pageNumber = eventPage.getNumber();
        this.pageSize = eventPage.getSize();
        this.totalPages = eventPage.getTotalPages();
        this.isLast = eventPage.isLast();
        this.content = eventPage.getContent().stream().map(event -> new SlotResponse(event)).collect(Collectors.toList());
    }
}
