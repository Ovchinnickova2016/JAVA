package ru.stqa.java.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stq.java.sandbox.Point;

public class PointTest {

  @Test
  public void testArea(){
    Point p1 = new Point(1.0, 2.0);
    Point p2 = new Point(4.0, 2.0);
    Assert.assertEquals(p1.distance(p2), 3.0);
    Point p3 = new Point(1.0, 5.0);
    Point p4 = new Point(1.0, 0.0);
    Assert.assertEquals(p3.distance(p4), 5.0);
    Point p5 = new Point(2.0, 5.0);
    Point p6 = new Point(3.0, 5.0);
    Assert.assertEquals(p5.distance(p6), 1.0);
  }

}
