package leavesc.hello.databindingsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import leavesc.hello.databindingsamples.common.adapter.CommonAdapter;
import leavesc.hello.databindingsamples.common.adapter.SimpleAdapter;
import leavesc.hello.databindingsamples.common.adapter.Student;

/**
 * 进行通用recycleAdapter 的案例使用
 * 使用其中的案例，还有突发无法检测的问题，建议不要使用
 */
@Deprecated
public class CommonAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_adapter);

        RecyclerView recyclerView = findViewById(R.id.show_list);

        List<Student> students = new ArrayList<>();
        Student student = new Student(R.mipmap.ic_launcher, "Kate");
        students.add(student);
        student = new Student(R.mipmap.ic_launcher, "Kate");
        students.add(student);
        student = new Student(R.mipmap.ic_launcher, "Johnson");
        students.add(student);
        student = new Student(R.mipmap.ic_launcher, "Make");
        students.add(student);

        SimpleAdapter<Student> adapter = new SimpleAdapter<>(students, R.layout.student_item, BR.student);
        recyclerView.setAdapter(adapter);

    }
}
