package me.arun.arunrxjavaexploring.RxOperaters;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.RxBasicActivity;
public class OperatorsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }
    public void observableActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ObservableOperaterActivity.class));
    }

    public void filteringOperatorActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, FilteringActivity.class));
    }
    public void combinOperaterActivtiy(View view) {
        startActivity(new Intent(OperatorsActivity.this, CombinOperaterActivtiy.class));
    }

    public void conditionBololeanOperaterActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ConditionBololeanOperater.class));
    }

    public void transFormingOperaterActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, TransFormingOperater.class));
    }

    public void utilityOperaterActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this,  UtilityOperater.class));
    }

    public void subjectOperators(View view)
    {
        startActivity(new Intent(OperatorsActivity.this,  SubjectOperatorActivity.class));
    }

}
