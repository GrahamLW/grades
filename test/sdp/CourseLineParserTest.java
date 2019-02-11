package sdp;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CourseLineParserTest {
  @Test
  public void correctlyProcessesWellFormattedCourse() {
    assertInput_producesCourse("CS 121   A",
                               new Course("CS", "121", Grade.A));
    assertInput_producesCourse("CS 121   A \n ",
                               new Course("CS", "121", Grade.A));
    assertInput_producesCourse("CS 121L1   B- \n ",
                               new Course("CS", "121L1", Grade.BMINUS));
  }

  @Test
  public void correctlyProcessesCoursesWithUnits() {
    assertInput_producesCourse("CS 121  0.5  A",
                               new Course("CS", "121", Grade.A, 0.5));
    assertInput_producesCourse("CS 121  1  A",
                               new Course("CS", "121", Grade.A, 1));
  }

  @Test
  public void nextLineReturnsRemainingOfCurrentLineThenSkipsNewline() {
    Scanner scanner = new Scanner(" CS  \n ");
    assertEquals("CS", scanner.next());
    assertEquals(true, scanner.hasNextLine());
    assertEquals("  ", scanner.nextLine());
    assertEquals(" ", scanner.nextLine());
  }

  private void assertInput_producesCourse(String input, Course course) {
    assertEquals(course, new CourseLineParser(new Scanner(input)).readCourse());
  }
}
