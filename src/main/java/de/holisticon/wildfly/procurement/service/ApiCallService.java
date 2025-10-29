package de.holisticon.wildfly.procurement.service;

import de.holisticon.wildfly.procurement.entity.ApiCall;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ApiCallService {

  private static final Logger LOGGER = Logger.getLogger(ApiCallService.class.getName());

  @PersistenceContext(unitName = "procurement-pu")
  private EntityManager entityManager;

  /**
   * Speichert einen neuen API-Aufruf in der Datenbank
   */
  public ApiCall saveApiCall(String nameParameter, String endpoint) {
    LOGGER.info("Speichere API-Aufruf: name=" + nameParameter + ", endpoint=" + endpoint);

    ApiCall apiCall = new ApiCall(nameParameter, endpoint);
    entityManager.persist(apiCall);
    entityManager.flush(); // Sofort in DB schreiben für ID-Generation

    LOGGER.info("API-Aufruf gespeichert mit ID: " + apiCall.getId());
    return apiCall;
  }

  /**
   * Gibt alle API-Aufrufe zurück
   */
  public List<ApiCall> findAllApiCalls() {
    TypedQuery<ApiCall> query = entityManager.createQuery(
        "SELECT a FROM ApiCall a ORDER BY a.callTimestamp DESC", ApiCall.class);
    return query.getResultList();
  }

  /**
   * Gibt alle API-Aufrufe für einen bestimmten Namen zurück
   */
  public List<ApiCall> findApiCallsByName(String nameParameter) {
    TypedQuery<ApiCall> query = entityManager.createQuery(
        "SELECT a FROM ApiCall a WHERE a.nameParameter = :name ORDER BY a.callTimestamp DESC",
        ApiCall.class);
    query.setParameter("name", nameParameter);
    return query.getResultList();
  }

  /**
   * Zählt alle API-Aufrufe
   */
  public long countApiCalls() {
    TypedQuery<Long> query = entityManager.createQuery(
        "SELECT COUNT(a) FROM ApiCall a", Long.class);
    return query.getSingleResult();
  }
}