package africa.semicolon.E_Transit.data.dtos.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DistanceMatrixRow {

    private List<DistanceMatrixElement> elements;
}
