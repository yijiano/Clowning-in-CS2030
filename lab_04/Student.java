public class Student extends KeyableMap<Course> {
    Student(String name) {
        super(name);
    }

    private Student(String name, ImmutableMap<String, Course> courseMap) {
        super(name, courseMap);
    }

    @Override
    public Student put(Course course) {
        return new Student(getKey(), getMap().put(course.getKey(), course));
    }
}

