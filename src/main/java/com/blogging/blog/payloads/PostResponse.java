package com.blogging.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDto> postDtoList;
    private  int pageNumber;
    private  int pageSize;
    private  int totalElement;
    private  int totalPages;
    private  boolean lastPage;
}
