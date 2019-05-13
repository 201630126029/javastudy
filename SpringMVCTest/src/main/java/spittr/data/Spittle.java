package spittr.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class Spittle {

    private final String message;
    private final Date time;
    private final Long id;
    private Double longitude;
    private Double latitude;
    public Spittle(String message, Date time){
        this(message,time, null, null);
    }

    public Spittle(String message, Date time, Double longitude, Double latitude){
        this.id=null;
        this.message=message;
        this.time=time;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Long getId() {
        return id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id", "time");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }
}
