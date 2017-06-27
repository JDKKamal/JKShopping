package com.jdkgroup.example.removeelement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kamlesh on 6/13/2017.
 */

public class RunRemoveElement
{
    public static void main(String[] args)
    {
        List<ModelStudent> alStudent = new ArrayList<>();
        alStudent.add(new ModelStudent(1, "abc"));
        alStudent.add(new ModelStudent(2, "abcd"));
        alStudent.add(new ModelStudent(1, "abc"));
        alStudent.add(new ModelStudent(2, "abc"));


        //TODO REMOVE ELEMENT SAME
        List<ModelStudent> alremoveElement = removeElement(alStudent);
        System.out.println(alremoveElement.size());
    }

    public static <T> List<T> removeElement(List<T> list)
    {
        for (Iterator<T> itr = list.iterator(); itr.hasNext();)
        {
            ModelStudent student = (ModelStudent) itr.next();
            if (1 == student.getId())
            {
                itr.remove();
            }
        }
        return list;
    }

    //Collections.sort(alContact, NameComparator);
    public static Comparator<ModelStudent> NameComparator = new Comparator<ModelStudent>() {

        @Override
        public int compare(ModelStudent e1, ModelStudent e2) {
            return (int) (e1.getName().compareTo(e2.getName()));
        }
    };

    public class ComparatorDuplicateRemove implements Comparator<ModelStudent> {

        public int compare(ModelStudent e1, ModelStudent e2)
        {
            return e1.getName().compareToIgnoreCase(e2.getName());
        }
    }
}
