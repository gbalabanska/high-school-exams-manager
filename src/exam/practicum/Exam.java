package exam.practicum;

import java.util.Objects;

/*
Данните за проведените матури са следните: 
име на ученик (знаков низ с дължина до 40 знака, съдържащ име, презиме и фамилия разделени с точно по един интервал),
служебен уникален номер (знаков низ с дължина 10 знака), 
училище (знаков низ с дължина до 50 знака), 
населено място (знаков низ с дължина до 40 знака), 
предмет (знаков низ с дължина до 30 знака, съдържащ редовни букви на кирилица), 
оценката получена на изпита по този предмет (реално число с два знака в дробната част).
 */
public class Exam implements Comparable<Exam>
{
  private String studName;
  private String studNumber;
  private String school;
  private String city;
  private String subject;
  private double note;

  public void setStudName(String studName) throws InvalidExamException
  {
    studName = studName.trim(); // Trim leading and trailing spaces

    if (isValidName(studName))
    {
      this.studName = studName;
    }
    else
    {
      throw new InvalidExamException("Invalid student name format. The name should not be more than 40 characters long and consist of three words separated by spaces.");
    }
  }

  private boolean isValidName(String name)
  {
    if (name.length() > 40)
    {
      return false;
    }

    String[] words = name.split(" ");
    return words.length == 3;
  }

  public void setStudNumber(String studNumber) throws InvalidExamException
  {
    if (studNumber.length() == 10)
    {
      this.studNumber = studNumber;
    }
    else
    {
      throw new InvalidExamException("Invalid student number format. The student number should be exactly 10 characters long.");
    }
  }

  public void setSchool(String school) throws InvalidExamException
  {
    if (school.length() <= 50)
    {
      this.school = school;
    }
    else
    {
      throw new InvalidExamException("Invalid school format. The school shouldn't be more than 50 characters long.");
    }
  }

  public void setCity(String city) throws InvalidExamException
  {
    if (city.length() <= 40)
    {
      this.city = city;
    }
    else
    {
      throw new InvalidExamException("Invalid city format. The city shouldn't be more than 40 characters long.");
    }

  }

  public void setSubject(String subject) throws InvalidExamException
  {
    if (subject.length() <= 30)
    {
      this.subject = subject;
    }
    else
    {
      throw new InvalidExamException("Invalid subject format. The subject should be up to 30 characters.");
    }
  }

  public void setNote(double note) throws InvalidExamException
  {
    double lowerBound = 2, upperBound = 6;
    if (note >= lowerBound && note <= upperBound)
    {
      this.note = note;
    }
    else
    {
      throw new InvalidExamException("Invalid note format. The note should be within the range of 2 to 6.");
    }
  }

  @Override
  public String toString()
  {
    String[] nameParts = studName.split(" ");
    String lastName = nameParts[2];
    String capitalFirstLatter = lastName.substring(0, 1).toUpperCase(); 
    String lowerRestOfString = lastName.substring(1).toLowerCase();  
    String lastNameFormatted = capitalFirstLatter + lowerRestOfString;

    String initials = "";
    initials += Character.toUpperCase(nameParts[0].charAt(0)) + ". ";
    initials += Character.toUpperCase(nameParts[1].charAt(0)) + ". ";
    
    String formattedNote = String.format("%.2f", note);
    return String.format("%s, %s %s, %s, %s, %s;", studNumber, lastNameFormatted, initials.trim(), city, subject, formattedNote);
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Exam exam = (Exam) obj;
    return Objects.equals(studNumber, exam.studNumber)
           && Objects.equals(subject, exam.subject);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(studNumber, subject);
  }

  @Override
  public int compareTo(Exam other)
  {
    return this.studNumber.compareTo(other.studNumber);
  }

  public Exam()
  {
  }

  public Exam(String name, String studNumber, String school, String city, String subject, double note)
  {
    this.studName = name;
    this.studNumber = studNumber;
    this.school = school;
    this.city = city;
    this.subject = subject;
    this.note = note;
  }

  public String getStudName()
  {
    return studName;
  }

  public String getStudNumber()
  {
    return studNumber;
  }

  public String getSchool()
  {
    return school;
  }

  public String getCity()
  {
    return city;
  }

  public String getSubject()
  {
    return subject;
  }

  public double getNote()
  {
    return note;
  }

  
}
