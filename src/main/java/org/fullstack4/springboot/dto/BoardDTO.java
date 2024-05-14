package org.fullstack4.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    private int idx;
    @NotBlank
    private String user_id;
    @NotBlank
    @Size(min=1, max=100)
    private String title;
    @NotBlank
    private String content;
    private String display_date;
    private LocalDateTime reg_date;
    private LocalDateTime modify_date;
}
