package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Event;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class RemindersResponse {

    private Collection<ReminderResponse> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean isLast;

    public RemindersResponse(Page<Event> reminderPage){
        this.content = reminderPage.getContent().stream().map(reminder -> new ReminderResponse(reminder)).collect(Collectors.toList());
        this.pageNumber = reminderPage.getNumber();
        this.pageSize = reminderPage.getSize();
        this.totalPages = reminderPage.getTotalPages();
        this.isLast = reminderPage.isLast();
    }
}
