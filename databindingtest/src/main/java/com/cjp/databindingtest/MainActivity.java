package com.cjp.databindingtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cjp.databindingtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Employee employee = new Employee("Chen","Marks");
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil. setContentView(this,R.layout.activity_main);
        binding.setEmployee(employee);
        binding.setPresenter(new Presenter());
    }

    public class Presenter{
        public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            employee.setFirstName(text.toString());
            binding.setEmployee(employee);
        }
        public void onClick(View view){
            Toast.makeText(MainActivity.this,"点到了",Toast.LENGTH_SHORT).show();
        }
        public void onOwnClick(Employee employee){
            Toast.makeText(MainActivity.this,employee.getFirstName()+employee.getLastName(),Toast.LENGTH_SHORT).show();
            Toas
        }
    }
}
