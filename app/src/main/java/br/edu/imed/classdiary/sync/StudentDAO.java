package br.edu.imed.classdiary.sync;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by Uzumaki on 13/06/2015.
 */
public class StudentDAO extends DatabaseManager<Student> {
    public StudentDAO(Context context) {
        super(context);
    }
    public void insert(Student student) throws Exception {
        super.insert("pessoa", student.getContentValues());
    }
}
