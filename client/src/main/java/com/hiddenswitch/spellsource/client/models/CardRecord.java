/**
 * Hidden Switch Spellsource API
 * The Spellsource API for matchmaking, user accounts, collections management and more
 *
 * OpenAPI spec version: 1.0.1
 * Contact: benjamin.s.berman@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.hiddenswitch.spellsource.client.models;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import com.hiddenswitch.spellsource.client.models.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;
/**
 * CardRecord
 */

public class CardRecord  implements Serializable {
  @SerializedName("_id")
  private String id = null;

  @SerializedName("entity")
  private Entity entity = null;

  @SerializedName("userId")
  private String userId = null;

  @SerializedName("collectionIds")
  private List<String> collectionIds = new ArrayList<String>();

  @SerializedName("borrowedByUserId")
  private String borrowedByUserId = null;

  @SerializedName("allianceId")
  private String allianceId = null;

  @SerializedName("donorUserId")
  private String donorUserId = null;

  public CardRecord id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CardRecord entity(Entity entity) {
    this.entity = entity;
    return this;
  }

   /**
   * Get entity
   * @return entity
  **/
  @ApiModelProperty(example = "null", value = "")
  public Entity getEntity() {
    return entity;
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
  }

  public CardRecord userId(String userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public CardRecord collectionIds(List<String> collectionIds) {
    this.collectionIds = collectionIds;
    return this;
  }

  public CardRecord addCollectionIdsItem(String collectionIdsItem) {
    this.collectionIds.add(collectionIdsItem);
    return this;
  }

   /**
   * Get collectionIds
   * @return collectionIds
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<String> getCollectionIds() {
    return collectionIds;
  }

  public void setCollectionIds(List<String> collectionIds) {
    this.collectionIds = collectionIds;
  }

  public CardRecord borrowedByUserId(String borrowedByUserId) {
    this.borrowedByUserId = borrowedByUserId;
    return this;
  }

   /**
   * Get borrowedByUserId
   * @return borrowedByUserId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getBorrowedByUserId() {
    return borrowedByUserId;
  }

  public void setBorrowedByUserId(String borrowedByUserId) {
    this.borrowedByUserId = borrowedByUserId;
  }

  public CardRecord allianceId(String allianceId) {
    this.allianceId = allianceId;
    return this;
  }

   /**
   * Get allianceId
   * @return allianceId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAllianceId() {
    return allianceId;
  }

  public void setAllianceId(String allianceId) {
    this.allianceId = allianceId;
  }

  public CardRecord donorUserId(String donorUserId) {
    this.donorUserId = donorUserId;
    return this;
  }

   /**
   * Get donorUserId
   * @return donorUserId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getDonorUserId() {
    return donorUserId;
  }

  public void setDonorUserId(String donorUserId) {
    this.donorUserId = donorUserId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardRecord cardRecord = (CardRecord) o;
    return Objects.equals(this.id, cardRecord.id) &&
        Objects.equals(this.entity, cardRecord.entity) &&
        Objects.equals(this.userId, cardRecord.userId) &&
        Objects.equals(this.collectionIds, cardRecord.collectionIds) &&
        Objects.equals(this.borrowedByUserId, cardRecord.borrowedByUserId) &&
        Objects.equals(this.allianceId, cardRecord.allianceId) &&
        Objects.equals(this.donorUserId, cardRecord.donorUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, entity, userId, collectionIds, borrowedByUserId, allianceId, donorUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardRecord {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    entity: ").append(toIndentedString(entity)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    collectionIds: ").append(toIndentedString(collectionIds)).append("\n");
    sb.append("    borrowedByUserId: ").append(toIndentedString(borrowedByUserId)).append("\n");
    sb.append("    allianceId: ").append(toIndentedString(allianceId)).append("\n");
    sb.append("    donorUserId: ").append(toIndentedString(donorUserId)).append("\n");
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
