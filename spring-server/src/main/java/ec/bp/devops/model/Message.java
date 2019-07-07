package ec.bp.devops.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Message
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-05T03:09:44.410Z")

public class Message   {
  @JsonProperty("message")
  private String message = null;

  @JsonProperty("to")
  private String to = null;

  @JsonProperty("from")
  private String from = null;

  @JsonProperty("timeToLifeSec")
  private Integer timeToLifeSec = null;

  public Message message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Message
   * @return message
  **/
  @ApiModelProperty(value = "Message")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Message to(String to) {
    this.to = to;
    return this;
  }

  /**
   * Receiver name
   * @return to
  **/
  @ApiModelProperty(value = "Receiver name")


  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Message from(String from) {
    this.from = from;
    return this;
  }

  /**
   * Name of the issuer
   * @return from
  **/
  @ApiModelProperty(value = "Name of the issuer")


  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Message timeToLifeSec(Integer timeToLifeSec) {
    this.timeToLifeSec = timeToLifeSec;
    return this;
  }

  /**
   * Time to life seconds
   * @return timeToLifeSec
  **/
  @ApiModelProperty(value = "Time to life seconds")


  public Integer getTimeToLifeSec() {
    return timeToLifeSec;
  }

  public void setTimeToLifeSec(Integer timeToLifeSec) {
    this.timeToLifeSec = timeToLifeSec;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return Objects.equals(this.message, message.message) &&
        Objects.equals(this.to, message.to) &&
        Objects.equals(this.from, message.from) &&
        Objects.equals(this.timeToLifeSec, message.timeToLifeSec);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, to, from, timeToLifeSec);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    timeToLifeSec: ").append(toIndentedString(timeToLifeSec)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

