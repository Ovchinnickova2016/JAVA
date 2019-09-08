package ru.stqa.java.sandbox;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stq.java.sandbox.Point;

public class PointTest {

  @Test
  public void testArea(){
    Point points = new Point(1.1, 2.1, 4.1, 3.1);
    Assert.assertEquals(points.distance(), 2);
  }
}
