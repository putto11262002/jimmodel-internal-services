package com.jimmodel.services.dto.Response;

import com.jimmodel.services.domain.Event;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobsResponse {
    private Collection<JobResponse> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean isLast;



    public JobsResponse(Page<Event> jobPage){
        this.pageSize = jobPage.getSize();
        this.totalPages = jobPage.getTotalPages();
        this.isLast = jobPage.isLast();
        this.content = jobPage.stream().map(job -> new JobResponse(job)).collect(Collectors.toList());

    }
}
