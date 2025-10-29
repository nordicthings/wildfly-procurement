package de.holisticon.wildfly.procurement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_calls")
public class ApiCall {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name_parameter", nullable = false)
  private String nameParameter;

  @Column(name = "call_timestamp", nullable = false)
  private LocalDateTime callTimestamp;

  @Column(name = "endpoint", nullable = false)
  private String endpoint;

  // Konstruktoren
  public ApiCall() {
  }

  public ApiCall(String nameParameter, String endpoint) {
    this.nameParameter = nameParameter;
    this.endpoint = endpoint;
    this.callTimestamp = LocalDateTime.now();
  }

  // Getter und Setter
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNameParameter() {
    return nameParameter;
  }

  public void setNameParameter(String nameParameter) {
    this.nameParameter = nameParameter;
  }

  public LocalDateTime getCallTimestamp() {
    return callTimestamp;
  }

  public void setCallTimestamp(LocalDateTime callTimestamp) {
    this.callTimestamp = callTimestamp;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  @Override
  public String toString() {
    return "ApiCall{" +
        "id=" + id +
        ", nameParameter='" + nameParameter + '\'' +
        ", callTimestamp=" + callTimestamp +
        ", endpoint='" + endpoint + '\'' +
        '}';
  }
}