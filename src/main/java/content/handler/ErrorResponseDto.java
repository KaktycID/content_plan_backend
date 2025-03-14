package content.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponseDto {

    private String error;
    private int status;
    private String message;
    private String stackTrace;
    @JsonIgnore
    private LocalDateTime timestamp;
    private String path;
}
