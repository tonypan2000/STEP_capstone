package com.google.step;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class OrganizationInfoTest {
  @Test
  public void ConstructorTest() {
    Entity entity = new Entity("Test");
    OrganizationInfo test = new OrganizationInfo(entity);
  }
}
