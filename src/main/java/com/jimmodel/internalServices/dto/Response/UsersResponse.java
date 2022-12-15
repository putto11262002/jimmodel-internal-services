package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.domain.User;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class UsersResponse {
    private Collection<UserResponse> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean isLast;

    public UsersResponse(Page<User> userPage){
        this.pageNumber = userPage.getNumber();
        this.pageSize = userPage.getSize();
        this.totalPages = userPage.getTotalPages();
        this.isLast = userPage.isLast();
        this.content = userPage.stream().map(user -> new UserResponse(user)).collect(Collectors.toList());

    }
}
