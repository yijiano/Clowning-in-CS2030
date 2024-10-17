import java.util.Optional;

public class Roster extends KeyableMap<Student> {
    Roster(String name) {
        super(name);
    }

    private Roster(String name, ImmutableMap<String, Student> studentMap) {
        super(name, studentMap);
    }

    public String getGrade(String studentName, String course, String assessment) {
        return get(studentName).flatMap(x -> x.get(course))
            .flatMap(x -> x.get(assessment))
            .map(x -> x.getGrade())
            .orElseGet(() -> String.format("No such record: %s %s %s", 
                        studentName, course, assessment));
    }

    public Roster add(String studentName, String courseName,
            String assessmentName, String grade) {

        Assessment newAssessment = new Assessment(assessmentName, grade);

        Student updatedStudent = this.get(studentName)
            .map(student -> student.get(courseName)
                    .map(course -> student.put(course.put(newAssessment)))
                    .orElse(student.put(new Course(courseName).put(newAssessment))))
            .orElse(new Student(studentName).put(new Course(courseName).put(newAssessment)));

        return new Roster(getKey(), getMap().put(studentName, updatedStudent));
    }

    @Override
    public Roster put(Student student) {
        return new Roster(getKey(), getMap().put(student.getKey(), student));
    }
}

