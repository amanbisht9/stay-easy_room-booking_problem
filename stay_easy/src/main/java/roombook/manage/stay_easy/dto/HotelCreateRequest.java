package roombook.manage.stay_easy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelCreateRequest {

    private String hotelName;

    private String location;

    private String description;

    private int roomAvailable;
    
}