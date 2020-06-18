package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import javax.servlet.http.HttpServletRequest;

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

    return newOrganization;
  }

  public Entity getEntity() {
    return this.entity;
  }

  public Query getQueryForDuplicates() {
    return new Query("Organization").addFilter("name", Query.FilterOperator.EQUAL, entity.getProperty("name"));
  }
}
