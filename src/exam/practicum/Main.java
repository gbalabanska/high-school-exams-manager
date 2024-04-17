package exam.practicum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author gabriela.balabanska
 */

public class Main
{
  private static Set<Exam> exams = new HashSet<>();
  private static Scanner scanner = new Scanner(System.in);
  private static int n = 0; // Exam count

  public static void main(String[] args)
  {
    // 1.
    // Enter valid exam count
    enterN();

    // Add n count exams
    for (int i = 1; i <= n; i++)
    {
      System.out.println();
      System.out.println("Enter information for Exam No:" + i);

      Exam currentExam;
      try
      {
        currentExam = enterExam();
      }
      catch (InvalidExamException iee)
      {
        System.out.println("Invalid exam information: " + iee.getMessage());
        i--;
        continue;
      }

      // Using Set to prevent duplicate exams; overridden equals method checks by subject and student number.
      if (exams.add(currentExam))
      {
        System.out.println("Exam information successfully entered for Exam No:" + i);
      }
      else
      {
        System.out.println("Exam No: " + i + " wasn't added. Exam with subject '" + currentExam.getSubject() + "' and student number '"
                           + currentExam.getStudNumber() + "' already exists.");
        i--;
      }
    }

    // 2.
    // Convert the set of exams to a list and sort it using the overridden compareTo method
    List<Exam> sortedExams = new ArrayList<>(exams);
    sortedExams.sort(Exam::compareTo);
    // Print sorted list
    printExams(sortedExams);

    // 3.
    // Get exams by subject and order by student number 
    System.out.print("Enter subject: ");
    String subject = scanner.nextLine();
    sortedExams = getExamsBySubjectOrbderByStudNumberDesc(subject);
    // Print exams
    printExamsBySubject(sortedExams);
  }

  // Method to filter exams by subject and return a sorted list
  private static List<Exam> getExamsBySubjectOrbderByStudNumberDesc(String subject)
  {
    return exams.stream()
        .filter(exam -> exam.getSubject().equalsIgnoreCase(subject))
        .sorted(Comparator.comparing(Exam::getNote, Comparator.reverseOrder())
            .thenComparing(Exam::getStudName))
        .collect(Collectors.toList());
  }

  // Method to calculate average score for a student
  private static double calculateAverageScore(String studNumber)
  {
    List<Exam> studentExams = exams.stream()
        .filter(exam -> exam.getStudNumber().equals(studNumber))
        .collect(Collectors.toList());

    double sum = studentExams.stream().mapToDouble(Exam::getNote).sum();
    return sum / studentExams.size();
  }

  // Method to print the required details
  private static void printExamsBySubject(List<Exam> sortedExams)
  {
    for (Exam exam : sortedExams)
    {
      double averageScore = calculateAverageScore(exam.getStudNumber());
      System.out.printf("Name: %s, School: %s, City: %s, Subject: %s, Note: %.2f, Average Score: %.2f%n",
                        exam.getStudName(), exam.getSchool(), exam.getCity(),
                        exam.getSubject(), exam.getNote(), averageScore);
    }
  }

  private static Exam enterExam() throws InvalidExamException
  {
    Exam exam = new Exam();
    try
    {
      System.out.print("Enter student name: ");
      String studentName = scanner.nextLine();
      exam.setStudName(studentName);

      System.out.print("Enter student number: ");
      String studentNumber = scanner.nextLine();
      exam.setStudNumber(studentNumber);

      System.out.print("Enter school: ");
      String school = scanner.nextLine();
      exam.setSchool(school);

      System.out.print("Enter city: ");
      String city = scanner.nextLine();
      exam.setCity(city);

      System.out.print("Enter subject: ");
      String subject = scanner.nextLine();
      exam.setSubject(subject);

      System.out.print("Enter grade: ");

      double note = 0;
      try
      {
        note = Double.parseDouble(scanner.nextLine());
      }
      catch (NumberFormatException nfe)
      {
        throw new InvalidExamException("Invalid grade format. Please enter a valid numeric grade.");
      }
      exam.setNote(note);
    }
    catch (Exception e)
    {
      throw new InvalidExamException(e.getMessage());
    }
    return exam;
  }

  private static void enterN()
  {
    do
    {
      System.out.print("Enter number: ");
      try
      {
        n = Integer.parseInt(scanner.nextLine());
      }
      catch (NumberFormatException nfe)
      {
        System.out.println("Invalid input for n. Please enter a positive integer.");
      }
    }
    while (n < 1);
  }

  private static void printExams(List<Exam> sortedExams)
  {
    for (Exam exam : sortedExams)
    {
      System.out.println(exam);
    }
  }

}
