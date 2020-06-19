package com.google.step.data;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.apache.commons.validator.UrlValidator;

public final class OrganizationInfo {
  private final Entity entity;

  public OrganizationInfo(HttpServletRequest request) {
    this.entity = getOrgEntityFrom(request);
  }

  private Entity getOrgEntityFrom(HttpServletRequest request) {
    Entity newOrganization = new Entity("Organization");
    newOrganization.setProperty("name", request.getParameter("orgName"));
    newOrganization.setProperty("webLink", request.getParameter("webLink"));
    newOrganization.setProperty("donateLink", request.getParameter("donateLink"));
    newOrganization.setProperty("about", request.getParameter("about"));
    newOrganization.setProperty("flags", new ArrayList<String>());

    return newOrganization;
  }

  public Boolean isValid() {
    UrlValidator urlValidator = new UrlValidator();
    //Required fields
    if (((String) this.entity.getProperty("name")).isEmpty() || 
    	  ((String) this.entity.getProperty("name")).isEmpty() ||
        !urlValidator.isValid(((String) this.entity.getProperty("webLink")))) {
      return false;
    }
    //Change invalid Optional Fields to null
    if (urlValidator.isValid(((String) this.entity.getProperty("donateLink")))) {
      this.entity.setProperty("donateLink",null);
    }
    return true;
  }

  public void compare(Entity duplicate) {
    this.entity.getProperties().forEach((property, value) -> {
      if (Objects.isNull(value) && !Objects.isNull(duplicate.getProperty(property))) {
        this.entity.setProperty(property, duplicate.getProperty(property));
      } 
      else if (!Objects.isNull(value) && !Objects.isNull(duplicate.getProperty(property))) {
        //TODO: Handle case where fields are conflicting
      }
    });
  }

  public Entity getEntity() {
    return this.entity;
  }

  public Query getQueryForDuplicates() {
    return new Query("Organization").addFilter("name", Query.FilterOperator.EQUAL, entity.getProperty("name"));
  }
}
