package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelsResponse {
    private Collection<ModelResponse> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean isLast;

    public ModelsResponse(Page<Model> modelPage){
        this.content = modelPage.getContent().stream().map(model -> new ModelResponse(model)).collect(Collectors.toList());
        this.pageNumber = modelPage.getNumber();
        this.pageSize = modelPage.getSize();
        this.totalPages = modelPage.getTotalPages();
        this.isLast = modelPage.isLast();

    }
}
