package ru.stqa.java.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stq.java.sandbox.Point;

public class PointTest {

  @Test
  public void testArea(){
    Point points = new Point(1, 2, 4, 2);
    Assert.assertEquals(points.distance(), 3);
    Point points2 = new Point(1, 5, 1, 0);
    Assert.assertEquals(points2.distance(), 5);
    Point points3 = new Point(2, 5, 3, 5);
    Assert.assertEquals(points3.distance(), 1);
  }
}
