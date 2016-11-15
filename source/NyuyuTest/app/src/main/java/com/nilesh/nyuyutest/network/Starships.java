package com.nilesh.nyuyutest.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 11/15/16.
 */

public class Starships {

  private String name;
  private String model;
  private String manufacturer;
  private String cost_in_credits;
  private String length;
  private String maxAtmospheringSpeed;
  private String crew;
  private String passengers;
  private String cargoCapacity;
  private String consumables;
  private String hyperdriveRating;
  private String mGLT;
  private String starshipClass;
  private List<Object> pilots = new ArrayList<Object>();
  private List<String> films = new ArrayList<String>();
  private String created;
  private String edited;
  private String url;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * @return The name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name The name
   */
  public void setName(String name) {
    this.name = name;
  }

  public Starships withName(String name) {
    this.name = name;
    return this;
  }

  /**
   * @return The model
   */
  public String getModel() {
    return model;
  }

  /**
   * @param model The model
   */
  public void setModel(String model) {
    this.model = model;
  }

  public Starships withModel(String model) {
    this.model = model;
    return this;
  }

  /**
   * @return The manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * @param manufacturer The manufacturer
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public Starships withManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
    return this;
  }

  /**
   * @return The costInCredits
   */
  public String getCostInCredits() {
    return cost_in_credits;
  }

  /**
   * @param costInCredits The cost_in_credits
   */
  public void setCostInCredits(String costInCredits) {
    this.cost_in_credits = costInCredits;
  }

  public Starships withCostInCredits(String costInCredits) {
    this.cost_in_credits = costInCredits;
    return this;
  }

  /**
   * @return The length
   */
  public String getLength() {
    return length;
  }

  /**
   * @param length The length
   */
  public void setLength(String length) {
    this.length = length;
  }

  public Starships withLength(String length) {
    this.length = length;
    return this;
  }

  /**
   * @return The maxAtmospheringSpeed
   */
  public String getMaxAtmospheringSpeed() {
    return maxAtmospheringSpeed;
  }

  /**
   * @param maxAtmospheringSpeed The max_atmosphering_speed
   */
  public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
    this.maxAtmospheringSpeed = maxAtmospheringSpeed;
  }

  public Starships withMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
    this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    return this;
  }

  /**
   * @return The crew
   */
  public String getCrew() {
    return crew;
  }

  /**
   * @param crew The crew
   */
  public void setCrew(String crew) {
    this.crew = crew;
  }

  public Starships withCrew(String crew) {
    this.crew = crew;
    return this;
  }

  /**
   * @return The passengers
   */
  public String getPassengers() {
    return passengers;
  }

  /**
   * @param passengers The passengers
   */
  public void setPassengers(String passengers) {
    this.passengers = passengers;
  }

  public Starships withPassengers(String passengers) {
    this.passengers = passengers;
    return this;
  }

  /**
   * @return The cargoCapacity
   */
  public String getCargoCapacity() {
    return cargoCapacity;
  }

  /**
   * @param cargoCapacity The cargo_capacity
   */
  public void setCargoCapacity(String cargoCapacity) {
    this.cargoCapacity = cargoCapacity;
  }

  public Starships withCargoCapacity(String cargoCapacity) {
    this.cargoCapacity = cargoCapacity;
    return this;
  }

  /**
   * @return The consumables
   */
  public String getConsumables() {
    return consumables;
  }

  /**
   * @param consumables The consumables
   */
  public void setConsumables(String consumables) {
    this.consumables = consumables;
  }

  public Starships withConsumables(String consumables) {
    this.consumables = consumables;
    return this;
  }

  /**
   * @return The hyperdriveRating
   */
  public String getHyperdriveRating() {
    return hyperdriveRating;
  }

  /**
   * @param hyperdriveRating The hyperdrive_rating
   */
  public void setHyperdriveRating(String hyperdriveRating) {
    this.hyperdriveRating = hyperdriveRating;
  }

  public Starships withHyperdriveRating(String hyperdriveRating) {
    this.hyperdriveRating = hyperdriveRating;
    return this;
  }

  /**
   * @return The mGLT
   */
  public String getMGLT() {
    return mGLT;
  }

  /**
   * @param mGLT The MGLT
   */
  public void setMGLT(String mGLT) {
    this.mGLT = mGLT;
  }

  public Starships withMGLT(String mGLT) {
    this.mGLT = mGLT;
    return this;
  }

  /**
   * @return The starshipClass
   */
  public String getStarshipClass() {
    return starshipClass;
  }

  /**
   * @param starshipClass The starship_class
   */
  public void setStarshipClass(String starshipClass) {
    this.starshipClass = starshipClass;
  }

  public Starships withStarshipClass(String starshipClass) {
    this.starshipClass = starshipClass;
    return this;
  }

  /**
   * @return The pilots
   */
  public List<Object> getPilots() {
    return pilots;
  }

  /**
   * @param pilots The pilots
   */
  public void setPilots(List<Object> pilots) {
    this.pilots = pilots;
  }

  public Starships withPilots(List<Object> pilots) {
    this.pilots = pilots;
    return this;
  }

  /**
   * @return The films
   */
  public List<String> getFilms() {
    return films;
  }

  /**
   * @param films The films
   */
  public void setFilms(List<String> films) {
    this.films = films;
  }

  public Starships withFilms(List<String> films) {
    this.films = films;
    return this;
  }

  /**
   * @return The created
   */
  public String getCreated() {
    return created;
  }

  /**
   * @param created The created
   */
  public void setCreated(String created) {
    this.created = created;
  }

  public Starships withCreated(String created) {
    this.created = created;
    return this;
  }

  /**
   * @return The edited
   */
  public String getEdited() {
    return edited;
  }

  /**
   * @param edited The edited
   */
  public void setEdited(String edited) {
    this.edited = edited;
  }

  public Starships withEdited(String edited) {
    this.edited = edited;
    return this;
  }

  /**
   * @return The url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url The url
   */
  public void setUrl(String url) {
    this.url = url;
  }

  public Starships withUrl(String url) {
    this.url = url;
    return this;
  }

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public Starships withAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    return this;
  }
}
