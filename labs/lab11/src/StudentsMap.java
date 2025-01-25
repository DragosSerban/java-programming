import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class StudentsMap {
    private Map<Integer, ArrayList<Student>> m;

    public StudentsMap() {
        m = new TreeMap<Integer,
                ArrayList<Student>>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public void adaugaStudent(Student s) {
        int key = (int) Math.round(s.getMedia());

        if (!m.containsKey(key)) {
            m.put(key, new ArrayList<Student>());
        }

        m.get(key).add(s);
    }

    public void afiseaza() {
        for (Map.Entry<Integer, ArrayList<Student>> intrare : m.entrySet()) {
            System.out.print(intrare.getKey() + " ");
            System.out.println(intrare.getValue());
        }
    }
}
