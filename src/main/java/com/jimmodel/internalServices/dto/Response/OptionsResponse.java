package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.domain.Event;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionsResponse {
    private Collection<OptionResponse> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean isLast;


    public OptionsResponse(Page<Event> optionPage){
       this.pageNumber = optionPage.getNumber();
       this.pageSize = optionPage.getSize();
       this.totalPages = optionPage.getTotalPages();
       this.isLast = optionPage.isLast();
       this.content = optionPage.getContent().stream().map(option -> new OptionResponse(option)).collect(Collectors.toList());
    }

}
