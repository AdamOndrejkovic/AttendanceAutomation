package be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StudentClasses {
    private String name;
    private List<Student> classListtttt = new List<Student>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Student> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Student student) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Student> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Student> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public Student get(int index) {
            return null;
        }

        @Override
        public Student set(int index, Student element) {
            return null;
        }

        @Override
        public void add(int index, Student element) {

        }

        @Override
        public Student remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Student> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Student> listIterator(int index) {
            return null;
        }

        @Override
        public List<Student> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public List<Student> getClassListtttt() {
        return classListtttt;
    }

    private ObservableList<Student> studentsListInClass = FXCollections.observableArrayList();

    public StudentClasses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ObservableList<Student> getOneOfClassesStudentList() {
        return studentsListInClass;
    }

}
