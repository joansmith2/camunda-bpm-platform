/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.persistence.entity;

import java.util.List;

import org.camunda.bpm.engine.history.HistoricIncident;
import org.camunda.bpm.engine.impl.HistoricIncidentQueryImpl;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.persistence.AbstractHistoricManager;

/**
 * @author Roman Smirnov
 *
 */
public class HistoricIncidentManager extends AbstractHistoricManager {

  public long findHistoricIncidentCountByQueryCriteria(HistoricIncidentQueryImpl query) {
    getAuthorizationManager().configureHistoricIncidentQuery(query);
    return (Long) getDbEntityManager().selectOne("selectHistoricIncidentCountByQueryCriteria", query);
  }

  @SuppressWarnings("unchecked")
  public List<HistoricIncident> findHistoricIncidentByQueryCriteria(HistoricIncidentQueryImpl query, Page page) {
    getAuthorizationManager().configureHistoricIncidentQuery(query);
    return getDbEntityManager().selectList("selectHistoricIncidentByQueryCriteria", query, page);
  }

  public void deleteHistoricIncidentsByProcessInstanceId(String processInstanceId) {
    if (isHistoryLevelFullEnabled()) {
      getDbEntityManager().delete(HistoricIncidentEntity.class, "deleteHistoricIncidentsByProcessInstanceId", processInstanceId);
    }
  }

  public void deleteHistoricIncidentsByProcessDefinitionId(String processDefinitionId) {
    if (isHistoryLevelFullEnabled()) {
      getDbEntityManager().delete(HistoricIncidentEntity.class, "deleteHistoricIncidentsByProcessDefinitionId", processDefinitionId);
    }
  }

}
