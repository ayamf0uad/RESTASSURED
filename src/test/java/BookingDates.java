import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingDates {
    @JsonProperty
    private Date checkin;
    @JsonProperty
    private Date checkout;
}
