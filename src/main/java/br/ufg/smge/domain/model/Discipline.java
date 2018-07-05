package br.ufg.smge.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discipline", schema = "app")
public class Discipline extends PersistenceModel {

  /**
   * 
   */
  private static final long serialVersionUID = 6664987647446605233L;

  public Discipline() {
    // TODO Auto-generated constructor stub
  }

  public Discipline(String name, String courseLoad, String media) {
    super();
    this.name = name;
    this.courseLoad = courseLoad;
    this.media = media;
  }

  @Column(name = "name")
  private String name;

  @Column(name = "course_load")
  private String courseLoad;

  @Column(name = "media")
  private String media;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCourseLoad() {
    return courseLoad;
  }

  public void setCourseLoad(String courseLoad) {
    this.courseLoad = courseLoad;
  }

  public String getMedia() {
    return media;
  }

  public void setMedia(String media) {
    this.media = media;
  }

}
