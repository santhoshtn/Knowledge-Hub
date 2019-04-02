//domain object search document consumed by kafka consumer

package com.stackroute.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDocument {
    @Id
    private String id;
    private String conceptName;
    private String domain;
    private String link;
}
