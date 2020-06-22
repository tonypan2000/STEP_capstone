package com.google.step.servlets;

import com.google.step.similarity.OrganizationsProtos.Organizations;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/predict")
public class PredictionServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String path = "../../../../../../../../neighbors";
    Organizations.Builder orgs = readProtobuf(path + ".txt");
  }

  private static Organizations.Builder readProtobuf(String filename) {
    Organizations.Builder orgs = Organizations.newBuilder();
    // Read the existing organizations
    try {
      orgs = Organizations.parseFrom(new FileInputStream(filename));
    } catch (IOException e) {
      System.err.println(filename + ": File not found.");
    }
    return orgs;
  }

}
