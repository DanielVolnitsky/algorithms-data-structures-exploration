package com.waytoodanny.coursera.week3.collinearpoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BruteCollinearPoints {

  private LineSegment[] resultingSegments;
  private List<LineSegment> segments = new ArrayList<>();

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    checkForNull(points);
    checkForDuplicates(points);

    for (int i = 0; i < points.length - 3; i++) {
      for (int j = i + 1; j < points.length - 2; j++) {
        for (int k = j + 1; k < points.length - 1; k++) {
          for (int l = k + 1; l < points.length; l++) {
            handleCollinear(points[i], points[j], points[k], points[l]);
          }
        }
      }
    }

    resultingSegments = segments.toArray(new LineSegment[]{});
    segments = null;
  }

  private void handleCollinear(Point p1, Point p2, Point p3, Point p4) {
    double slope1 = p1.slopeTo(p2);
    double slope2 = p1.slopeTo(p3);
    double slope3 = p1.slopeTo(p4);

    if (Double.compare(slope1, slope2) == 0
        && Double.compare(slope1, slope3) == 0) {
      segments.add(new LineSegment(p1, p4));
    }
  }

  public int numberOfSegments() {
    return resultingSegments.length;
  }

  public LineSegment[] segments() {
    return resultingSegments;
  }

  private void checkForDuplicates(Point[] points) {
    Point[] copy = Arrays.copyOf(points, points.length);
    Arrays.sort(copy);
    for (int i = 0; i < copy.length - 1; i++) {
      if (copy[i].compareTo(copy[i + 1]) == 0) {
        throw new IllegalArgumentException();
      }
    }
  }

  private void checkForNull(Point[] points) {
    if (points == null) {
      throw new NullPointerException();
    }
    Arrays.stream(points)
        .filter(Objects::isNull)
        .findAny()
        .ifPresent(p -> {
          throw new IllegalArgumentException();
        });
  }
}
