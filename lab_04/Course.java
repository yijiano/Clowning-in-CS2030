public class Course extends KeyableMap<Assessment> {
    Course(String name) {
        super(name);
    }

    private Course(String name, ImmutableMap<String, Assessment> assessmentMap) {
        super(name, assessmentMap);
    }

    @Override
    public Course put(Assessment assessment) {
        return new Course(getKey(), getMap().put(assessment.getKey(), assessment));
    }
}

